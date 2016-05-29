package com.github.nikdon.raw

import com.github.nikdon.IsResourceId
import com.github.nikdon.dto.methods._
import com.github.nikdon.dto.{ForceReply, InlineKeyboardMarkup, ReplyKeyboardMarkup, ReplyMarkup}
import io.circe.Encoder
import io.circe.generic.auto._
import io.circe.syntax._
import shapeless.{Generic, HNil}


trait CirceEncoders {
  private[this] def encodeCaseObject[A <: Product](implicit gen: Generic.Aux[A, HNil]): Encoder[A] =
    Encoder[String].contramap[A](_.productPrefix)

  implicit val replyMarkupJsonEncoder: Encoder[ReplyMarkup] = Encoder instance {
    case x: ReplyKeyboardMarkup  ⇒ x.asJson
    case x: InlineKeyboardMarkup ⇒ x.asJson
    case x: ForceReply           ⇒ x.asJson
  }

  implicit val getMeJsonEncoder: Encoder[GetMe.type] = encodeCaseObject[GetMe.type]
  implicit def sendMessageJsonEncoder[A: IsResourceId : Encoder] = Encoder[SendMessage[A]]
  implicit def forwardMessageJsonEncoder[A: IsResourceId : Encoder] = Encoder[ForwardMessage[A]]
}
