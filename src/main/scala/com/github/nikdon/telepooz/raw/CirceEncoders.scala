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
import com.github.nikdon.telepooz.model.methods._
import com.github.nikdon.telepooz.tags._
import com.github.nikdon.telepooz.utils._
import io.circe.Encoder
import io.circe.syntax._
import io.circe.generic.auto._
import io.circe.generic.semiauto._
import shapeless.tag._

trait CirceEncoders {

  // Types
  implicit def chatIdTagEncoder[A : IsResourceId : Encoder]: Encoder[A @@ ChatId] = Encoder[A].contramap[A @@ ChatId](identity)
  implicit def fileIdTagEncoder[A : IsResourceId : Encoder]: Encoder[A @@ FileId] = Encoder[A].contramap[A @@ FileId](identity)
  implicit def foursquareIdTagEncoder[A : IsResourceId : Encoder]: Encoder[A @@ FoursquareId] = Encoder[A].contramap[A @@ FoursquareId](identity)
  implicit def messageIdTagEncoder[A : IsResourceId : Encoder]: Encoder[A @@ MessageId] = Encoder[A].contramap[A @@ MessageId](identity)
  implicit def resultIdTagEncoder[A : IsResourceId : Encoder]: Encoder[A @@ ResultId] = Encoder[A].contramap[A @@ ResultId](identity)
  implicit def updateIdTagEncoder[A : IsResourceId : Encoder]: Encoder[A @@ UpdateId] = Encoder[A].contramap[A @@ UpdateId](identity)
  implicit def userIdTagEncoder[A : IsResourceId : Encoder]: Encoder[A @@ UserId] = Encoder[A].contramap[A @@ UserId](identity)
  implicit def queryIdTagEncoder[A : IsResourceId : Encoder]: Encoder[A @@ QueryId] = Encoder[A].contramap[A @@ QueryId](identity)

  // Models
  implicit val dateEncoder: Encoder[Date] = Encoder[Long].contramap[Date](d ⇒ d.getTime)                      // TODO Check
  implicit val durationEncoder: Encoder[Duration] = Encoder[Int].contramap[Duration](d ⇒ d.getSeconds.toInt)  // TODO Check

  implicit def audioEncoder(implicit E: Encoder[String @@ FileId]): Encoder[Audio] = Encoder[Audio]
  implicit def callbackQueryEncoder(implicit E: Encoder[String @@ QueryId]): Encoder[CallbackQuery] = Encoder[CallbackQuery]

  implicit val callbackGameEncoder: Encoder[CallbackGame] = deriveEncoder[CallbackGame]

  implicit val chatTypeEncoder: Encoder[ChatType] = Encoder[String].contramap[ChatType](_.productPrefix)
  implicit def chatEncoder(implicit E: Encoder[Long @@ ChatId]): Encoder[Chat] = Encoder[Chat]
  implicit val memberStatusEncoder: Encoder[MemberStatus] = Encoder[String].contramap[MemberStatus](_.productPrefix)
  implicit val chatMemberEncoder: Encoder[ChatMember] = deriveEncoder[ChatMember]

  implicit def contactEncoder(implicit E: Encoder[Int @@ UserId]): Encoder[Contact] = deriveEncoder[Contact]
  implicit def documentEncoder(implicit E: Encoder[String @@ FileId]): Encoder[Document] = Encoder[Document]
  implicit def fileEncoder(implicit E: Encoder[String @@ FileId]): Encoder[File] = Encoder[File]

  implicit val inlineKeyboardButtonEncoder: Encoder[InlineKeyboardButton] = deriveEncoder[InlineKeyboardButton]
  implicit val keyboardButtonEncoder      : Encoder[KeyboardButton]       = deriveEncoder[KeyboardButton]
  implicit val locationEncoder            : Encoder[Location]             = deriveEncoder[Location]

  implicit val gameHighScoreEncoder: Encoder[GameHighScore] = deriveEncoder[GameHighScore]
  implicit def animationEncoder(implicit E: Encoder[String @@ FileId]): Encoder[Animation] = Encoder[Animation]
  implicit def gameEncoder: Encoder[Game] = Encoder[Game]

  implicit def messageEncoder(implicit E: Encoder[Long @@ MessageId], EE: Encoder[Long @@ ChatId], EEE: Encoder[String @@ FileId]): Encoder[Message] = Encoder[Message]

