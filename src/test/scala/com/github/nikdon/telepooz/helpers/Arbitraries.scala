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

package com.github.nikdon.telepooz.helpers

import java.time.Duration
import java.util.Date

import com.github.nikdon.telepooz.model.MessageEntityType._
import com.github.nikdon.telepooz.model._
import com.github.nikdon.telepooz.model.inline.{ChosenInlineQuery, _}
import com.github.nikdon.telepooz.model.methods._
import com.github.nikdon.telepooz.model.methods.payments.{AnswerPreCheckoutQuery, AnswerShippingQuery, SendInvoice}
import com.github.nikdon.telepooz.model.payments._
import org.scalacheck.Arbitrary._
import org.scalacheck.{Arbitrary, Gen}

object Arbitraries {

  implicit val durationArb: Arbitrary[Duration] = Arbitrary(arbitrary[Int].map(s ⇒ Duration.ofSeconds(s.toLong)))

  // Payments
  implicit val invoiceArb: Arbitrary[Invoice]                     = Arbitrary(Gen.resultOf(Invoice))
  implicit val labeledPriceArb: Arbitrary[LabeledPrice]           = Arbitrary(Gen.resultOf(LabeledPrice))
  implicit val orderInfoArb: Arbitrary[OrderInfo]                 = Arbitrary(Gen.resultOf(OrderInfo))
  implicit val preCheckoutQueryArb: Arbitrary[PreCheckoutQuery]   = Arbitrary(Gen.resultOf(PreCheckoutQuery))
  implicit val shippingAddressArb: Arbitrary[ShippingAddress]     = Arbitrary(Gen.resultOf(ShippingAddress))
  implicit val shippingOptionArb: Arbitrary[ShippingOption]       = Arbitrary(Gen.resultOf(ShippingOption))
  implicit val shippingQueryArb: Arbitrary[ShippingQuery]         = Arbitrary(Gen.resultOf(ShippingQuery))
  implicit val successfulPaymentArb: Arbitrary[SuccessfulPayment] = Arbitrary(Gen.resultOf(SuccessfulPayment))

  implicit val audioArb: Arbitrary[Audio]                               = Arbitrary(Gen.resultOf(Audio))
  implicit val callbackQueryArb: Arbitrary[CallbackQuery]               = Arbitrary(Gen.resultOf(CallbackQuery))
  implicit val callbackGameArb: Arbitrary[CallbackGame]                 = Arbitrary(Gen.const(CallbackGame()))
  implicit val fileArb: Arbitrary[File]                                 = Arbitrary(Gen.resultOf(File))
  implicit val chosenInlineQueryArb: Arbitrary[ChosenInlineQuery]       = Arbitrary(Gen.resultOf(ChosenInlineQuery))
  implicit val locationArb: Arbitrary[Location]                         = Arbitrary(Gen.resultOf(Location))
  implicit val userArb: Arbitrary[User]                                 = Arbitrary(Gen.resultOf(User))
  implicit val parseModeArb: Arbitrary[ParseMode]                       = Arbitrary(Gen.oneOf(ParseMode.Markdown, ParseMode.HTML))
  implicit val inlineKeyboardButtonArb: Arbitrary[InlineKeyboardButton] = Arbitrary(Gen.resultOf(InlineKeyboardButton))
  implicit val inlineQueryArb: Arbitrary[InlineQuery]                   = Arbitrary(Gen.resultOf(InlineQuery))
  implicit val keyboardButtonArb: Arbitrary[KeyboardButton]             = Arbitrary(Gen.resultOf(KeyboardButton))
  implicit val updateArb: Arbitrary[Update]                             = Arbitrary(Gen.resultOf(Update))
  implicit val memberStatusArb: Arbitrary[MemberStatus] = Arbitrary(
    Gen.oneOf(MemberStatus.Creator,
              MemberStatus.Administrator,
              MemberStatus.Member,
              MemberStatus.Left,
              MemberStatus.Kicked)
  )
  implicit val chatMemberArb: Arbitrary[ChatMember] = Arbitrary(Gen.resultOf(ChatMember))
  implicit val chatTypeArb: Arbitrary[ChatType] = Arbitrary(
    Gen.oneOf(ChatType.Group, ChatType.Channel, ChatType.Private, ChatType.SuperGroup)
  )
  implicit val chatArb: Arbitrary[Chat]       = Arbitrary(Gen.resultOf(Chat))
  implicit val venueArb: Arbitrary[Venue]     = Arbitrary(Gen.resultOf(Venue))
  implicit val contactAbr: Arbitrary[Contact] = Arbitrary(Gen.resultOf(Contact))
  implicit val messageEntityTypeAbt: Arbitrary[MessageEntityType] = Arbitrary(
    Gen.oneOf(Mention, Hashtag, BotCommand, Url, Email, Bold, Italic, Code, Pre, TextLink)
  )
  implicit val messageEntityArb: Arbitrary[MessageEntity]         = Arbitrary(Gen.resultOf(MessageEntity))
  implicit val documentArb: Arbitrary[Document]                   = Arbitrary(Gen.resultOf(Document))
  implicit val photoSizeArb: Arbitrary[PhotoSize]                 = Arbitrary(Gen.resultOf(PhotoSize))
  implicit val stickerArb: Arbitrary[Sticker]                     = Arbitrary(Gen.resultOf(Sticker))
  implicit val videoArb: Arbitrary[Video]                         = Arbitrary(Gen.resultOf(Video))
  implicit val videoNoteArb: Arbitrary[VideoNote]                 = Arbitrary(Gen.resultOf(VideoNote))
  implicit val voiceArb: Arbitrary[Voice]                         = Arbitrary(Gen.resultOf(Voice))
  implicit val userProfilePhotosArb: Arbitrary[UserProfilePhotos] = Arbitrary(Gen.resultOf(UserProfilePhotos))

