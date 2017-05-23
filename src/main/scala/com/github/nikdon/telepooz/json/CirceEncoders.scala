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
import com.github.nikdon.telepooz.model.methods._
import com.github.nikdon.telepooz.model.methods.payments.{AnswerPreCheckoutQuery, AnswerShippingQuery, SendInvoice}
import com.github.nikdon.telepooz.model.payments._
import com.github.nikdon.telepooz.utils._
import io.circe.Encoder
import io.circe.generic.extras._
import io.circe.generic.extras.semiauto._
import io.circe.syntax._
import io.circe.generic.extras.auto._

trait CirceEncoders {

  implicit val customConfig: Configuration = Configuration.default.withSnakeCaseKeys.withDefaults

  // Models
  implicit val dateEncoder: Encoder[Date]         = Encoder[Long].contramap[Date](d ⇒ d.getTime)
  implicit val durationEncoder: Encoder[Duration] = Encoder[Int].contramap[Duration](d ⇒ d.getSeconds.toInt)

  implicit val audioEncoder: Encoder[Audio]                 = deriveEncoder[Audio]
  implicit val callbackQueryEncoder: Encoder[CallbackQuery] = deriveEncoder[CallbackQuery]

  implicit val callbackGameEncoder: Encoder[CallbackGame] = deriveEncoder[CallbackGame]

  implicit val chatTypeEncoder: Encoder[ChatType] =
    Encoder[String].contramap[ChatType](e => snakenize(e.productPrefix))

  implicit val chatEncoder: Encoder[Chat] = deriveEncoder[Chat]

  implicit val memberStatusEncoder: Encoder[MemberStatus] =
    Encoder[String].contramap[MemberStatus](e => snakenize(e.productPrefix))

  implicit val chatMemberEncoder: Encoder[ChatMember] = deriveEncoder[ChatMember]

  implicit val contactEncoder: Encoder[Contact]   = deriveEncoder[Contact]
  implicit val documentEncoder: Encoder[Document] = deriveEncoder[Document]
  implicit val fileEncoder: Encoder[File]         = deriveEncoder[File]

  implicit val inlineKeyboardButtonEncoder: Encoder[InlineKeyboardButton] = deriveEncoder[InlineKeyboardButton]
  implicit val keyboardButtonEncoder: Encoder[KeyboardButton]             = deriveEncoder[KeyboardButton]
  implicit val locationEncoder: Encoder[Location]                         = deriveEncoder[Location]

  implicit val gameHighScoreEncoder: Encoder[GameHighScore] = deriveEncoder[GameHighScore]
  implicit val animationEncoder: Encoder[Animation]         = deriveEncoder[Animation]
  implicit val gameEncoder: Encoder[Game]                   = deriveEncoder[Game]

  implicit val messageEncoder: Encoder[Message] = deriveEncoder[Message]

  implicit val messageEntityTypeEncoder: Encoder[MessageEntityType] =
    Encoder[String].contramap[MessageEntityType](e ⇒ snakenize(e.productPrefix))

  implicit val messageEntityEncoder: Encoder[MessageEntity] = deriveEncoder[MessageEntity]

  implicit val parseModeEncoder: Encoder[ParseMode] =
    Encoder[String].contramap[ParseMode](e => snakenize(e.productPrefix))

  implicit val photoSizeEncoder: Encoder[PhotoSize] = deriveEncoder[PhotoSize]

  implicit val replyMarkupEncoder: Encoder[ReplyMarkup] = Encoder.instance {
    case fr: ForceReply            => fr.asJson
    case ikm: InlineKeyboardMarkup => ikm.asJson
    case rkh: ReplyKeyboardHide    => rkh.asJson
    case rkm: ReplyKeyboardMarkup  => rkm.asJson
  }

  implicit val stickerEncoder: Encoder[Sticker]                     = deriveEncoder[Sticker]
  implicit val userEncoder: Encoder[User]                           = deriveEncoder[User]
  implicit val userProfilePhotosEncoder: Encoder[UserProfilePhotos] = deriveEncoder[UserProfilePhotos]
  implicit val venueEncoder: Encoder[Venue]                         = deriveEncoder[Venue]
  implicit val videoEncoder: Encoder[Video]                         = deriveEncoder[Video]
  implicit val videoNoteEncoder: Encoder[VideoNote]                 = deriveEncoder[VideoNote]
  implicit val voiceEncoder: Encoder[Voice]                         = deriveEncoder[Voice]