  implicit val messageEntityTypeEncoder: Encoder[MessageEntityType] = Encoder[String].contramap[MessageEntityType](e ⇒ snakenize {e.productPrefix})
  implicit val messageEntityEncoder    : Encoder[MessageEntity]     = deriveEncoder[MessageEntity]

  implicit def parseModeEncoder: Encoder[ParseMode] = Encoder[String].contramap[ParseMode](_.productPrefix)

  implicit def photoSizeEncoder(implicit E: Encoder[String @@ FileId]): Encoder[PhotoSize] = Encoder[PhotoSize]

  implicit val forceReplyEncoder          : Encoder[ForceReply]           = deriveEncoder[ForceReply]
  implicit val inlineKeyboardMarkupEncoder: Encoder[InlineKeyboardMarkup] = deriveEncoder[InlineKeyboardMarkup]
  implicit val replyKeyboardHideEncoder   : Encoder[ReplyKeyboardHide]    = deriveEncoder[ReplyKeyboardHide]
  implicit val replyKeyboardMarkupEncoder : Encoder[ReplyKeyboardMarkup]  = deriveEncoder[ReplyKeyboardMarkup]

  implicit def stickerEncoder(implicit E: Encoder[String @@ FileId]): Encoder[Sticker] = Encoder[Sticker]
  implicit def userEncoder(implicit E: Encoder[Int @@ UserId]): Encoder[User] = Encoder[User]
  implicit val userProfilePhotosEncoder: Encoder[UserProfilePhotos] = deriveEncoder[UserProfilePhotos]
  implicit def venueEncoder(implicit E: Encoder[String @@ FoursquareId]): Encoder[Venue] = deriveEncoder[Venue]
  implicit def videoEncoder(implicit E: Encoder[String @@ FileId]): Encoder[Video] = Encoder[Video]
  implicit def voiceEncoder(implicit E: Encoder[String @@ FileId]): Encoder[Voice] = Encoder[Voice]
  implicit def updateEncoder(implicit E: Encoder[Long @@ UpdateId]): Encoder[Update] = Encoder[Update]

  // Inline
  implicit def inlineQueryEncoder(implicit E: Encoder[String @@ QueryId]): Encoder[inline.InlineQuery] = Encoder[inline.InlineQuery]
  implicit def choosenInlineQueryEncoder(implicit E: Encoder[String @@ ResultId], EE: Encoder[String @@ MessageId]): Encoder[inline.ChosenInlineQuery] = Encoder[inline.ChosenInlineQuery]
  implicit val inputContactMessageContentEncoder: Encoder[inline.InputContactMessageContent] = deriveEncoder[inline.InputContactMessageContent]
  implicit def inputVenueMessageContent(implicit E: Encoder[String @@ FoursquareId]): Encoder[inline.InputVenueMessageContent] = deriveEncoder[inline.InputVenueMessageContent]
  implicit val inputLocationMessageContentEncoder: Encoder[inline.InputLocationMessageContent] = deriveEncoder[inline.InputLocationMessageContent]
  implicit val inputTextMessageContentEncoder: Encoder[inline.InputTextMessageContent] = deriveEncoder[inline.InputTextMessageContent]