  // Game
  implicit val gameHighScoreArv: Arbitrary[GameHighScore] = Arbitrary(Gen.resultOf(GameHighScore.apply _))
  implicit val animationArb: Arbitrary[Animation]         = Arbitrary(Gen.resultOf(Animation.apply _))
  implicit val gameArb: Arbitrary[Game]                   = Arbitrary(Gen.resultOf(Game.apply _))

  implicit val messageArb: Arbitrary[Message] = {
    val g: Gen[Message] = for {
      messageId             ← arbitrary[Long]
      date                  ← arbitrary[Date]
      chat                  ← arbitrary[Chat]
      from                  ← arbitrary[Option[User]]
      forwardFrom           ← arbitrary[Option[User]]
      forwardFromChat       ← arbitrary[Option[Chat]]
      forwardDate           ← arbitrary[Option[Date]]
      replyToMessage        ← arbitrary[Option[Message]]
      text                  ← arbitrary[Option[String]]
      entities              ← arbitrary[Option[Vector[MessageEntity]]]
      audio                 ← arbitrary[Option[Audio]]
      document              ← arbitrary[Option[Document]]
      game                  ← arbitrary[Option[Game]]
      photo                 ← arbitrary[Option[Vector[PhotoSize]]]
      sticker               ← arbitrary[Option[Sticker]]
      video                 ← arbitrary[Option[Video]]
      videoNote             ← arbitrary[Option[VideoNote]]
      voice                 ← arbitrary[Option[Voice]]
      caption               ← arbitrary[Option[String]]
      contact               ← arbitrary[Option[Contact]]
      location              ← arbitrary[Option[Location]]
      venue                 ← arbitrary[Option[Venue]]
      newChatMembers        ← arbitrary[Option[List[User]]]
      leftChatMember        ← arbitrary[Option[User]]
      newChatTitle          ← arbitrary[Option[String]]
      newChatPhoto          ← arbitrary[Option[Vector[PhotoSize]]]
      deleteChatPhoto       ← arbitrary[Option[Boolean]]
      groupChatCreated      ← arbitrary[Option[Boolean]]
      superGroupChatCreated ← arbitrary[Option[Boolean]]
      channelChatCreated    ← arbitrary[Option[Boolean]]
      migrateToChatId       ← arbitrary[Option[Long]]
      migrateFromChatId     ← arbitrary[Option[Long]]
      pinnedMessage         ← arbitrary[Option[Message]]
    } yield
      Message(
        messageId,
        date,
        chat,
        from,
        forwardFrom,
        forwardFromChat,
        forwardDate,
        replyToMessage,
        text,
        entities,
        audio,
        document,
        game,
        photo,
        sticker,
        video,
        videoNote,
        voice,
        caption,
        contact,
        location,
        venue,
        newChatMembers,
        leftChatMember,
        newChatTitle,
        newChatPhoto,
        deleteChatPhoto,
        groupChatCreated,
        superGroupChatCreated,
        channelChatCreated,
        migrateToChatId,
        migrateFromChatId,
        pinnedMessage
      )

    Arbitrary(g)
  }