  // Payments
  implicit val labeledPriceEncoder: Encoder[LabeledPrice]           = deriveEncoder[LabeledPrice]
  implicit val invoiceEncoder: Encoder[Invoice]                     = deriveEncoder[Invoice]
  implicit val shippingAddressEncoder: Encoder[ShippingAddress]     = deriveEncoder[ShippingAddress]
  implicit val shippingQueryEncoder: Encoder[ShippingQuery]         = deriveEncoder[ShippingQuery]
  implicit val orderInfoEncoder: Encoder[OrderInfo]                 = deriveEncoder[OrderInfo]
  implicit val preCheckoutQueryEncoder: Encoder[PreCheckoutQuery]   = deriveEncoder[PreCheckoutQuery]
  implicit val shippingOptionEncoder: Encoder[ShippingOption]       = deriveEncoder[ShippingOption]
  implicit val successfulPaymentEncoder: Encoder[SuccessfulPayment] = deriveEncoder[SuccessfulPayment]

  implicit val updateEncoder: Encoder[Update]                       = deriveEncoder[Update]

  // Inline
  implicit val inlineQueryEncoder: Encoder[inline.InlineQuery]             = deriveEncoder[inline.InlineQuery]
  implicit val chosenInlineQueryEncoder: Encoder[inline.ChosenInlineQuery] = deriveEncoder[inline.ChosenInlineQuery]
  implicit val inputContactMessageContentEncoder: Encoder[inline.InputContactMessageContent] =
    deriveEncoder[inline.InputContactMessageContent]
  implicit val inputVenueMessageContent: Encoder[inline.InputVenueMessageContent] =
    deriveEncoder[inline.InputVenueMessageContent]
  implicit val inputLocationMessageContentEncoder: Encoder[inline.InputLocationMessageContent] =
    deriveEncoder[inline.InputLocationMessageContent]
  implicit val inputTextMessageContentEncoder: Encoder[inline.InputTextMessageContent] =
    deriveEncoder[inline.InputTextMessageContent]