  /** inline.InlineQueryResult */
  implicit def inlineQueryResultArticleEncoder(implicit E: Encoder[String @@ ResultId]): Encoder[inline.InlineQueryResultArticle] = Encoder[inline.InlineQueryResultArticle].mapJson(_.deepMerge(inline.InlineQueryResultArticle.`type`.asJson))
  implicit def inlineQueryResultPhotoEncoder(implicit E: Encoder[String @@ ResultId]): Encoder[inline.InlineQueryResultPhoto] = Encoder[inline.InlineQueryResultPhoto].mapJson(_.deepMerge(inline.InlineQueryResultPhoto.`type`.asJson))
  implicit def inlineQueryResultGifEncoder(implicit E: Encoder[String @@ ResultId]): Encoder[inline.InlineQueryResultGif] = Encoder[inline.InlineQueryResultGif].mapJson(_.deepMerge(inline.InlineQueryResultGif.`type`.asJson))
  implicit def inlineQueryResultMpeg4GifEncoder(implicit E: Encoder[String @@ ResultId]): Encoder[inline.InlineQueryResultMpeg4Gif] = Encoder[inline.InlineQueryResultMpeg4Gif].mapJson(_.deepMerge(inline.InlineQueryResultMpeg4Gif.`type`.asJson))
  implicit def inlineQueryResultVideoEncoder(implicit E: Encoder[String @@ ResultId]): Encoder[inline.InlineQueryResultVideo] = Encoder[inline.InlineQueryResultVideo].mapJson(_.deepMerge(inline.InlineQueryResultVideo.`type`.asJson))
  implicit def inlineQueryResultAudioEncoder(implicit E: Encoder[String @@ ResultId]): Encoder[inline.InlineQueryResultAudio] = Encoder[inline.InlineQueryResultAudio].mapJson(_.deepMerge(inline.InlineQueryResultAudio.`type`.asJson))
  implicit def inlineQueryResultVoiceEncoder(implicit E: Encoder[String @@ ResultId]): Encoder[inline.InlineQueryResultVoice] = Encoder[inline.InlineQueryResultVoice].mapJson(_.deepMerge(inline.InlineQueryResultVoice.`type`.asJson))
  implicit def inlineQueryResultDocumentEncoder(implicit E: Encoder[String @@ ResultId]): Encoder[inline.InlineQueryResultDocument] = Encoder[inline.InlineQueryResultDocument].mapJson(_.deepMerge(inline.InlineQueryResultDocument.`type`.asJson))
  implicit def inlineQueryResultLocationEncoder(implicit E: Encoder[String @@ ResultId]): Encoder[inline.InlineQueryResultLocation] = Encoder[inline.InlineQueryResultLocation].mapJson(_.deepMerge(inline.InlineQueryResultLocation.`type`.asJson))
  implicit def inlineQueryResultVenueEncoder(implicit E: Encoder[String @@ ResultId], EE: Encoder[String @@ FoursquareId]): Encoder[inline.InlineQueryResultVenue] = Encoder[inline.InlineQueryResultVenue].mapJson(_.deepMerge(inline.InlineQueryResultVenue.`type`.asJson))
  implicit def inlineQueryResultContactEncoder(implicit E: Encoder[String @@ ResultId]): Encoder[inline.InlineQueryResultContact] = Encoder[inline.InlineQueryResultContact].mapJson(_.deepMerge(inline.InlineQueryResultContact.`type`.asJson))
  implicit def inlineQueryResultCachedPhotoEncoder(implicit E: Encoder[String @@ ResultId]): Encoder[inline.InlineQueryResultCachedPhoto] = Encoder[inline.InlineQueryResultCachedPhoto].mapJson(_.deepMerge(inline.InlineQueryResultCachedPhoto.`type`.asJson))
  implicit def inlineQueryResultCachedGifEncoder(implicit E: Encoder[String @@ ResultId]): Encoder[inline.InlineQueryResultCachedGif] = Encoder[inline.InlineQueryResultCachedGif].mapJson(_.deepMerge(inline.InlineQueryResultCachedGif.`type`.asJson))
  implicit def inlineQueryResultCachedMpeg4GifEncoder(implicit E: Encoder[String @@ ResultId]): Encoder[inline.InlineQueryResultCachedMpeg4Gif] = Encoder[inline.InlineQueryResultCachedMpeg4Gif].mapJson(_.deepMerge(inline.InlineQueryResultCachedMpeg4Gif.`type`.asJson))
  implicit def inlineQueryResultCachedStickerEncoder(implicit E: Encoder[String @@ ResultId]): Encoder[inline.InlineQueryResultCachedSticker] = Encoder[inline.InlineQueryResultCachedSticker].mapJson(_.deepMerge(inline.InlineQueryResultCachedSticker.`type`.asJson))
  implicit def inlineQueryResultCachedDocumentEncoder(implicit E: Encoder[String @@ ResultId]): Encoder[inline.InlineQueryResultCachedDocument] = Encoder[inline.InlineQueryResultCachedDocument].mapJson(_.deepMerge(inline.InlineQueryResultCachedDocument.`type`.asJson))
  implicit def inlineQueryResultCachedVideoEncoder(implicit E: Encoder[String @@ ResultId]): Encoder[inline.InlineQueryResultCachedVideo] = Encoder[inline.InlineQueryResultCachedVideo].mapJson(_.deepMerge(inline.InlineQueryResultCachedVideo.`type`.asJson))
  implicit def inlineQueryResultCachedVoiceEncoder(implicit E: Encoder[String @@ ResultId]): Encoder[inline.InlineQueryResultCachedVoice] = Encoder[inline.InlineQueryResultCachedVoice].mapJson(_.deepMerge(inline.InlineQueryResultCachedVoice.`type`.asJson))
  implicit def inlineQueryResultCachedAudioEncoder(implicit E: Encoder[String @@ ResultId]): Encoder[inline.InlineQueryResultCachedAudio] = Encoder[inline.InlineQueryResultCachedAudio].mapJson(_.deepMerge(inline.InlineQueryResultCachedAudio.`type`.asJson))
  implicit def inlineQueryResultGameEncoder(implicit E: Encoder[String @@ ResultId]): Encoder[inline.InlineQueryResultGame] = Encoder[inline.InlineQueryResultGame].mapJson(_.deepMerge(inline.InlineQueryResultGame.`type`.asJson))

