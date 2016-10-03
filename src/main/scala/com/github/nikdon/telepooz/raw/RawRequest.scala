package com.github.nikdon.telepooz.raw

import com.github.nikdon.telepooz.model._
import io.circe.Json


sealed trait RawRequest[Result] extends Product with Serializable {
  def name: String = this.productPrefix
  def payload: Json
}

object RawRequest {

  def apply[A](implicit ev: RawRequest[A]): RawRequest[A] = ev

  case object GetMe extends RawRequest[Response[User]] {override val payload = Json.Null}
  case class SendMessage(payload: Json) extends RawRequest[Response[Message]]
  case class ForwardMessage(payload: Json) extends RawRequest[Response[Message]]
  case class GetUpdates(payload: Json) extends RawRequest[Response[Vector[Update]]]
  case class SendGame(payload: Json) extends RawRequest[Response[Message]]

  // TODO Add an ability to upload files as multipart form
  case class SendPhoto(payload: Json) extends RawRequest[Response[Message]]
  case class SendAudio(payload: Json) extends RawRequest[Response[Message]]
  case class SendDocument(payload: Json) extends RawRequest[Response[Message]]
  case class SendSticker(payload: Json) extends RawRequest[Response[Message]]
  case class SendVideo(payload: Json) extends RawRequest[Response[Message]]
  case class SendVoice(payload: Json) extends RawRequest[Response[Message]]

  case class SendLocation(payload: Json) extends RawRequest[Response[Message]]
  case class SendVenue(payload: Json) extends RawRequest[Response[Message]]
  case class SendContact(payload: Json) extends RawRequest[Response[Message]]
  case class SendChatAction(payload: Json) extends RawRequest[Response[Boolean]]
  case class GetUserProfilePhotos(payload: Json) extends RawRequest[Response[UserProfilePhotos]]
  case class GetFile(payload: Json) extends RawRequest[Response[File]]
  case class KickChatMember(payload: Json) extends RawRequest[Response[Boolean]]
  case class LeaveChat(payload: Json) extends RawRequest[Response[Boolean]]
  case class UnbanChatMember(payload: Json) extends RawRequest[Response[Boolean]]
  case class GetChat(payload: Json) extends RawRequest[Response[Chat]]
  case class GetChatAdministrators(payload: Json) extends RawRequest[Response[Vector[ChatMember]]]
  case class GetChatMembersCount(payload: Json) extends RawRequest[Response[Int]]
  case class GetChatMember(payload: Json) extends RawRequest[Response[ChatMember]]
  case class AnswerCallbackQuery(payload: Json) extends RawRequest[Response[Boolean]]

  case class EditMessageText(payload: Json) extends RawRequest[Response[Either[Boolean, Message]]]
  case class EditMessageCaption(payload: Json) extends RawRequest[Response[Either[Boolean, Message]]]
  case class EditMessageReplyMarkup(payload: Json) extends RawRequest[Response[Either[Boolean, Message]]]

  case class SetWebhook(payload: Json) extends RawRequest[Response[Boolean]]
}
