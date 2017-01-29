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

package com.github.nikdon.telepooz.json

import java.time.Duration
import java.util.Date

import com.github.nikdon.telepooz.model._
import com.github.nikdon.telepooz.model.inline._
import com.github.nikdon.telepooz.utils._
import io.circe.Decoder
import io.circe.generic.semiauto._

trait CirceDecoders {
  
  // Models
  implicit val dateDecoder: Decoder[Date] = Decoder[Long].map(d ⇒ new Date(d))                          // TODO Check
  implicit val durationDecoder: Decoder[Duration] = Decoder[Int].map(d ⇒ Duration.ofSeconds(d.toLong))  // TODO Check

  implicit val audioDecoder: Decoder[Audio] = deriveDecoder[Audio]

  implicit val chatTypeDecoder: Decoder[ChatType] = Decoder[String].map(a ⇒ ChatType.unsafe( pascalize(a) ))
  implicit val chatDecoder: Decoder[Chat] = deriveDecoder[Chat]

  implicit val contactDecoder: Decoder[Contact] = deriveDecoder[Contact]
  implicit val documentDecoder: Decoder[Document] = deriveDecoder[Document]
  implicit val fileDecoder: Decoder[File] = deriveDecoder[File]
  implicit val callbackGameDecoder: Decoder[CallbackGame] = deriveDecoder[CallbackGame]
  implicit val inlineKeyboardButtonDecoder: Decoder[InlineKeyboardButton] = deriveDecoder[InlineKeyboardButton]
  implicit val keyboardButtonDecoder: Decoder[KeyboardButton] = deriveDecoder[KeyboardButton]
  implicit val locationDecoder: Decoder[Location] = deriveDecoder[Location]

  implicit val messageEntityTypeDecoder: Decoder[MessageEntityType] = Decoder[String].map(a ⇒ MessageEntityType.unsafe( pascalize(a) ))
  implicit val messageEntityDecoder: Decoder[MessageEntity] = deriveDecoder[MessageEntity]

  implicit val webhookInfoDecoder: Decoder[WebhookInfo] = deriveDecoder[WebhookInfo]

  implicit val parseModeDecoder: Decoder[ParseMode] = Decoder[String].map(ParseMode.unsafe)

  implicit val photoSizeDecoder: Decoder[PhotoSize] = deriveDecoder[PhotoSize]

  implicit val replyMarkupDecoder: Decoder[ReplyMarkup] = deriveDecoder[ReplyMarkup]

  implicit val stickerDecoder: Decoder[Sticker] = deriveDecoder[Sticker]
  implicit val userDecoder: Decoder[User] = deriveDecoder[User]
  implicit val userProfilePhotosDecoder: Decoder[UserProfilePhotos] = deriveDecoder[UserProfilePhotos]
  implicit val venueDecoder: Decoder[Venue] = deriveDecoder[Venue]
  implicit val videoDecoder: Decoder[Video] = deriveDecoder[Video]
  implicit val voiceDecoder: Decoder[Voice] = deriveDecoder[Voice]

  // Game
  implicit val gameHighScoreDecoder: Decoder[GameHighScore] = deriveDecoder[GameHighScore]
  implicit val animationDecoder: Decoder[Animation] = deriveDecoder[Animation]
  implicit val gameDecoder: Decoder[Game] = deriveDecoder[Game]

  implicit val messageDecoder: Decoder[Message] = deriveDecoder[Message]
  implicit val callbackQueryDecoder: Decoder[CallbackQuery] = deriveDecoder[CallbackQuery]

  implicit val memberStatusDecoder: Decoder[MemberStatus] = Decoder[String].map(a ⇒ MemberStatus.unsafe( pascalize(a) ))
  implicit val chatMemberDecoder: Decoder[ChatMember] = deriveDecoder[ChatMember]

  // Inline
  implicit val inlineQueryDecoder: Decoder[InlineQuery] = deriveDecoder[InlineQuery]
  implicit val chosenInlineQueryDecoder: Decoder[ChosenInlineQuery] = deriveDecoder[ChosenInlineQuery]
  implicit val inputContactMessageContent: Decoder[InputContactMessageContent] = deriveDecoder[InputContactMessageContent]
  implicit val inputVenueMessageContentDecoder: Decoder[InputVenueMessageContent] = deriveDecoder[InputVenueMessageContent]
  implicit val inputLocationMessageContentDecoder: Decoder[InputLocationMessageContent] = deriveDecoder[InputLocationMessageContent]
  implicit val inputTextMessageContentDecoder: Decoder[InputTextMessageContent] = deriveDecoder[InputTextMessageContent]

  implicit def eitherResponseDecoder[A, B](implicit D: Decoder[A], DD: Decoder[B]): Decoder[Either[A, B]] = deriveDecoder[Either[A, B]]

  implicit val updateDecoder: Decoder[Update] = deriveDecoder[Update]
  implicit def responseDecoder[T](implicit D: Decoder[T]): Decoder[Response[T]] = deriveDecoder[Response[T]]
}
