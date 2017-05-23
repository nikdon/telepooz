/*
 * Copyright 2016 Nikolay Donets
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
import com.github.nikdon.telepooz.api._
import com.github.nikdon.telepooz.model.Update
import com.github.nikdon.telepooz.model.methods.SetWebhook
import com.github.nikdon.telepooz.json.CirceDecoders
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport._

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

  private[this] val route: Route = path(endpoint) {
    entity(as[Update]) { update ⇒
      log.debug("Received request with update {}", update.update_id)
      self ! update
      complete(OK)
    }
  }

  override def preStart() = {
    SetWebhook(endpoint)
      .foldMap(are)
      .flatMap(_ ⇒ {
        Http().bindAndHandle(route, interface, port)
      })
      .pipeTo(self)
  }

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
