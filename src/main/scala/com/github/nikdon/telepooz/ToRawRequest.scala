package com.github.nikdon.telepooz

import com.github.nikdon.telepooz.model.methods
import com.github.nikdon.telepooz.raw.{CirceEncoders, RawRequest}
import com.github.nikdon.telepooz.tags.{ChatId, FileId, MessageId, UpdateId, UserId}
import io.circe.Encoder
import io.circe.syntax._
import shapeless.tag.@@


@scala.annotation.implicitNotFound("No member of type class ToRaw in scope for ${Model} and ${RawRequest}")
trait ToRawRequest[Model, RawRequest] extends Produce[Model, RawRequest]

object ToRawRequest extends CirceEncoders {
  def apply[Model, RawRequest](f: Model ⇒ RawRequest): ToRawRequest[Model, RawRequest] =
    new ToRawRequest[Model, RawRequest] {
      override def produce(a: Model): RawRequest = f(a)
    }

  object syntax {

    implicit class ToRawCommandSyntaxOps[Model, RawRequest](a: Model)(implicit F: ToRawRequest[Model, RawRequest]) {
      def toRawRequest: RawRequest = F.produce(a)
    }

  }

  implicit val getMe: ToRawRequest[methods.GetMe.type, RawRequest.GetMe.type] =
    ToRawRequest(m ⇒ RawRequest.GetMe)

  implicit def sendMessage[A: IsResourceId](implicit E: Encoder[A @@ ChatId]): ToRawRequest[methods.SendMessage[A], RawRequest.SendMessage] =
    ToRawRequest(m ⇒ RawRequest.SendMessage(m.asJson))

  implicit def forwardMessage[A: IsResourceId, B: IsResourceId](implicit E: Encoder[A @@ ChatId], EE: Encoder[B @@ ChatId], EEE: Encoder[Long @@ MessageId]): ToRawRequest[methods.ForwardMessage[A, B], RawRequest.ForwardMessage] =
    ToRawRequest(m ⇒ RawRequest.ForwardMessage(m.asJson))

  implicit def getUpdates(implicit E: Encoder[Long @@ UpdateId]): ToRawRequest[methods.GetUpdates, RawRequest.GetUpdates] =
    ToRawRequest(m ⇒ RawRequest.GetUpdates(m.asJson))

  implicit def sendPhoto[A: IsResourceId](implicit E: Encoder[A @@ ChatId], EE: Encoder[Long @@ MessageId], EEE: Encoder[String @@ FileId]): ToRawRequest[methods.SendPhoto[A], RawRequest.SendPhoto] =
    ToRawRequest(m ⇒ RawRequest.SendPhoto(m.asJson))

  implicit def sendAudio[A: IsResourceId](implicit E: Encoder[A @@ ChatId], EE: Encoder[Long @@ MessageId], EEE: Encoder[String @@ FileId]): ToRawRequest[methods.SendAudio[A], RawRequest.SendAudio] =
    ToRawRequest(m ⇒ RawRequest.SendAudio(m.asJson))

  implicit def sendDocument[A: IsResourceId](implicit E: Encoder[A @@ ChatId], EE: Encoder[Long @@ MessageId], EEE: Encoder[String @@ FileId]): ToRawRequest[methods.SendDocument[A], RawRequest.SendDocument] =
    ToRawRequest(m ⇒ RawRequest.SendDocument(m.asJson))

  implicit def sendSticker[A: IsResourceId](implicit E: Encoder[A @@ ChatId], EE: Encoder[Long @@ MessageId], EEE: Encoder[String @@ FileId]): ToRawRequest[methods.SendSticker[A], RawRequest.SendSticker] =
    ToRawRequest(m ⇒ RawRequest.SendSticker(m.asJson))

  implicit def sendVideo[A: IsResourceId](implicit E: Encoder[A @@ ChatId], EE: Encoder[Long @@ MessageId], EEE: Encoder[String @@ FileId]): ToRawRequest[methods.SendVideo[A], RawRequest.SendVideo] =
    ToRawRequest(m ⇒ RawRequest.SendVideo(m.asJson))

  implicit def sendVoice[A: IsResourceId](implicit E: Encoder[A @@ ChatId], EE: Encoder[Long @@ MessageId], EEE: Encoder[String @@ FileId]): ToRawRequest[methods.SendVoice[A], RawRequest.SendVoice] =
    ToRawRequest(m ⇒ RawRequest.SendVoice(m.asJson))

  implicit def sendLocation[A: IsResourceId](implicit E: Encoder[A @@ ChatId], EE: Encoder[Long @@ MessageId]): ToRawRequest[methods.SendLocation[A], RawRequest.SendLocation] =
    ToRawRequest(m ⇒ RawRequest.SendLocation(m.asJson))

  implicit def sendVenue[A: IsResourceId](implicit E: Encoder[A @@ ChatId], EE: Encoder[Long @@ MessageId]): ToRawRequest[methods.SendVenue[A], RawRequest.SendVenue] =
    ToRawRequest(m ⇒ RawRequest.SendVenue(m.asJson))

  implicit def sendContact[A: IsResourceId](implicit E: Encoder[A @@ ChatId], EE: Encoder[Long @@ MessageId]): ToRawRequest[methods.SendContact[A], RawRequest.SendContact] =
    ToRawRequest(m ⇒ RawRequest.SendContact(m.asJson))

