package com.github.nikdon.telepooz.raw

import java.time.Duration
import java.util.Date

import com.github.nikdon.telepooz.IsResourceId
import com.github.nikdon.telepooz.model._
import com.github.nikdon.telepooz.model.methods._
import com.github.nikdon.telepooz.tags._
import com.github.nikdon.telepooz.utils._
import io.circe.Encoder
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
  private implicit val dateEncoder: Encoder[Date] = Encoder[Long].contramap[Date](d ⇒ d.getTime)                      // TODO Check
  private implicit val durationEncoder: Encoder[Duration] = Encoder[Int].contramap[Duration](d ⇒ d.getSeconds.toInt)  // TODO Check

  implicit def audioEncoder(implicit E: Encoder[String @@ FileId]): Encoder[Audio] = deriveEncoder[Audio]
  implicit def callbackQueryEncoder(implicit E: Encoder[String @@ QueryId]): Encoder[CallbackQuery] = deriveEncoder[CallbackQuery]

  implicit val chatTypeEncoder: Encoder[ChatType] = Encoder[String].contramap[ChatType](_.productPrefix)
  implicit def chatEncoder(implicit E: Encoder[Long @@ ChatId]): Encoder[Chat] = deriveEncoder[Chat]
  implicit val memberStatusEncoder: Encoder[MemberStatus] = Encoder[String].contramap[MemberStatus](_.productPrefix)
  implicit val chatMemberEncoder: Encoder[ChatMember] = deriveEncoder[ChatMember]

  implicit def contactEncoder(implicit E: Encoder[Int @@ UserId]): Encoder[Contact] = deriveEncoder[Contact]
  implicit def documentEncoder(implicit E: Encoder[String @@ FileId]): Encoder[Document] = deriveEncoder[Document]
  implicit def fileEncoder(implicit E: Encoder[String @@ FileId]): Encoder[File] = deriveEncoder[File]

  implicit val inlineKeyboardButtonEncoder: Encoder[InlineKeyboardButton] = deriveEncoder[InlineKeyboardButton]
  implicit val keyboardButtonEncoder      : Encoder[KeyboardButton]       = deriveEncoder[KeyboardButton]
  implicit val locationEncoder            : Encoder[Location]             = deriveEncoder[Location]

  implicit def messageEncoder(implicit E: Encoder[Long @@ MessageId], EE: Encoder[Long @@ ChatId]): Encoder[Message] = deriveEncoder[Message]

  implicit val messageEntityTypeEncoder: Encoder[MessageEntityType] = Encoder[String].contramap[MessageEntityType](e ⇒ snakenize {e.productPrefix})
  implicit val messageEntityEncoder    : Encoder[MessageEntity]     = deriveEncoder[MessageEntity]

  implicit def parseModeEncoder: Encoder[ParseMode] = Encoder[String].contramap[ParseMode](_.productPrefix)

  implicit def photoSizeEncoder(implicit E: Encoder[String @@ FileId]): Encoder[PhotoSize] = deriveEncoder[PhotoSize]

  implicit val forceReplyEncoder          : Encoder[ForceReply]           = deriveEncoder[ForceReply]
  implicit val inlineKeyboardMarkupEncoder: Encoder[InlineKeyboardMarkup] = deriveEncoder[InlineKeyboardMarkup]
  implicit val replyKeyboardHideEncoder   : Encoder[ReplyKeyboardHide]    = deriveEncoder[ReplyKeyboardHide]
  implicit val replyKeyboardMarkupEncoder : Encoder[ReplyKeyboardMarkup]  = deriveEncoder[ReplyKeyboardMarkup]

  implicit def stickerEncoder(implicit E: Encoder[String @@ FileId]): Encoder[Sticker] = deriveEncoder[Sticker]
  implicit def userEncoder(implicit E: Encoder[Int @@ UserId]): Encoder[User] = deriveEncoder[User]
  implicit val userProfilePhotosEncoder: Encoder[UserProfilePhotos] = deriveEncoder[UserProfilePhotos]
  implicit def venueEncoder(implicit E: Encoder[String @@ FoursquareId]): Encoder[Venue] = deriveEncoder[Venue]
  implicit def videoEncoder(implicit E: Encoder[String @@ FileId]): Encoder[Video] = deriveEncoder[Video]
  implicit def voiceEncoder(implicit E: Encoder[String @@ FileId]): Encoder[Voice] = deriveEncoder[Voice]
  implicit def updateEncoder(implicit E: Encoder[Long @@ UpdateId]): Encoder[Update] = deriveEncoder[Update]

  // Inline
  implicit def inlineQueryEncoder(implicit E: Encoder[String @@ QueryId]): Encoder[inline.InlineQuery] = deriveEncoder[inline.InlineQuery]
  implicit def choosenInlineQueryEncoder(implicit E: Encoder[String @@ ResultId], EE: Encoder[String @@ MessageId]): Encoder[inline.ChosenInlineQuery] = deriveEncoder[inline.ChosenInlineQuery]
  implicit val inputContactMessageContentEncoder: Encoder[inline.InputContactMessageContent] = deriveEncoder[inline.InputContactMessageContent]
  implicit def inputVenueMessageContent(implicit E: Encoder[String @@ FoursquareId]): Encoder[inline.InputVenueMessageContent] = deriveEncoder[inline.InputVenueMessageContent]
  implicit val inputLocationMessageContentEncoder: Encoder[inline.InputLocationMessageContent] = deriveEncoder[inline.InputLocationMessageContent]
  implicit val inputTextMessageContentEncoder: Encoder[inline.InputTextMessageContent] = deriveEncoder[inline.InputTextMessageContent]

  implicit def inlineQueryResultArticleEncoder(implicit E: Encoder[String @@ ResultId]): Encoder[inline.InlineQueryResultArticle] = deriveEncoder[inline.InlineQueryResultArticle]
  implicit def inlineQueryResultPhotoEncoder(implicit E: Encoder[String @@ ResultId]): Encoder[inline.InlineQueryResultPhoto] = deriveEncoder[inline.InlineQueryResultPhoto]
  implicit def inlineQueryResultGifEncoder(implicit E: Encoder[String @@ ResultId]): Encoder[inline.InlineQueryResultGif] = deriveEncoder[inline.InlineQueryResultGif]
  implicit def inlineQueryResultMpeg4GifEncoder(implicit E: Encoder[String @@ ResultId]): Encoder[inline.InlineQueryResultMpeg4Gif] = deriveEncoder[inline.InlineQueryResultMpeg4Gif]
  implicit def inlineQueryResultVideoEncoder(implicit E: Encoder[String @@ ResultId]): Encoder[inline.InlineQueryResultVideo] = deriveEncoder[inline.InlineQueryResultVideo]
  implicit def inlineQueryResultAudioEncoder(implicit E: Encoder[String @@ ResultId]): Encoder[inline.InlineQueryResultAudio] = deriveEncoder[inline.InlineQueryResultAudio]
  implicit def inlineQueryResultVoiceEncoder(implicit E: Encoder[String @@ ResultId]): Encoder[inline.InlineQueryResultVoice] = deriveEncoder[inline.InlineQueryResultVoice]
  implicit def inlineQueryResultDocumentEncoder(implicit E: Encoder[String @@ ResultId]): Encoder[inline.InlineQueryResultDocument] = deriveEncoder[inline.InlineQueryResultDocument]
  implicit def inlineQueryResultLocationEncoder(implicit E: Encoder[String @@ ResultId]): Encoder[inline.InlineQueryResultLocation] = deriveEncoder[inline.InlineQueryResultLocation]
  implicit def inlineQueryResultVenueEncoder(implicit E: Encoder[String @@ ResultId], EE: Encoder[String @@ FoursquareId]): Encoder[inline.InlineQueryResultVenue] = deriveEncoder[inline.InlineQueryResultVenue]
  implicit def inlineQueryResultContactEncoder(implicit E: Encoder[String @@ ResultId]): Encoder[inline.InlineQueryResultContact] = deriveEncoder[inline.InlineQueryResultContact]
  implicit def inlineQueryResultCachedPhotoEncoder(implicit E: Encoder[String @@ ResultId]): Encoder[inline.InlineQueryResultCachedPhoto] = deriveEncoder[inline.InlineQueryResultCachedPhoto]
  implicit def inlineQueryResultCachedGifEncoder(implicit E: Encoder[String @@ ResultId]): Encoder[inline.InlineQueryResultCachedGif] = deriveEncoder[inline.InlineQueryResultCachedGif]
  implicit def inlineQueryResultCachedMpeg4GifEncoder(implicit E: Encoder[String @@ ResultId]): Encoder[inline.InlineQueryResultCachedMpeg4Gif] = deriveEncoder[inline.InlineQueryResultCachedMpeg4Gif]
  implicit def inlineQueryResultCachedStickerEncoder(implicit E: Encoder[String @@ ResultId]): Encoder[inline.InlineQueryResultCachedSticker] = deriveEncoder[inline.InlineQueryResultCachedSticker]

  implicit def answerInlineQueryEncoder(implicit E: Encoder[String @@ QueryId]): Encoder[inline.AnswerInlineQuery] = deriveEncoder[inline.AnswerInlineQuery]

  // Methods
  implicit val getMeJsonEncoder: Encoder[GetMe.type] = Encoder.instance(_ ⇒ io.circe.Json.Null)
  implicit def sendMessageJsonEncoder(implicit E: Encoder[Long @@ ChatId], EE: Encoder[Long @@ MessageId]): Encoder[SendMessage] = deriveEncoder[SendMessage]
  implicit def forwardMessageJsonEncoder[A: IsResourceId, B: IsResourceId](implicit E: Encoder[A @@ ChatId], EE: Encoder[B @@ ChatId], EEE: Encoder[Long @@ MessageId]): Encoder[ForwardMessage[A, B]] = deriveEncoder[ForwardMessage[A, B]]
  implicit def getUpdatesJsonEncoder(implicit E: Encoder[Long @@ UpdateId]): Encoder[GetUpdates] = deriveEncoder[GetUpdates]
  implicit def sendPhotoEncoder[A: IsResourceId](implicit E: Encoder[A @@ ChatId], EE: Encoder[Long @@ MessageId], EEE: Encoder[String @@ FileId]): Encoder[SendPhoto[A]] = deriveEncoder[SendPhoto[A]]
  implicit def sendAudioEncoder[A: IsResourceId](implicit E: Encoder[A @@ ChatId], EE: Encoder[Long @@ MessageId], EEE: Encoder[String @@ FileId]): Encoder[SendAudio[A]] = deriveEncoder[SendAudio[A]]
  implicit def sendDocumentEncoder[A: IsResourceId](implicit E: Encoder[A @@ ChatId], EE: Encoder[Long @@ MessageId], EEE: Encoder[String @@ FileId]): Encoder[SendDocument[A]] = deriveEncoder[SendDocument[A]]
  implicit def sendStickerEncoder[A: IsResourceId](implicit E: Encoder[A @@ ChatId], EE: Encoder[Long @@ MessageId], EEE: Encoder[String @@ FileId]): Encoder[SendSticker[A]] = deriveEncoder[SendSticker[A]]
  implicit def sendVideoEncoder[A: IsResourceId](implicit E: Encoder[A @@ ChatId], EE: Encoder[Long @@ MessageId], EEE: Encoder[String @@ FileId]): Encoder[SendVideo[A]] = deriveEncoder[SendVideo[A]]
  implicit def sendVoiceEncoder[A: IsResourceId](implicit E: Encoder[A @@ ChatId], EE: Encoder[Long @@ MessageId], EEE: Encoder[String @@ FileId]): Encoder[SendVoice[A]] = deriveEncoder[SendVoice[A]]
  implicit def sendLocationEncoder[A: IsResourceId](implicit E: Encoder[A @@ ChatId], EE: Encoder[Long @@ MessageId]): Encoder[SendLocation[A]] = deriveEncoder[SendLocation[A]]
  implicit def sendVenueEncoder[A: IsResourceId](implicit E: Encoder[A @@ ChatId], EE: Encoder[Long @@ MessageId]): Encoder[SendVenue[A]] = deriveEncoder[SendVenue[A]]
  implicit def sendContactEncoder[A: IsResourceId](implicit E: Encoder[A @@ ChatId], EE: Encoder[Long @@ MessageId]): Encoder[SendContact[A]] = deriveEncoder[SendContact[A]]
  implicit val chatActionEncoder: Encoder[ChatAction] = Encoder[String].contramap[ChatAction](e ⇒ snakenize {e.productPrefix})
  implicit def sendChatActionEncoder[A: IsResourceId](implicit E: Encoder[A @@ ChatId]): Encoder[SendChatAction[A]] = deriveEncoder[SendChatAction[A]]
  implicit def getUserProfilePhotosEncoder(implicit E: Encoder[Int @@ UserId]): Encoder[GetUserProfilePhotos] = deriveEncoder[GetUserProfilePhotos]
  implicit def getFileEncoder(implicit E: Encoder[String @@ FileId]): Encoder[GetFile] = deriveEncoder[GetFile]
  implicit def kickChatMemberEncoder[A: IsResourceId](implicit E: Encoder[A @@ ChatId], EE: Encoder[Int @@ UserId]): Encoder[KickChatMember[A]] = deriveEncoder[KickChatMember[A]]
  implicit def leaveChatEncoder[A: IsResourceId](implicit E: Encoder[A @@ ChatId]): Encoder[LeaveChat[A]] = deriveEncoder[LeaveChat[A]]
  implicit def unbanChatMemberEncoder[A: IsResourceId](implicit E: Encoder[A @@ ChatId], EE: Encoder[Int @@ UserId]): Encoder[UnbanChatMember[A]] = deriveEncoder[UnbanChatMember[A]]
  implicit def getChatEncoder[A: IsResourceId](implicit E: Encoder[A @@ ChatId]): Encoder[GetChat[A]] = deriveEncoder[GetChat[A]]
  implicit def getChatAdministratorsEncoder[A: IsResourceId](implicit E: Encoder[A @@ ChatId]): Encoder[GetChatAdministrators[A]] = deriveEncoder[GetChatAdministrators[A]]
  implicit def getChatMembersCountEncoder[A: IsResourceId](implicit E: Encoder[A @@ ChatId]): Encoder[GetChatMembersCount[A]] = deriveEncoder[GetChatMembersCount[A]]
  implicit def getChatMemberEncoder[A: IsResourceId](implicit E: Encoder[A @@ ChatId], EE: Encoder[Int @@ UserId]): Encoder[GetChatMember[A]] = deriveEncoder[GetChatMember[A]]
  implicit def answerCallbackQueryEncoder(implicit E: Encoder[String @@ QueryId]): Encoder[AnswerCallbackQuery] = deriveEncoder[AnswerCallbackQuery]
}
