package com.github.nikdon.telepooz.interpreters

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.Http.OutgoingConnection
import akka.http.scaladsl.client.RequestBuilding
import akka.http.scaladsl.model.{HttpRequest, HttpResponse, Uri}
import akka.http.scaladsl.unmarshalling.Unmarshal
import akka.stream.Materializer
import akka.stream.scaladsl.{Flow, Keep, RunnableGraph, Sink, Source}
import cats.~>
import com.github.nikdon.telepooz.dto
import com.github.nikdon.telepooz.raw.RawRequest
import com.typesafe.config.{Config, ConfigFactory}
import de.heikoseeberger.akkahttpcirce.CirceSupport
import io.circe.Decoder
import io.circe.generic.auto._

import scala.concurrent.duration._
import scala.concurrent.{ExecutionContextExecutor, Future}


abstract class AkkaInterpreter extends (RawRequest ~> Future) with CirceSupport {

  implicit def system: ActorSystem
  implicit def executor: ExecutionContextExecutor
  implicit def materializer: Materializer

  def config: Config = ConfigFactory.load()
  def telegramHost: String = config.getString("telegram.host")
  def token: String = config.getString("telegram.token")
  def timeout = 10.seconds

  def telegramConnectionFlow: Flow[HttpRequest, HttpResponse, Future[OutgoingConnection]] =
    Http(system).outgoingConnectionHttps(telegramHost)

  def telegramRequest(request: HttpRequest): RunnableGraph[Future[HttpResponse]] =
    Source.single(request).via(telegramConnectionFlow).toMat(Sink.head)(Keep.right)

  def toHttpRequest[A](c: RawRequest[A]): HttpRequest = {
    val uri = "https://" + telegramHost + "/bot" + token +"/" + c.name
    val payload = c.json
    val res = RequestBuilding.Post(Uri(uri), content = payload)
    println("res = " + res)
    res
  }

  override def apply[A](fa: RawRequest[A]): Future[A] = {
    implicit val userJosnDecoder = Decoder[dto.User]
    implicit val chatJosnDecoder = Decoder[dto.Chat]
    implicit val messageEntityJosnDecoder = Decoder[dto.MessageEntity]
    implicit val audioJosnDecoder = Decoder[dto.Audio]
    implicit val documentJosnDecoder = Decoder[dto.Document]
    implicit val photoSizeJosnDecoder = Decoder[dto.PhotoSize]
    implicit val stickerJosnDecoder = Decoder[dto.Sticker]
    implicit val voiceJosnDecoder = Decoder[dto.Voice]
    implicit val videoJosnDecoder = Decoder[dto.Video]
    implicit val contactJosnDecoder = Decoder[dto.Contact]
    implicit val locationJosnDecoder = Decoder[dto.Location]
    implicit val venueJosnDecoder = Decoder[dto.Venue]
    implicit val messageJsonDecoder = Decoder[dto.Message]

    fa match {
      case m@RawRequest.GetMe ⇒
        val r = toHttpRequest(m)
        implicit val unmarshaler = circeUnmarshaller[A]
        telegramRequest(r).run().flatMap(res ⇒ {
          println("res = " + res)
          Unmarshal(res).to[A]
        })

      case m@RawRequest.SendMessage(json) ⇒
        val r = toHttpRequest(m)
        implicit val unmarshaler = circeUnmarshaller[A]
        telegramRequest(r).run().flatMap(res ⇒ Unmarshal(res).to[A])


      case m@RawRequest.ForwardMessage(json) ⇒ ???
    }
  }
}