  /** inline.InlineQueryResult */
  implicit val inlineQueryResultArticleEncoder: Encoder[inline.InlineQueryResultArticle] =
    deriveEncoder[inline.InlineQueryResultArticle].mapJson(_.deepMerge(inline.InlineQueryResultArticle.`type`.asJson))
  implicit val inlineQueryResultPhotoEncoder: Encoder[inline.InlineQueryResultPhoto] =
    deriveEncoder[inline.InlineQueryResultPhoto].mapJson(_.deepMerge(inline.InlineQueryResultPhoto.`type`.asJson))
  implicit val inlineQueryResultGifEncoder: Encoder[inline.InlineQueryResultGif] =
    deriveEncoder[inline.InlineQueryResultGif].mapJson(_.deepMerge(inline.InlineQueryResultGif.`type`.asJson))
  implicit val inlineQueryResultMpeg4GifEncoder: Encoder[inline.InlineQueryResultMpeg4Gif] =
    deriveEncoder[inline.InlineQueryResultMpeg4Gif]
      .mapJson(_.deepMerge(inline.InlineQueryResultMpeg4Gif.`type`.asJson))
  implicit val inlineQueryResultVideoEncoder: Encoder[inline.InlineQueryResultVideo] =
    deriveEncoder[inline.InlineQueryResultVideo].mapJson(_.deepMerge(inline.InlineQueryResultVideo.`type`.asJson))
  implicit val inlineQueryResultAudioEncoder: Encoder[inline.InlineQueryResultAudio] =
    deriveEncoder[inline.InlineQueryResultAudio].mapJson(_.deepMerge(inline.InlineQueryResultAudio.`type`.asJson))
  implicit val inlineQueryResultVoiceEncoder: Encoder[inline.InlineQueryResultVoice] =
    deriveEncoder[inline.InlineQueryResultVoice].mapJson(_.deepMerge(inline.InlineQueryResultVoice.`type`.asJson))
  implicit val inlineQueryResultDocumentEncoder: Encoder[inline.InlineQueryResultDocument] =
    deriveEncoder[inline.InlineQueryResultDocument]
      .mapJson(_.deepMerge(inline.InlineQueryResultDocument.`type`.asJson))
  implicit val inlineQueryResultLocationEncoder: Encoder[inline.InlineQueryResultLocation] =
    deriveEncoder[inline.InlineQueryResultLocation]
      .mapJson(_.deepMerge(inline.InlineQueryResultLocation.`type`.asJson))
  implicit val inlineQueryResultVenueEncoder: Encoder[inline.InlineQueryResultVenue] =
    deriveEncoder[inline.InlineQueryResultVenue].mapJson(_.deepMerge(inline.InlineQueryResultVenue.`type`.asJson))
  implicit val inlineQueryResultContactEncoder: Encoder[inline.InlineQueryResultContact] =
    deriveEncoder[inline.InlineQueryResultContact].mapJson(_.deepMerge(inline.InlineQueryResultContact.`type`.asJson))
  implicit val inlineQueryResultCachedPhotoEncoder: Encoder[inline.InlineQueryResultCachedPhoto] =
    deriveEncoder[inline.InlineQueryResultCachedPhoto]
      .mapJson(_.deepMerge(inline.InlineQueryResultCachedPhoto.`type`.asJson))
  implicit val inlineQueryResultCachedGifEncoder: Encoder[inline.InlineQueryResultCachedGif] =
    deriveEncoder[inline.InlineQueryResultCachedGif]
      .mapJson(_.deepMerge(inline.InlineQueryResultCachedGif.`type`.asJson))
  implicit val inlineQueryResultCachedMpeg4GifEncoder: Encoder[inline.InlineQueryResultCachedMpeg4Gif] =
    deriveEncoder[inline.InlineQueryResultCachedMpeg4Gif]
      .mapJson(_.deepMerge(inline.InlineQueryResultCachedMpeg4Gif.`type`.asJson))
  implicit val inlineQueryResultCachedStickerEncoder: Encoder[inline.InlineQueryResultCachedSticker] =
    deriveEncoder[inline.InlineQueryResultCachedSticker]
      .mapJson(_.deepMerge(inline.InlineQueryResultCachedSticker.`type`.asJson))
  implicit val inlineQueryResultCachedDocumentEncoder: Encoder[inline.InlineQueryResultCachedDocument] =
    deriveEncoder[inline.InlineQueryResultCachedDocument]
      .mapJson(_.deepMerge(inline.InlineQueryResultCachedDocument.`type`.asJson))
  implicit val inlineQueryResultCachedVideoEncoder: Encoder[inline.InlineQueryResultCachedVideo] =
    deriveEncoder[inline.InlineQueryResultCachedVideo]
      .mapJson(_.deepMerge(inline.InlineQueryResultCachedVideo.`type`.asJson))
  implicit val inlineQueryResultCachedVoiceEncoder: Encoder[inline.InlineQueryResultCachedVoice] =
    deriveEncoder[inline.InlineQueryResultCachedVoice]
      .mapJson(_.deepMerge(inline.InlineQueryResultCachedVoice.`type`.asJson))
  implicit val inlineQueryResultCachedAudioEncoder: Encoder[inline.InlineQueryResultCachedAudio] =
    deriveEncoder[inline.InlineQueryResultCachedAudio]
      .mapJson(_.deepMerge(inline.InlineQueryResultCachedAudio.`type`.asJson))
  implicit val inlineQueryResultGameEncoder: Encoder[inline.InlineQueryResultGame] =
    deriveEncoder[inline.InlineQueryResultGame].mapJson(_.deepMerge(inline.InlineQueryResultGame.`type`.asJson))

  implicit val answerInlineQueryEncoder: Encoder[inline.AnswerInlineQuery] = deriveEncoder[inline.AnswerInlineQuery]