  /** [[ReplyMarkup]] */
  implicit val replyKeyboardMarkupArb: Arbitrary[ReplyKeyboardMarkup]   = Arbitrary(Gen.resultOf(ReplyKeyboardMarkup))
  implicit val inlineKeyboardMarkupArb: Arbitrary[InlineKeyboardMarkup] = Arbitrary(Gen.resultOf(InlineKeyboardMarkup))
  implicit val replyKeyboardHideArb: Arbitrary[ReplyKeyboardHide]       = Arbitrary(Gen.resultOf(ReplyKeyboardHide))
  implicit val forceReplyArb: Arbitrary[ForceReply]                     = Arbitrary(Gen.resultOf(ForceReply))
  implicit val replyMarkupArb: Arbitrary[ReplyMarkup] = Arbitrary(
    Gen.oneOf(
      inlineKeyboardMarkupArb.arbitrary,
      replyKeyboardHideArb.arbitrary,
      forceReplyArb.arbitrary
    ))

  implicit val answerInlineQueryArb: Arbitrary[AnswerInlineQuery] = Arbitrary(Gen.resultOf(AnswerInlineQuery))

  /** [[InputMessageContent]] */
  implicit val inputTextMessageContentArb: Arbitrary[InputTextMessageContent] = Arbitrary(
    Gen.resultOf(InputTextMessageContent))
  implicit val inputLocationMessageContentArb: Arbitrary[InputLocationMessageContent] = Arbitrary(
    Gen.resultOf(InputLocationMessageContent))
  implicit val inputVenueMessageContentAbr: Arbitrary[InputVenueMessageContent] = Arbitrary(
    Gen.resultOf(InputVenueMessageContent))
  implicit val inputContactMessageContentArb: Arbitrary[InputContactMessageContent] = Arbitrary(
    Gen.resultOf(InputContactMessageContent))
  implicit val inputMessageContentArb: Arbitrary[InputMessageContent] = Arbitrary(
    Gen.oneOf(
      inputTextMessageContentArb.arbitrary,
      inputLocationMessageContentArb.arbitrary,
      inputVenueMessageContentAbr.arbitrary,
      inputContactMessageContentArb.arbitrary
    ))

