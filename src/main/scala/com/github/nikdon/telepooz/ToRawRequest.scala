package com.github.nikdon.telepooz

import com.github.nikdon.telepooz.model.methods
import com.github.nikdon.telepooz.raw.{CirceEncoders, RawRequest}
import com.github.nikdon.telepooz.tags.{ChatId, FileId, MessageId, UpdateId}
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
}
