package com.github.nikdon.telepooz.interpreters

import akka.actor.ActorSystem
import akka.event.Logging
import akka.http.scaladsl.Http
import akka.http.scaladsl.Http.OutgoingConnection
import akka.http.scaladsl.client.RequestBuilding
import akka.http.scaladsl.model.{HttpRequest, HttpResponse, Uri}
import akka.stream._
import akka.stream.scaladsl.{Flow, Keep, RunnableGraph, Sink, Source}
import cats.std.string._
import cats.syntax.semigroup._
import cats.~>
import com.github.nikdon.telepooz.model._
import com.github.nikdon.telepooz.raw.RawRequest.{ForwardMessage, GetMe, GetUpdates, SendMessage}
import com.github.nikdon.telepooz.raw.{CirceDecoders, RawRequest}
import com.typesafe.config.{Config, ConfigFactory}
import de.heikoseeberger.akkahttpcirce.CirceSupport
import io.circe.{Decoder, Json}

import scala.concurrent.{ExecutionContextExecutor, Future}


trait ApiRequestExecutor extends (RawRequest ~> Future)
                                 with CirceSupport
                                 with CirceDecoders {

  implicit def system: ActorSystem
  implicit def executor: ExecutionContextExecutor
  implicit def materializer: Materializer

  def config: Config = ConfigFactory.load()
  def telegramHost: String = config.getString("telegram.host")
  def token: String = config.getString("telegram.token")

  def logger = Logging(system, getClass)

  private[this] def telegramConnectionFlow: Flow[HttpRequest, HttpResponse, Future[OutgoingConnection]] =
    Http(system).outgoingConnectionHttps(telegramHost)

  private[this] def telegramRequest(request: HttpRequest): RunnableGraph[Future[HttpResponse]] =
    Source.single(request).via(telegramConnectionFlow).toMat(Sink.head)(Keep.right)

  override def apply[A](fa: RawRequest[A]): Future[A] = {

    def go[B: Decoder](methodName: String, payload: Json): Future[Response[B]] = {
      val uri = "https://" |+| telegramHost |+| "/bot" + token |+| "/" + methodName
      for {
        response ← telegramRequest(RequestBuilding.Post(Uri(uri), content = payload)).run()
        decoded ← circeUnmarshaller(responseDecoder).apply(response.entity)
      } yield decoded
    }

    fa match {
      case m@GetMe                   => go(m.name, m.payload)
      case m@SendMessage(payload)    => go(m.name, m.payload)
      case m@ForwardMessage(payload) => go(m.name, m.payload)
      case m@GetUpdates(payload)     => go(m.name, m.payload)
    }
  }
}