  implicit def sendChatAction[A: IsResourceId](implicit E: Encoder[A @@ ChatId]): ToRawRequest[methods.SendChatAction[A], RawRequest.SendChatAction] =
    ToRawRequest(m ⇒ RawRequest.SendChatAction(m.asJson))

  implicit def getUserProfilePhotos(implicit E: Encoder[Int @@ UserId]): ToRawRequest[methods.GetUserProfilePhotos, RawRequest.GetUserProfilePhotos] =
    ToRawRequest(m ⇒ RawRequest.GetUserProfilePhotos(m.asJson))

  implicit def getFile(implicit E: Encoder[String @@ FileId]): ToRawRequest[methods.GetFile, RawRequest.GetFile] =
    ToRawRequest(m ⇒ RawRequest.GetFile(m.asJson))

  implicit def kickChatMember[A: IsResourceId](implicit E: Encoder[A @@ ChatId], EE: Encoder[Int @@ UserId]): ToRawRequest[methods.KickChatMember[A], RawRequest.KickChatMember] =
    ToRawRequest(m ⇒ RawRequest.KickChatMember(m.asJson))

  implicit def leaveChat[A: IsResourceId](implicit E: Encoder[A @@ ChatId]): ToRawRequest[methods.LeaveChat[A], RawRequest.LeaveChat] =
    ToRawRequest(m ⇒ RawRequest.LeaveChat(m.asJson))

  implicit def unbanChatMember[A: IsResourceId](implicit E: Encoder[A @@ ChatId], EE: Encoder[Int @@ UserId]): ToRawRequest[methods.UnbanChatMember[A], RawRequest.UnbanChatMember] =
    ToRawRequest(m ⇒ RawRequest.UnbanChatMember(m.asJson))

  implicit def getChat[A: IsResourceId](implicit E: Encoder[A @@ ChatId]): ToRawRequest[methods.GetChat[A], RawRequest.GetChat] =
    ToRawRequest(m ⇒ RawRequest.GetChat(m.asJson))

  implicit def getChatAdministrators[A: IsResourceId](implicit E: Encoder[A @@ ChatId]): ToRawRequest[methods.GetChatAdministrators[A], RawRequest.GetChatAdministrators] =
    ToRawRequest(m ⇒ RawRequest.GetChatAdministrators(m.asJson))

  implicit def getChatMembersCount[A: IsResourceId](implicit E: Encoder[A @@ ChatId]): ToRawRequest[methods.GetChatMembersCount[A], RawRequest.GetChatMembersCount] =
    ToRawRequest(m ⇒ RawRequest.GetChatMembersCount(m.asJson))

  implicit def getChatMember[A: IsResourceId](implicit E: Encoder[A @@ ChatId], EE: Encoder[Int @@ UserId]): ToRawRequest[methods.GetChatMember[A], RawRequest.GetChatMember] =
    ToRawRequest(m ⇒ RawRequest.GetChatMember(m.asJson))

  implicit def answerCallbackQuery(implicit E: Encoder[String @@ UserId]): ToRawRequest[methods.AnswerCallbackQuery, RawRequest.AnswerCallbackQuery] =
    ToRawRequest(m ⇒ RawRequest.AnswerCallbackQuery(m.asJson))

  implicit def editMessageText[A: IsResourceId](implicit E: Encoder[A @@ ChatId]): ToRawRequest[methods.EditMessageText[A], RawRequest.EditMessageText] =
    ToRawRequest(m ⇒ RawRequest.EditMessageText(m.asJson))

  implicit def editMessageCaption[A: IsResourceId](implicit E: Encoder[A @@ ChatId]): ToRawRequest[methods.EditMessageCaption[A], RawRequest.EditMessageCaption] =
    ToRawRequest(m ⇒ RawRequest.EditMessageCaption(m.asJson))

  implicit def editMessageReplyMarkup[A: IsResourceId](implicit E: Encoder[A @@ ChatId]): ToRawRequest[methods.EditMessageReplyMarkup[A], RawRequest.EditMessageReplyMarkup] =
    ToRawRequest(m ⇒ RawRequest.EditMessageReplyMarkup(m.asJson))

  implicit val setWebhook: ToRawRequest[methods.SetWebhook, RawRequest.SetWebhook] =
    ToRawRequest(m ⇒ RawRequest.SetWebhook(m.asJson))

  implicit def sendGame[A: IsResourceId](implicit E: Encoder[A @@ ChatId], EE: Encoder[Long @@ MessageId]): ToRawRequest[methods.SendGame[A], RawRequest.SendGame] =
    ToRawRequest(m ⇒ RawRequest.SendGame(m.asJson))

  implicit def setGameScore[A: IsResourceId](implicit E: Encoder[A @@ ChatId], EE: Encoder[Long @@ MessageId], EEE: Encoder[String @@ MessageId], EEEE: Encoder[Long @@ UserId]): ToRawRequest[methods.SetGameScore[A], RawRequest.SetGameScore] =
    ToRawRequest(m ⇒ RawRequest.SetGameScore(m.asJson))

  implicit def getGameHighScores[A: IsResourceId](implicit E: Encoder[A @@ ChatId], EE: Encoder[Long @@ MessageId], EEE: Encoder[String @@ MessageId], EEEE: Encoder[Long @@ UserId]): ToRawRequest[methods.GetGameHighScores[A], RawRequest.GetGameHighScores] =
    ToRawRequest(m ⇒ RawRequest.GetGameHighScores(m.asJson))
}