  // Methods
  implicit val getMeJsonEncoder: Encoder[GetMe.type]                = Encoder.instance(_ ⇒ io.circe.Json.Null)
  implicit val getWebhookInfoEncoder: Encoder[GetWebhookInfo.type]  = Encoder.instance(_ ⇒ io.circe.Json.Null)
  implicit val sendMessageJsonEncoder: Encoder[SendMessage]         = deriveEncoder[SendMessage]
  implicit val forwardMessageJsonEncoder: Encoder[ForwardMessage]   = deriveEncoder[ForwardMessage]
  implicit val getUpdatesJsonEncoder: Encoder[GetUpdates]           = deriveEncoder[GetUpdates]
  implicit val sendPhotoEncoder: Encoder[SendPhoto]                 = deriveEncoder[SendPhoto]
  implicit val sendAudioEncoder: Encoder[SendAudio]                 = deriveEncoder[SendAudio]
  implicit val sendDocumentEncoder: Encoder[SendDocument]           = deriveEncoder[SendDocument]
  implicit val sendStickerEncoder: Encoder[SendSticker]             = deriveEncoder[SendSticker]
  implicit val sendVideoEncoder: Encoder[SendVideo]                 = deriveEncoder[SendVideo]
  implicit val sendVideoNoteEncoder: Encoder[SendVideoNote]         = deriveEncoder[SendVideoNote]
  implicit val sendVoiceEncoder: Encoder[SendVoice]                 = deriveEncoder[SendVoice]
  implicit val sendLocationEncoder: Encoder[SendLocation]           = deriveEncoder[SendLocation]
  implicit val sendVenueEncoder: Encoder[SendVenue]                 = deriveEncoder[SendVenue]
  implicit val sendContactEncoder: Encoder[SendContact]             = deriveEncoder[SendContact]
  implicit val sendGameEncoder: Encoder[SendGame]                   = deriveEncoder[SendGame]
  implicit val setGameScoreEncoder: Encoder[SetGameScore]           = deriveEncoder[SetGameScore]
  implicit val getGameHighScoresEncoder: Encoder[GetGameHighScores] = deriveEncoder[GetGameHighScores]

  // Payment Methods
  implicit val answerPreCheckoutQueryEncoder: Encoder[AnswerPreCheckoutQuery] = deriveEncoder[AnswerPreCheckoutQuery]
  implicit val answerShippingQueryEncoder: Encoder[AnswerShippingQuery]       = deriveEncoder[AnswerShippingQuery]
  implicit val sendInvoiceEncoder: Encoder[SendInvoice]                       = deriveEncoder[SendInvoice]

  implicit val chatActionEncoder: Encoder[ChatAction] =
    Encoder[String].contramap[ChatAction](e ⇒ snakenize(e.productPrefix))

  implicit val sendChatActionEncoder: Encoder[SendChatAction]               = deriveEncoder[SendChatAction]
  implicit val getUserProfilePhotosEncoder: Encoder[GetUserProfilePhotos]   = deriveEncoder[GetUserProfilePhotos]
  implicit val getFileEncoder: Encoder[GetFile]                             = deriveEncoder[GetFile]
  implicit val kickChatMemberEncoder: Encoder[KickChatMember]               = deriveEncoder[KickChatMember]
  implicit val leaveChatEncoder: Encoder[LeaveChat]                         = deriveEncoder[LeaveChat]
  implicit val unbanChatMemberEncoder: Encoder[UnbanChatMember]             = deriveEncoder[UnbanChatMember]
  implicit val deleteMessageEncoder: Encoder[DeleteMessage]                 = deriveEncoder[DeleteMessage]
  implicit val getChatEncoder: Encoder[GetChat]                             = deriveEncoder[GetChat]
  implicit val getChatAdministratorsEncoder: Encoder[GetChatAdministrators] = deriveEncoder[GetChatAdministrators]
  implicit val getChatMembersCountEncoder: Encoder[GetChatMembersCount]     = deriveEncoder[GetChatMembersCount]
  implicit val getChatMemberEncoder: Encoder[GetChatMember]                 = deriveEncoder[GetChatMember]
  implicit val answerCallbackQueryEncoder: Encoder[AnswerCallbackQuery]     = deriveEncoder[AnswerCallbackQuery]

  implicit val editMessageTextEncoder: Encoder[EditMessageText]               = deriveEncoder[EditMessageText]
  implicit val editMessageCaptionEncoder: Encoder[EditMessageCaption]         = deriveEncoder[EditMessageCaption]
  implicit val editMessageReplyMarkupEncoder: Encoder[EditMessageReplyMarkup] = deriveEncoder[EditMessageReplyMarkup]

  implicit val setWebhookEncoder: Encoder[SetWebhook] = deriveEncoder[SetWebhook]
}
