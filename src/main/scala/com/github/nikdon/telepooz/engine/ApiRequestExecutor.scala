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

  protected val config      : Config = ConfigFactory.load()
  protected val telegramHost: String = config.getString("telegram.host")
  protected val token       : String = config.getString("telegram.token")

  private[this] val http = Http()

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
    case m@GetMe                          ⇒ go(m.name, m.payload)
    case m@SendMessage(payload)           ⇒ go(m.name, m.payload)
    case m@ForwardMessage(payload)        ⇒ go(m.name, m.payload)
    case m@GetUpdates(payload)            ⇒ go(m.name, m.payload)
    case m@SendPhoto(payload)             ⇒ go(m.name, m.payload)
    case m@SendAudio(payload)             ⇒ go(m.name, m.payload)
    case m@SendDocument(payload)          ⇒ go(m.name, m.payload)
    case m@SendSticker(payload)           ⇒ go(m.name, m.payload)
    case m@SendVideo(payload)             ⇒ go(m.name, m.payload)
    case m@SendVoice(payload)             ⇒ go(m.name, m.payload)
    case m@SendLocation(payload)          ⇒ go(m.name, m.payload)
    case m@SendVenue(payload)             ⇒ go(m.name, m.payload)
    case m@SendContact(payload)           ⇒ go(m.name, m.payload)
    case m@SendChatAction(payload)        ⇒ go(m.name, m.payload)
    case m@GetUserProfilePhotos(payload)  ⇒ go(m.name, m.payload)
    case m@GetFile(payload)               ⇒ go(m.name, m.payload)
    case m@KickChatMember(payload)        ⇒ go(m.name, m.payload)
    case m@LeaveChat(payload)             ⇒ go(m.name, m.payload)
    case m@UnbanChatMember(payload)       ⇒ go(m.name, m.payload)
    case m@GetChat(payload)               ⇒ go(m.name, m.payload)
    case m@GetChatAdministrators(payload) ⇒ go(m.name, m.payload)
    case m@GetChatMembersCount(payload)   ⇒ go(m.name, m.payload)
    case m@GetChatMember(payload)         ⇒ go(m.name, m.payload)
    case m@AnswerCallbackQuery(payload)   ⇒ go(m.name, m.payload)
  }
}
