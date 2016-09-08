package com.github.nikdon.telepooz.engine

import akka.actor.{ActorLogging, ActorRef, Props, Stash}
import akka.http.scaladsl.Http
import akka.http.scaladsl.Http.ServerBinding
import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server._
import akka.pattern.pipe
import akka.stream.ActorMaterializer
import akka.stream.actor.ActorPublisher
import akka.stream.actor.ActorPublisherMessage.{Cancel, Request}
import akka.stream.scaladsl.Source
import cats.instances.future._
import com.github.nikdon.telepooz.ToRawRequest.syntax._
import com.github.nikdon.telepooz.api
import com.github.nikdon.telepooz.model.Update
import com.github.nikdon.telepooz.model.methods.SetWebhook
import com.github.nikdon.telepooz.raw.CirceDecoders
import de.heikoseeberger.akkahttpcirce.CirceSupport._

class Webhook(endpoint: String, interface: String = "::0", port: Int = 8080)(implicit are: ApiRequestExecutor,
                                                                             materializer: ActorMaterializer) {
  val source: Source[Update, ActorRef] =
    Source.actorPublisher[Update](UpdatePublisher.props(endpoint, interface, port))
}

private[this] object UpdatePublisher {
  def props(endpoint: String, interface: String, port: Int)(implicit are: ApiRequestExecutor,
                                                            materializer: ActorMaterializer): Props =
    Props(new UpdatePublisher(endpoint, interface, port))
}

private[this] class UpdatePublisher(endpoint: String, interface: String, port: Int)(implicit are: ApiRequestExecutor,
                                                                                    materializer: ActorMaterializer)
    extends ActorPublisher[Update]
    with Stash
    with ActorLogging
    with CirceDecoders {

  implicit val system = context.system
  implicit val ec     = context.dispatcher

  var binding: ServerBinding = _

  private[this] val route: Route = pathEndOrSingleSlash {
    entity(as[Update]) { update ⇒
      log.debug("Received request with update {}", update.update_id)
      self ! update
      complete(OK)
    }
  }

  override def preStart() =
    api
      .execute(SetWebhook(endpoint).toRawRequest)
      .foldMap(are)
      .flatMap(_ ⇒ {
        Http().bindAndHandle(route, interface, port)
      })
      .pipeTo(self)

  def inactive: Receive = {
    case bind: ServerBinding ⇒
      binding = bind
      log.debug("Ready for request handling at {}", bind)
      context.become(active)
      unstashAll()
    case msg ⇒
      log.debug("Inactive, stash message: {}", msg)
      stash()
  }

  def active: Receive = {
    case update: Update ⇒
      log.debug("Received message with update {}", update.update_id)
      onNext(update)
    case Cancel ⇒
      log.debug("Received Cancel")
      binding.unbind()
      context.stop(self)
    case Request(_) ⇒ log.debug("Received Request(_)")
    case unknown    ⇒ log.debug("Received unknown message: {}", unknown)
  }

  override def receive: Receive = inactive
}
