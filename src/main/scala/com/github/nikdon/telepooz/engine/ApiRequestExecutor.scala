package com.github.nikdon.telepooz.engine

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.client.RequestBuilding
import akka.http.scaladsl.model.Uri
import akka.stream._
import cats.implicits._
import cats.~>
import com.github.nikdon.telepooz.model._
import com.github.nikdon.telepooz.raw.RawRequest._
import com.github.nikdon.telepooz.raw._
import com.typesafe.config.{Config, ConfigFactory}
import de.heikoseeberger.akkahttpcirce.CirceSupport
import io.circe.syntax._
import io.circe.{Decoder, Json, JsonObject}

import scala.concurrent.{ExecutionContextExecutor, Future}


abstract class ApiRequestExecutor(implicit system: ActorSystem, materializer: Materializer, ec: ExecutionContextExecutor)
  extends (RawRequest ~> Future)
          with CirceSupport
          with CirceEncoders
          with CirceDecoders {

  protected lazy val config      : Config = ConfigFactory.load()
  protected lazy val telegramHost: String = config.getString("telegram.host")
  protected lazy val token       : String = config.getString("telegram.token")

  private[this] lazy val http = Http()

  private[this] def dropNulls(j: Json): Json = j.withObject { c ⇒
    val fields = c.toList.filterNot { case (f, v) ⇒ v.isNull }
    JsonObject.fromIterable(fields).asJson
  }

  private[this] def go[B: Decoder](methodName: String, payload: Json): Future[Response[B]] = {
    val uri = "https://" |+| telegramHost |+| "/bot" + token |+| "/" + methodName
    for {
      response ← http.singleRequest(RequestBuilding.Post(Uri(uri), content = dropNulls(payload)))
      decoded ← circeUnmarshaller(responseDecoder).apply(response.entity)
    } yield decoded
  }

  override def apply[A](fa: RawRequest[A]): Future[A] = fa match {
    case m: GetMe.type            ⇒ go(m.name, m.payload)
    case m: SendMessage           ⇒ go(m.name, m.payload)
    case m: ForwardMessage        ⇒ go(m.name, m.payload)
    case m: GetUpdates            ⇒ go(m.name, m.payload)
    case m: SendPhoto             ⇒ go(m.name, m.payload)
    case m: SendAudio             ⇒ go(m.name, m.payload)
    case m: SendDocument          ⇒ go(m.name, m.payload)
    case m: SendSticker           ⇒ go(m.name, m.payload)
    case m: SendVideo             ⇒ go(m.name, m.payload)
    case m: SendVoice             ⇒ go(m.name, m.payload)
    case m: SendLocation          ⇒ go(m.name, m.payload)
    case m: SendVenue             ⇒ go(m.name, m.payload)
    case m: SendContact           ⇒ go(m.name, m.payload)
    case m: SendChatAction        ⇒ go(m.name, m.payload)
    case m: GetUserProfilePhotos  ⇒ go(m.name, m.payload)
    case m: GetFile               ⇒ go(m.name, m.payload)
    case m: KickChatMember        ⇒ go(m.name, m.payload)
    case m: LeaveChat             ⇒ go(m.name, m.payload)
    case m: UnbanChatMember       ⇒ go(m.name, m.payload)
    case m: GetChat               ⇒ go(m.name, m.payload)
    case m: GetChatAdministrators ⇒ go(m.name, m.payload)
    case m: GetChatMembersCount   ⇒ go(m.name, m.payload)
    case m: GetChatMember         ⇒ go(m.name, m.payload)
    case m: AnswerCallbackQuery   ⇒ go(m.name, m.payload)
  }
}
