package com.github.nikdon.telepooz.raw

import java.time.Duration
import java.util.Date

import com.github.nikdon.telepooz.IsResourceId
import com.github.nikdon.telepooz.model._
import com.github.nikdon.telepooz.tags._
import com.github.nikdon.telepooz.utils._
import io.circe.Encoder
import io.circe.generic.auto._
import shapeless.tag._
import shapeless.{Generic, HNil}



trait CirceEncoders {
  private[this] def encodeCaseObject[A <: Product](implicit gen: Generic.Aux[A, HNil]): Encoder[A] =
    Encoder[String].contramap[A](_.productPrefix)

//  implicit val getMeJsonEncoder: Encoder[GetMe.type] = encodeCaseObject[GetMe.type]
//  implicit def sendMessageJsonEncoder[A: IsResourceId : Encoder] = Encoder[SendMessage[A]]
//  implicit def forwardMessageJsonEncoder[A: IsResourceId : Encoder] = Encoder[ForwardMessage[A]]

  implicit def chatIdTagEncoder[A : IsResourceId : Encoder]: Encoder[A @@ ChatId] = Encoder[A].contramap[A @@ ChatId](identity)
  implicit def fileIdTagEncoder[A : IsResourceId : Encoder]: Encoder[A @@ FileId] = Encoder[A].contramap[A @@ FileId](identity)
  implicit def foursquareIdTagEncoder[A : IsResourceId : Encoder]: Encoder[A @@ FoursquareId] = Encoder[A].contramap[A @@ FoursquareId](identity)
  implicit def messageIdTagEncoder[A : IsResourceId : Encoder]: Encoder[A @@ MessageId] = Encoder[A].contramap[A @@ MessageId](identity)
  implicit def userIdTagEncoder[A : IsResourceId : Encoder]: Encoder[A @@ UserId] = Encoder[A].contramap[A @@ UserId](identity)
  implicit def queryIdTagEncoder[A : IsResourceId : Encoder]: Encoder[A @@ QueryId] = Encoder[A].contramap[A @@ QueryId](identity)

  implicit val dateEncoder: Encoder[Date] = Encoder[Int].contramap[Date](d ⇒ (d.getTime / 1000).toInt)
  implicit val durationEncoder: Encoder[Duration] = Encoder[Int].contramap[Duration](d ⇒ d.getSeconds.toInt)

  implicit def audioEncoder(implicit E: Encoder[String @@ FileId]) = Encoder[Audio]

  implicit val chatTypeEncoder = Encoder[String].contramap[ChatType](_.productPrefix)
  implicit def chatEncoder(implicit E: Encoder[Int @@ ChatId]) = Encoder[Chat]

  implicit def contactEncoder(implicit E: Encoder[Int @@ UserId]) = Encoder[Contact]
  implicit def documentEncoder(implicit E: Encoder[String @@ FileId]) = Encoder[Document]
  implicit def fileEncoder(implicit E: Encoder[String @@ FileId]) = Encoder[File]
  implicit val inlineKeyboardButtonEncoder = Encoder[InlineKeyboardButton]
  implicit val keyboardButtonEncoder = Encoder[KeyboardButton]
  implicit val locationEncoder = Encoder[Location]

  implicit val messageEntityTypeEncoder = Encoder[String].contramap[MessageEntityType](e ⇒ snakenize { e.productPrefix })
  implicit val messageEntityEncoder = Encoder[MessageEntity]

  implicit def photoSizeEncoder(implicit E: Encoder[String @@ FileId]) = Encoder[PhotoSize]

  implicit val forceReplyEncoder = Encoder[ForceReply]
  implicit val inlineKeyboardMarkupEncoder = Encoder[InlineKeyboardMarkup]
  implicit val replyKeyboardMarkupEncoder = Encoder[ReplyKeyboardMarkup]

  implicit def stickerEncoder(implicit E: Encoder[String @@ FileId]) = Encoder[Sticker]
  implicit def userEncoder(implicit E: Encoder[Int @@ UserId]) = Encoder[User]
  implicit val userProfilePhotosEncoder = Encoder[UserProfilePhotos]
  implicit def venueEncoder(implicit E: Encoder[String @@ FoursquareId]) = Encoder[Venue]
  implicit def videoEncoder(implicit E: Encoder[String @@ FileId]) = Encoder[Video]
  implicit def voiceEncoder(implicit E: Encoder[String @@ FileId]) = Encoder[Voice]

  implicit def messageEncoder(implicit E: Encoder[Int @@ MessageId], EE: Encoder[Int @@ ChatId]) = Encoder[Message]

}
