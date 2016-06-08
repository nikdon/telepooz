package com.github.nikdon.telepooz.raw

import java.time.Duration
import java.util.Date

import com.github.nikdon.telepooz.IsResourceId
import com.github.nikdon.telepooz.model._
import com.github.nikdon.telepooz.model.inline.{ChosenInlineQuery, InlineQuery}
import com.github.nikdon.telepooz.tags._
import com.github.nikdon.telepooz.tags.syntax._
import com.github.nikdon.telepooz.utils._
import io.circe.Decoder
import io.circe.generic.auto._
import shapeless.tag._



trait CirceDecoders {
  // Tags
  implicit def chatIdTagDecoder[A : IsResourceId : Decoder]: Decoder[A @@ ChatId] = Decoder[A].map(a ⇒ a.chatId)
  implicit def fileIdTagDecoder[A : IsResourceId : Decoder]: Decoder[A @@ FileId] = Decoder[A].map(a ⇒ a.fileId)
  implicit def foursquareIdTagDecoder[A : IsResourceId : Decoder]: Decoder[A @@ FoursquareId] = Decoder[A].map(a ⇒ a.foursquareId)
  implicit def messageIdTagDecoder[A : IsResourceId : Decoder]: Decoder[A @@ MessageId] = Decoder[A].map(a ⇒ a.messageId)
  implicit def resultIdDecoder[A : IsResourceId : Decoder]: Decoder[A @@ ResultId] = Decoder[A].map(a ⇒ a.resultId)
  implicit def updateIdTagDecoder[A : IsResourceId : Decoder]: Decoder[A @@ UpdateId] = Decoder[A].map(a ⇒ a.updateId)
  implicit def userIdTagDecoder[A : IsResourceId : Decoder]: Decoder[A @@ UserId] = Decoder[A].map(a ⇒ a.userId)
  implicit def queryIdTagDecoder[A : IsResourceId : Decoder]: Decoder[A @@ QueryId] = Decoder[A].map(a ⇒ a.queryId)

  // Models
  private implicit val dateDecoder: Decoder[Date] = Decoder[Long].map(d ⇒ new Date(d))                          // TODO Check
  private implicit val durationDecoder: Decoder[Duration] = Decoder[Int].map(d ⇒ Duration.ofSeconds(d.toLong))  // TODO Check

  implicit def audioDecoder(implicit D: Decoder[String @@ FileId]) = Decoder[Audio]

  implicit def chatTypeDecoder: Decoder[ChatType] = Decoder[String].map(a ⇒ ChatType.unsafe( pascalize(a) ))
  implicit def chatDecoder(implicit D: Decoder[Long @@ ChatId]) = Decoder[Chat]

  implicit def contactDecoder(implicit D: Decoder[Int @@ UserId]) = Decoder[Contact]
  implicit def documentDecoder(implicit D: Decoder[String @@ FileId]) = Decoder[Document]
  implicit def fileDecoder(implicit D: Decoder[String @@ FileId]) = Decoder[File]
  implicit val inlineKeyboardButtonDecoder = Decoder[InlineKeyboardButton]
  implicit val keyboardButtonDecoder = Decoder[KeyboardButton]
  implicit val locationDecoder = Decoder[Location]

  implicit val messageEntityTypeDecoder: Decoder[MessageEntityType] = Decoder[String].map(a ⇒ MessageEntityType.unsafe( pascalize(a) ))
  implicit val messageEntityDecoder = Decoder[MessageEntity]

  implicit val parseModeDecoder: Decoder[ParseMode] = Decoder[String].map(ParseMode.unsafe)

  implicit def photoSizeDecoder(implicit D: Decoder[String @@ FileId]) = Decoder[PhotoSize]

  implicit val forceReplyDecoder           = Decoder[ForceReply]
  implicit val inlineKeyboardMarkupDecoder = Decoder[InlineKeyboardMarkup]
  implicit val replyKeyboardHideDecoder    = Decoder[ReplyKeyboardHide]
  implicit val replyKeyboardMarkupDecoder  = Decoder[ReplyKeyboardMarkup]

  implicit def stickerDecoder(implicit D: Decoder[String @@ FileId]) = Decoder[Sticker]
  implicit def userDecoder(implicit D: Decoder[Int @@ UserId]) = Decoder[User]
  implicit val userProfilePhotosDecoder = Decoder[UserProfilePhotos]
  implicit def venueDecoder(implicit D: Decoder[String @@ FoursquareId]) = Decoder[Venue]
  implicit def videoDecoder(implicit D: Decoder[String @@ FileId]) = Decoder[Video]
  implicit def voiceDecoder(implicit D: Decoder[String @@ FileId]) = Decoder[Voice]

  implicit def messageDecoder(implicit D: Decoder[Int @@ MessageId], DD: Decoder[Long @@ ChatId]) = Decoder[Message]
  implicit def callbackQueryDecoder(implicit D: Decoder[String @@ QueryId]) = Decoder[CallbackQuery]

  implicit def inlineQueryDecoder(implicit D: Decoder[String @@ QueryId]) = Decoder[InlineQuery]
  implicit def chosenInlineQueryDecoder(implicit D: Decoder[String @@ ResultId], DD: Decoder[String @@ MessageId]) = Decoder[ChosenInlineQuery]

  implicit def updateDecoder(implicit D: Decoder[Int @@ UpdateId]) = Decoder[Update]

  implicit def responseDecoder[T](implicit D: Decoder[T]) = Decoder[Response[T]]

}