  /** [[InlineQueryResult]] */
  implicit val inlineQueryResultArticleArb: Arbitrary[InlineQueryResultArticle] = Arbitrary(
    Gen.resultOf(InlineQueryResultArticle.apply _))
  implicit val inlineQueryResultPhotoArb: Arbitrary[InlineQueryResultPhoto] = Arbitrary(
    Gen.resultOf(InlineQueryResultPhoto.apply _))
  implicit val inlineQueryResultGifArb: Arbitrary[InlineQueryResultGif] = Arbitrary(
    Gen.resultOf(InlineQueryResultGif.apply _))
  implicit val inlineQueryResultMpeg4GifArb: Arbitrary[InlineQueryResultMpeg4Gif] = Arbitrary(
    Gen.resultOf(InlineQueryResultMpeg4Gif.apply _))
  implicit val inlineQueryResultVideoArb: Arbitrary[InlineQueryResultVideo] = Arbitrary(
    Gen.resultOf(InlineQueryResultVideo.apply _))
  implicit val inlineQueryResultAudioArb: Arbitrary[InlineQueryResultAudio] = Arbitrary(
    Gen.resultOf(InlineQueryResultAudio.apply _))
  implicit val inlineQueryResultVoiceArb: Arbitrary[InlineQueryResultVoice] = Arbitrary(
    Gen.resultOf(InlineQueryResultVoice.apply _))
  implicit val inlineQueryResultDocumentArb: Arbitrary[InlineQueryResultDocument] = Arbitrary(
    Gen.resultOf(InlineQueryResultDocument.apply _))
  implicit val inlineQueryResultLocationArb: Arbitrary[InlineQueryResultLocation] = Arbitrary(
    Gen.resultOf(InlineQueryResultLocation.apply _))
  implicit val inlineQueryResultVenueArb: Arbitrary[InlineQueryResultVenue] = Arbitrary(
    Gen.resultOf(InlineQueryResultVenue.apply _))
  implicit val inlineQueryResultContactArb: Arbitrary[InlineQueryResultContact] = Arbitrary(
    Gen.resultOf(InlineQueryResultContact.apply _))
  implicit val inlineQueryResultCachedPhotoArb: Arbitrary[InlineQueryResultCachedPhoto] = Arbitrary(
    Gen.resultOf(InlineQueryResultCachedPhoto.apply _))
  implicit val inlineQueryResultCachedGifArb: Arbitrary[InlineQueryResultCachedGif] = Arbitrary(
    Gen.resultOf(InlineQueryResultCachedGif.apply _))
  implicit val inlineQueryResultCachedMpeg4GifArb: Arbitrary[InlineQueryResultCachedMpeg4Gif] = Arbitrary(
    Gen.resultOf(InlineQueryResultCachedMpeg4Gif.apply _))
  implicit val inlineQueryResultCachedStickerArb: Arbitrary[InlineQueryResultCachedSticker] = Arbitrary(
    Gen.resultOf(InlineQueryResultCachedSticker.apply _))
  implicit val inlineQueryResultCachedDocumentArb: Arbitrary[InlineQueryResultCachedDocument] = Arbitrary(
    Gen.resultOf(InlineQueryResultCachedDocument.apply _))
  implicit val inlineQueryResultCachedVideoArb: Arbitrary[InlineQueryResultCachedVideo] = Arbitrary(
    Gen.resultOf(InlineQueryResultCachedVideo.apply _))
  implicit val inlineQueryResultCachedVoiceArb: Arbitrary[InlineQueryResultCachedVoice] = Arbitrary(
    Gen.resultOf(InlineQueryResultCachedVoice.apply _))
  implicit val inlineQueryResultCachedAudioArb: Arbitrary[InlineQueryResultCachedAudio] = Arbitrary(
    Gen.resultOf(InlineQueryResultCachedAudio.apply _))
  implicit val inlineQueryResultGameArb: Arbitrary[InlineQueryResultGame] = Arbitrary(
    Gen.resultOf(InlineQueryResultGame.apply _))
  implicit val inlineQueryResultArb: Arbitrary[InlineQueryResult] = Arbitrary(
    Gen.oneOf(
      inlineQueryResultArticleArb.arbitrary,
      inlineQueryResultPhotoArb.arbitrary,
      inlineQueryResultGifArb.arbitrary,
      inlineQueryResultMpeg4GifArb.arbitrary,
      inlineQueryResultVideoArb.arbitrary,
      inlineQueryResultAudioArb.arbitrary,
      inlineQueryResultVoiceArb.arbitrary,
      inlineQueryResultDocumentArb.arbitrary,
      inlineQueryResultLocationArb.arbitrary,
      inlineQueryResultVenueArb.arbitrary,
      inlineQueryResultContactArb.arbitrary,
      inlineQueryResultCachedPhotoArb.arbitrary,
      inlineQueryResultCachedGifArb.arbitrary,
      inlineQueryResultCachedMpeg4GifArb.arbitrary,
      inlineQueryResultCachedStickerArb.arbitrary,
      inlineQueryResultCachedDocumentArb.arbitrary,
      inlineQueryResultCachedVideoArb.arbitrary,
      inlineQueryResultCachedVoiceArb.arbitrary,
      inlineQueryResultCachedAudioArb.arbitrary,
      inlineQueryResultGameArb.arbitrary
    ))

