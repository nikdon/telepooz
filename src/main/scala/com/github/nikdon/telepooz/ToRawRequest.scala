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

  implicit val getMeToRawRequest: ToRawRequest[methods.GetMe.type, RawRequest.GetMe.type] =
    ToRawRequest(m ⇒ RawRequest.GetMe)

  implicit def sendMessageToRawRequest[A: IsResourceId](implicit E: Encoder[A @@ ChatId], EE: Encoder[Long @@ MessageId])
  : ToRawRequest[methods.SendMessage[A], RawRequest.SendMessage] =
    ToRawRequest(m ⇒ RawRequest.SendMessage(m.asJson))

  implicit def forwardMessageToRawRequest[A: IsResourceId, B: IsResourceId](implicit E: Encoder[A @@ ChatId],
                                                                            EE: Encoder[B @@ ChatId],
                                                                            EEE: Encoder[Long @@ MessageId])
  : ToRawRequest[methods.ForwardMessage[A, B], RawRequest.ForwardMessage] =
    ToRawRequest(m ⇒ RawRequest.ForwardMessage(m.asJson))

  implicit def getUpdatesToRawRequest(implicit E: Encoder[Long @@ UpdateId]): ToRawRequest[methods.GetUpdates, RawRequest.GetUpdates] =
    ToRawRequest(m ⇒ RawRequest.GetUpdates(m.asJson))

  implicit def sendPhotoToRawRequest[A: IsResourceId](implicit E: Encoder[A @@ ChatId],
                                                      EE: Encoder[Long @@ MessageId],
                                                      EEE: Encoder[String @@ FileId]): ToRawRequest[methods.SendPhoto[A], RawRequest.SendPhoto] =
    ToRawRequest(m ⇒ RawRequest.SendPhoto(m.asJson))

  implicit def sendAudioToRawRequest[A: IsResourceId](implicit E: Encoder[A @@ ChatId],
                                                      EE: Encoder[Long @@ MessageId],
                                                      EEE: Encoder[String @@ FileId]): ToRawRequest[methods.SendAudio[A], RawRequest.SendAudio] =
    ToRawRequest(m ⇒ RawRequest.SendAudio(m.asJson))

  implicit def sendDocumentToRawRequest[A: IsResourceId](implicit E: Encoder[A @@ ChatId],
                                                         EE: Encoder[Long @@ MessageId],
                                                         EEE: Encoder[String @@ FileId]): ToRawRequest[methods.SendDocument[A], RawRequest.SendDocument] =
    ToRawRequest(m ⇒ RawRequest.SendDocument(m.asJson))

  implicit def sendStickerToRawRequest[A: IsResourceId](implicit E: Encoder[A @@ ChatId],
                                                        EE: Encoder[Long @@ MessageId],
                                                        EEE: Encoder[String @@ FileId]): ToRawRequest[methods.SendSticker[A], RawRequest.SendSticker] =
    ToRawRequest(m ⇒ RawRequest.SendSticker(m.asJson))

  implicit def sendVideoToRawRequest[A: IsResourceId](implicit E: Encoder[A @@ ChatId],
                                                      EE: Encoder[Long @@ MessageId],
                                                      EEE: Encoder[String @@ FileId]): ToRawRequest[methods.SendVideo[A], RawRequest.SendVideo] =
    ToRawRequest(m ⇒ RawRequest.SendVideo(m.asJson))

  implicit def sendVoiceToRawRequest[A: IsResourceId](implicit E: Encoder[A @@ ChatId],
                                                      EE: Encoder[Long @@ MessageId],
                                                      EEE: Encoder[String @@ FileId]): ToRawRequest[methods.SendVoice[A], RawRequest.SendVoice] =
    ToRawRequest(m ⇒ RawRequest.SendVoice(m.asJson))

  implicit def sendLocationToRawRequest[A: IsResourceId](implicit E: Encoder[A @@ ChatId],
                                                         EE: Encoder[Long @@ MessageId]): ToRawRequest[methods.SendLocation[A], RawRequest.SendLocation] =
    ToRawRequest(m ⇒ RawRequest.SendLocation(m.asJson))

  implicit def sendVenueToRawRequest[A: IsResourceId](implicit E: Encoder[A @@ ChatId],
                                                      EE: Encoder[Long @@ MessageId]): ToRawRequest[methods.SendVenue[A], RawRequest.SendVenue] =
    ToRawRequest(m ⇒ RawRequest.SendVenue(m.asJson))

  implicit def sendContactToRawRequest[A: IsResourceId](implicit E: Encoder[A @@ ChatId],
                                                        EE: Encoder[Long @@ MessageId]): ToRawRequest[methods.SendContact[A], RawRequest.SendContact] =
    ToRawRequest(m ⇒ RawRequest.SendContact(m.asJson))

  implicit def sendChatActionToRawRequest[A: IsResourceId](implicit E: Encoder[A @@ ChatId]): ToRawRequest[methods.SendChatAction[A], RawRequest.SendChatAction] =
    ToRawRequest(m ⇒ RawRequest.SendChatAction(m.asJson))

  implicit def getUserProfilePhotosToRawRequest(implicit E: Encoder[Int @@ UserId]): ToRawRequest[methods.GetUserProfilePhotos, RawRequest.GetUserProfilePhotos] =
    ToRawRequest(m ⇒ RawRequest.GetUserProfilePhotos(m.asJson))
}
