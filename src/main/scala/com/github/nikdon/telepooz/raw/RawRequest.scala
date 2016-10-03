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
  case object GetWebhookInfo extends RawRequest[Response[WebhookInfo]] {override val payload = Json.Null}
  final case class SendMessage(payload: Json) extends RawRequest[Response[Message]]
  final case class ForwardMessage(payload: Json) extends RawRequest[Response[Message]]
  final case class GetUpdates(payload: Json) extends RawRequest[Response[Vector[Update]]]
  final case class SendGame(payload: Json) extends RawRequest[Response[Message]]
  final case class SetGameScore(payload: Json) extends RawRequest[Response[Message]]
  final case class GetGameHighScores(payload: Json) extends RawRequest[Response[Vector[GameHighScore]]]

  // TODO Add an ability to upload files as multipart form
  final case class SendPhoto(payload: Json) extends RawRequest[Response[Message]]
  final case class SendAudio(payload: Json) extends RawRequest[Response[Message]]
  final case class SendDocument(payload: Json) extends RawRequest[Response[Message]]
  final case class SendSticker(payload: Json) extends RawRequest[Response[Message]]
  final case class SendVideo(payload: Json) extends RawRequest[Response[Message]]
  final case class SendVoice(payload: Json) extends RawRequest[Response[Message]]

  final case class SendLocation(payload: Json) extends RawRequest[Response[Message]]
  final case class SendVenue(payload: Json) extends RawRequest[Response[Message]]
  final case class SendContact(payload: Json) extends RawRequest[Response[Message]]
  final case class SendChatAction(payload: Json) extends RawRequest[Response[Boolean]]
  final case class GetUserProfilePhotos(payload: Json) extends RawRequest[Response[UserProfilePhotos]]
  final case class GetFile(payload: Json) extends RawRequest[Response[File]]
  final case class KickChatMember(payload: Json) extends RawRequest[Response[Boolean]]
  final case class LeaveChat(payload: Json) extends RawRequest[Response[Boolean]]
  final case class UnbanChatMember(payload: Json) extends RawRequest[Response[Boolean]]
  final case class GetChat(payload: Json) extends RawRequest[Response[Chat]]
  final case class GetChatAdministrators(payload: Json) extends RawRequest[Response[Vector[ChatMember]]]
  final case class GetChatMembersCount(payload: Json) extends RawRequest[Response[Int]]
  final case class GetChatMember(payload: Json) extends RawRequest[Response[ChatMember]]
  final case class AnswerCallbackQuery(payload: Json) extends RawRequest[Response[Boolean]]

  final case class EditMessageText(payload: Json) extends RawRequest[Response[Either[Boolean, Message]]]
  final case class EditMessageCaption(payload: Json) extends RawRequest[Response[Either[Boolean, Message]]]
  final case class EditMessageReplyMarkup(payload: Json) extends RawRequest[Response[Either[Boolean, Message]]]

  final case class SetWebhook(payload: Json) extends RawRequest[Response[Boolean]]
}