  /** Methods */
  implicit val getMeArb: Arbitrary[GetMe.type]                        = Arbitrary(Gen.const(GetMe))
  implicit val sendMessageArb: Arbitrary[SendMessage]                 = Arbitrary(Gen.resultOf(SendMessage.apply _))
  implicit val forwardMessageArb: Arbitrary[ForwardMessage]           = Arbitrary(Gen.resultOf(ForwardMessage.apply _))
  implicit val answerCallbackQueryArb: Arbitrary[AnswerCallbackQuery] = Arbitrary(Gen.resultOf(AnswerCallbackQuery))
  implicit val getChatAdministratorsArb: Arbitrary[GetChatAdministrators] = Arbitrary(
    Gen.resultOf(GetChatAdministrators.apply _))
  implicit val getChatMembersCountArb: Arbitrary[GetChatMembersCount] = Arbitrary(
    Gen.resultOf(GetChatMembersCount.apply _))
  implicit val getChatMemberArb: Arbitrary[GetChatMember] = Arbitrary(Gen.resultOf(GetChatMember.apply _))
  implicit val getChatArb: Arbitrary[GetChat]             = Arbitrary(Gen.resultOf(GetChat.apply _))
  implicit val chatActionArb: Arbitrary[ChatAction] = Arbitrary(
    Gen.oneOf(
      ChatAction.Typing,
      ChatAction.UploadPhoto,
      ChatAction.RecordVideo,
      ChatAction.UploadVideo,
      ChatAction.RecordAudio,
      ChatAction.UploadAudio,
      ChatAction.UploadDocument,
      ChatAction.FindLocation,
      ChatAction.RecordVideoNote,
      ChatAction.UploadVideoNote
    ))
  implicit val sendChatActionArb: Arbitrary[SendChatAction] = Arbitrary(Gen.resultOf(SendChatAction.apply _))
  implicit val sendLocationArb: Arbitrary[SendLocation]     = Arbitrary(Gen.resultOf(SendLocation.apply _))
  implicit val sendVenueArb: Arbitrary[SendVenue]           = Arbitrary(Gen.resultOf(SendVenue.apply _))
  implicit val sendContactArb: Arbitrary[SendContact]       = Arbitrary(Gen.resultOf(SendContact.apply _))
  implicit val sendVideoArb: Arbitrary[SendVideo]           = Arbitrary(Gen.resultOf(SendVideo.apply _))
  implicit val sendVoiceArb: Arbitrary[SendVoice]           = Arbitrary(Gen.resultOf(SendVoice.apply _))

  // Payments methods
  implicit val answerPreCheckoutQueryArb: Arbitrary[AnswerPreCheckoutQuery] = Arbitrary(Gen.resultOf(AnswerPreCheckoutQuery))
  implicit val answerShippingQueryArb: Arbitrary[AnswerShippingQuery] = Arbitrary(Gen.resultOf(AnswerShippingQuery))
  implicit val sendInvoiceArb: Arbitrary[SendInvoice] = Arbitrary(Gen.resultOf(SendInvoice))

  implicit val editMessageTextArb: Arbitrary[EditMessageText] = Arbitrary(Gen.resultOf(EditMessageText.apply _))
  implicit val editMessageCaptionArb: Arbitrary[EditMessageCaption] = Arbitrary(
    Gen.resultOf(EditMessageCaption.apply _))
  implicit val editMessageReplyMarkupArb: Arbitrary[EditMessageReplyMarkup] = Arbitrary(
    Gen.resultOf(EditMessageReplyMarkup.apply _))
}
