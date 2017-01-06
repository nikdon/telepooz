/*
 * Copyright 2016 Nikolay Donets
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.nikdon.telepooz.raw

import java.time.Duration
import java.util.Date

import com.github.nikdon.telepooz.IsResourceId
import com.github.nikdon.telepooz.model._
import com.github.nikdon.telepooz.model.inline._
import com.github.nikdon.telepooz.tags._
import com.github.nikdon.telepooz.tags.syntax._
import com.github.nikdon.telepooz.utils._
import io.circe.Decoder
import io.circe.generic.auto._
import io.circe.generic.semiauto._
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
  implicit val dateDecoder: Decoder[Date] = Decoder[Long].map(d ⇒ new Date(d))                          // TODO Check
  implicit val durationDecoder: Decoder[Duration] = Decoder[Int].map(d ⇒ Duration.ofSeconds(d.toLong))  // TODO Check

  implicit def audioDecoder(implicit D: Decoder[String @@ FileId]): Decoder[Audio] = deriveDecoder[Audio]

  implicit def chatTypeDecoder: Decoder[ChatType] = Decoder[String].map(a ⇒ ChatType.unsafe( pascalize(a) ))
  implicit def chatDecoder(implicit D: Decoder[Long @@ ChatId]): Decoder[Chat] = deriveDecoder[Chat]

  implicit def contactDecoder(implicit D: Decoder[Int @@ UserId]): Decoder[Contact] = deriveDecoder[Contact]
  implicit def documentDecoder(implicit D: Decoder[String @@ FileId]): Decoder[Document] = deriveDecoder[Document]
  implicit def fileDecoder(implicit D: Decoder[String @@ FileId]): Decoder[File] = deriveDecoder[File]
  implicit val inlineKeyboardButtonDecoder: Decoder[InlineKeyboardButton] = deriveDecoder[InlineKeyboardButton]
  implicit val keyboardButtonDecoder: Decoder[KeyboardButton] = deriveDecoder[KeyboardButton]
  implicit val locationDecoder: Decoder[Location] = deriveDecoder[Location]

  implicit val messageEntityTypeDecoder: Decoder[MessageEntityType] = Decoder[String].map(a ⇒ MessageEntityType.unsafe( pascalize(a) ))
  implicit val messageEntityDecoder: Decoder[MessageEntity] = deriveDecoder[MessageEntity]

  implicit val webhookInfoDecoder: Decoder[WebhookInfo] = deriveDecoder[WebhookInfo]

  implicit val parseModeDecoder: Decoder[ParseMode] = Decoder[String].map(ParseMode.unsafe)

  implicit def photoSizeDecoder(implicit D: Decoder[String @@ FileId]): Decoder[PhotoSize] = deriveDecoder[PhotoSize]

  implicit val replyMarkupDecoder: Decoder[ReplyMarkup] = deriveDecoder[ReplyMarkup]

  implicit def stickerDecoder(implicit D: Decoder[String @@ FileId]): Decoder[Sticker] = deriveDecoder[Sticker]
  implicit def userDecoder(implicit D: Decoder[Int @@ UserId]): Decoder[User] = deriveDecoder[User]
  implicit val userProfilePhotosDecoder: Decoder[UserProfilePhotos] = deriveDecoder[UserProfilePhotos]
  implicit def venueDecoder(implicit D: Decoder[String @@ FoursquareId]): Decoder[Venue] = deriveDecoder[Venue]
  implicit def videoDecoder(implicit D: Decoder[String @@ FileId]): Decoder[Video] = deriveDecoder[Video]
  implicit def voiceDecoder(implicit D: Decoder[String @@ FileId]): Decoder[Voice] = deriveDecoder[Voice]

  // Game
  implicit val gameHighScoreDecoder: Decoder[GameHighScore] = deriveDecoder[GameHighScore]
  implicit def animationDecoder(implicit D: Decoder[String @@ FileId]): Decoder[Animation] = deriveDecoder[Animation]
  implicit val gameDecoder: Decoder[Game] = deriveDecoder[Game]

  implicit def messageDecoder(implicit D: Decoder[Long @@ MessageId], DD: Decoder[Long @@ ChatId], DDD: Decoder[String @@ FileId]): Decoder[Message] = deriveDecoder[Message]
  implicit def callbackQueryDecoder(implicit D: Decoder[String @@ QueryId]): Decoder[CallbackQuery] = deriveDecoder[CallbackQuery]

  implicit val memberStatusDecoder: Decoder[MemberStatus] = Decoder[String].map(a ⇒ MemberStatus.unsafe( pascalize(a) ))
  implicit val chatMemberDecoder: Decoder[ChatMember] = deriveDecoder[ChatMember]

  // Inline
  implicit def inlineQueryDecoder(implicit D: Decoder[String @@ QueryId]): Decoder[InlineQuery] = deriveDecoder[InlineQuery]
  implicit def chosenInlineQueryDecoder(implicit D: Decoder[String @@ ResultId], DD: Decoder[String @@ MessageId]): Decoder[ChosenInlineQuery] = deriveDecoder[ChosenInlineQuery]
  implicit val inputContactMessageContent: Decoder[InputContactMessageContent] = deriveDecoder[InputContactMessageContent]
  implicit def inputVenueMessageContentDecoder(implicit D: Decoder[String @@ FoursquareId]): Decoder[InputVenueMessageContent] = deriveDecoder[InputVenueMessageContent]
  implicit val inputLocationMessageContentDecoder: Decoder[InputLocationMessageContent] = deriveDecoder[InputLocationMessageContent]
  implicit val inputTextMessageContentDecoder: Decoder[InputTextMessageContent] = deriveDecoder[InputTextMessageContent]

  implicit def eitherResponseDecoder[A, B](implicit D: Decoder[A], DD: Decoder[B]): Decoder[Either[A, B]] = deriveDecoder[Either[A, B]]

  implicit def updateDecoder(implicit D: Decoder[Long @@ UpdateId]): Decoder[Update] = deriveDecoder[Update]
  implicit def responseDecoder[T](implicit D: Decoder[T]): Decoder[Response[T]] = deriveDecoder[Response[T]]
}