  implicit def answerInlineQueryEncoder(implicit E: Encoder[String @@ QueryId]): Encoder[inline.AnswerInlineQuery] = Encoder[inline.AnswerInlineQuery]

  // Methods
  implicit val getMeJsonEncoder: Encoder[GetMe.type] = Encoder.instance(_ ⇒ io.circe.Json.Null)
  implicit val getWebhookInfoEncoder: Encoder[GetWebhookInfo.type] = Encoder.instance(_ ⇒ io.circe.Json.Null)
  implicit def sendMessageJsonEncoder[A: IsResourceId](implicit E: Encoder[A @@ ChatId], EE: Encoder[Long @@ MessageId]): Encoder[SendMessage[A]] = Encoder[SendMessage[A]]
  implicit def forwardMessageJsonEncoder[A: IsResourceId, B: IsResourceId](implicit E: Encoder[A @@ ChatId], EE: Encoder[B @@ ChatId], EEE: Encoder[Long @@ MessageId]): Encoder[ForwardMessage[A, B]] = Encoder[ForwardMessage[A, B]]
  implicit def getUpdatesJsonEncoder(implicit E: Encoder[Long @@ UpdateId]): Encoder[GetUpdates] = deriveEncoder[GetUpdates]
  implicit def sendPhotoEncoder[A: IsResourceId](implicit E: Encoder[A @@ ChatId], EE: Encoder[Long @@ MessageId], EEE: Encoder[String @@ FileId]): Encoder[SendPhoto[A]] = Encoder[SendPhoto[A]]
  implicit def sendAudioEncoder[A: IsResourceId](implicit E: Encoder[A @@ ChatId], EE: Encoder[Long @@ MessageId], EEE: Encoder[String @@ FileId]): Encoder[SendAudio[A]] = Encoder[SendAudio[A]]
  implicit def sendDocumentEncoder[A: IsResourceId](implicit E: Encoder[A @@ ChatId], EE: Encoder[Long @@ MessageId], EEE: Encoder[String @@ FileId]): Encoder[SendDocument[A]] = Encoder[SendDocument[A]]
  implicit def sendStickerEncoder[A: IsResourceId](implicit E: Encoder[A @@ ChatId], EE: Encoder[Long @@ MessageId], EEE: Encoder[String @@ FileId]): Encoder[SendSticker[A]] = Encoder[SendSticker[A]]
  implicit def sendVideoEncoder[A: IsResourceId](implicit E: Encoder[A @@ ChatId], EE: Encoder[Long @@ MessageId], EEE: Encoder[String @@ FileId]): Encoder[SendVideo[A]] = Encoder[SendVideo[A]]
  implicit def sendVoiceEncoder[A: IsResourceId](implicit E: Encoder[A @@ ChatId], EE: Encoder[Long @@ MessageId], EEE: Encoder[String @@ FileId]): Encoder[SendVoice[A]] = Encoder[SendVoice[A]]
  implicit def sendLocationEncoder[A: IsResourceId](implicit E: Encoder[A @@ ChatId], EE: Encoder[Long @@ MessageId]): Encoder[SendLocation[A]] = Encoder[SendLocation[A]]
  implicit def sendVenueEncoder[A: IsResourceId](implicit E: Encoder[A @@ ChatId], EE: Encoder[Long @@ MessageId]): Encoder[SendVenue[A]] = Encoder[SendVenue[A]]
  implicit def sendContactEncoder[A: IsResourceId](implicit E: Encoder[A @@ ChatId], EE: Encoder[Long @@ MessageId]): Encoder[SendContact[A]] = Encoder[SendContact[A]]
  implicit def sendGameEncoder[A: IsResourceId](implicit E: Encoder[A @@ ChatId], EE: Encoder[Long @@ MessageId]): Encoder[SendGame[A]] = Encoder[SendGame[A]]
  implicit def setGameScoreEncoder[A: IsResourceId](implicit E: Encoder[A @@ ChatId], EE: Encoder[Long @@ MessageId], EEE: Encoder[String @@ MessageId], EEEE: Encoder[Long @@ UserId]): Encoder[SetGameScore[A]] = Encoder[SetGameScore[A]]
  implicit def getGameHighScoresEncoder[A: IsResourceId](implicit E: Encoder[A @@ ChatId], EE: Encoder[Long @@ MessageId], EEE: Encoder[String @@ MessageId], EEEE: Encoder[Long @@ UserId]): Encoder[GetGameHighScores[A]] = Encoder[GetGameHighScores[A]]
  implicit val chatActionEncoder: Encoder[ChatAction] = Encoder[String].contramap[ChatAction](e ⇒ snakenize {e.productPrefix})
  implicit def sendChatActionEncoder[A: IsResourceId](implicit E: Encoder[A @@ ChatId]): Encoder[SendChatAction[A]] = Encoder[SendChatAction[A]]
  implicit def getUserProfilePhotosEncoder(implicit E: Encoder[Int @@ UserId]): Encoder[GetUserProfilePhotos] = Encoder[GetUserProfilePhotos]
  implicit def getFileEncoder(implicit E: Encoder[String @@ FileId]): Encoder[GetFile] = deriveEncoder[GetFile]
  implicit def kickChatMemberEncoder[A: IsResourceId](implicit E: Encoder[A @@ ChatId], EE: Encoder[Int @@ UserId]): Encoder[KickChatMember[A]] = Encoder[KickChatMember[A]]
  implicit def leaveChatEncoder[A: IsResourceId](implicit E: Encoder[A @@ ChatId]): Encoder[LeaveChat[A]] = deriveEncoder[LeaveChat[A]]
  implicit def unbanChatMemberEncoder[A: IsResourceId](implicit E: Encoder[A @@ ChatId], EE: Encoder[Int @@ UserId]): Encoder[UnbanChatMember[A]] = Encoder[UnbanChatMember[A]]
  implicit def getChatEncoder[A: IsResourceId](implicit E: Encoder[A @@ ChatId]): Encoder[GetChat[A]] = deriveEncoder[GetChat[A]]
  implicit def getChatAdministratorsEncoder[A: IsResourceId](implicit E: Encoder[A @@ ChatId]): Encoder[GetChatAdministrators[A]] = deriveEncoder[GetChatAdministrators[A]]
  implicit def getChatMembersCountEncoder[A: IsResourceId](implicit E: Encoder[A @@ ChatId]): Encoder[GetChatMembersCount[A]] = deriveEncoder[GetChatMembersCount[A]]
  implicit def getChatMemberEncoder[A: IsResourceId](implicit E: Encoder[A @@ ChatId], EE: Encoder[Int @@ UserId]): Encoder[GetChatMember[A]] = Encoder[GetChatMember[A]]
  implicit def answerCallbackQueryEncoder(implicit E: Encoder[String @@ QueryId]): Encoder[AnswerCallbackQuery] = Encoder[AnswerCallbackQuery]

  implicit def editMessageTextEncoder[A: IsResourceId](implicit E: Encoder[A @@ ChatId], EE: Encoder[Long @@ MessageId], EEE: Encoder[String @@ MessageId]): Encoder[EditMessageText[A]] = Encoder[EditMessageText[A]]
  implicit def editMessageCaptionEncoder[A: IsResourceId](implicit E: Encoder[A @@ ChatId], EE: Encoder[Long @@ MessageId], EEE: Encoder[String @@ MessageId]): Encoder[EditMessageCaption[A]] = Encoder[EditMessageCaption[A]]
  implicit def editMessageReplyMarkupEncoder[A: IsResourceId](implicit E: Encoder[A @@ ChatId], EE: Encoder[Long @@ MessageId], EEE: Encoder[String @@ MessageId]): Encoder[EditMessageReplyMarkup[A]] = Encoder[EditMessageReplyMarkup[A]]

  implicit val setWebhookEncoder: Encoder[SetWebhook] = deriveEncoder[SetWebhook]
}
