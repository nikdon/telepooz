package com.github.nikdon.telepooz.helpers

import java.time.Duration
import java.util.Date

import com.github.nikdon.telepooz.model.MessageEntityType._
import com.github.nikdon.telepooz.model._
import com.github.nikdon.telepooz.model.inline.{ChosenInlineQuery, _}
import com.github.nikdon.telepooz.model.methods._
import com.github.nikdon.telepooz.tags._
import com.github.nikdon.telepooz.{IsResourceId, tags}
import org.scalacheck.Arbitrary._
import org.scalacheck.{Arbitrary, Gen}
import shapeless.tag._


object Arbitraries extends tags.Syntax {

  /** [[tags]] */
  implicit def chatIdArb[A: IsResourceId: Arbitrary]: Arbitrary[A @@ ChatId] = Arbitrary(arbitrary[A].map(_.chatId))
  implicit def fileIdArb[A: IsResourceId : Arbitrary]: Arbitrary[A @@ FileId] = Arbitrary(arbitrary[A].map(_.fileId))
  implicit def foursquareIdArv[A: IsResourceId : Arbitrary]: Arbitrary[A @@ FoursquareId] = Arbitrary(arbitrary[A].map(_.foursquareId))
  implicit def messageIdArb[A: IsResourceId: Arbitrary]: Arbitrary[A @@ MessageId] = Arbitrary(arbitrary[A].map(_.messageId))
  implicit def resultIdArb[A: IsResourceId: Arbitrary]: Arbitrary[A @@ ResultId] = Arbitrary(arbitrary[A].map(_.resultId))
  implicit def queryIdArb[A: IsResourceId : Arbitrary]: Arbitrary[A @@ QueryId] = Arbitrary(arbitrary[A].map(_.queryId))
  implicit def updateIdArb[A: IsResourceId: Arbitrary]: Arbitrary[A @@ UpdateId] = Arbitrary(arbitrary[A].map(_.updateId))
  implicit def userIdArb[A: IsResourceId: Arbitrary]: Arbitrary[A @@ UserId] = Arbitrary(arbitrary[A].map(_.userId))

  implicit val durationArb: Arbitrary[Duration] = Arbitrary(arbitrary[Int].map(s ⇒ Duration.ofSeconds(s.toLong)))

  implicit val audioArb               : Arbitrary[Audio]                = Arbitrary(Gen.resultOf(Audio))
  implicit val callbackQueryArb       : Arbitrary[CallbackQuery]        = Arbitrary(Gen.resultOf(CallbackQuery))
  implicit val fileArb                : Arbitrary[File]                 = Arbitrary(Gen.resultOf(File))
  implicit val chosenInlineQueryArb   : Arbitrary[ChosenInlineQuery]    = Arbitrary(Gen.resultOf(ChosenInlineQuery))
  implicit val locationArb            : Arbitrary[Location]             = Arbitrary(Gen.resultOf(Location))
  implicit val userArb                : Arbitrary[User]                 = Arbitrary(Gen.resultOf(User))
  implicit val parseModeArb           : Arbitrary[ParseMode]            = Arbitrary(Gen.oneOf(ParseMode.Markdown, ParseMode.HTML))
  implicit val inlineKeyboardButtonArb: Arbitrary[InlineKeyboardButton] = Arbitrary(Gen.resultOf(InlineKeyboardButton))
  implicit val inlineQueryArb         : Arbitrary[InlineQuery]          = Arbitrary(Gen.resultOf(InlineQuery))
  implicit val keyboardButtonArb      : Arbitrary[KeyboardButton]       = Arbitrary(Gen.resultOf(KeyboardButton))
  implicit val messageArb             : Arbitrary[Message]              = {
    val g: Gen[Message] = for {
      messageId ← arbitrary[Long @@ MessageId]
      date ← arbitrary[Date]
      chat ← arbitrary[Chat]
      from ← arbitrary[Option[User]]
      forwardFrom ← arbitrary[Option[User]]
      forwardFromChat ← arbitrary[Option[Chat]]
      forwardDate ← arbitrary[Option[Date]]
      replyToMessage ← arbitrary[Option[Message]]
      text ← arbitrary[Option[String]]
      entities ← arbitrary[Option[Vector[MessageEntity]]]
      audio ← arbitrary[Option[Audio]]
      document ← arbitrary[Option[Document]]
      photo ← arbitrary[Option[Vector[PhotoSize]]]
      sticker ← arbitrary[Option[Sticker]]
      video ← arbitrary[Option[Video]]
      voice ← arbitrary[Option[Voice]]
      caption ← arbitrary[Option[String]]
      contact ← arbitrary[Option[Contact]]
      location ← arbitrary[Option[Location]]
      venue ← arbitrary[Option[Venue]]
      newChatMember ← arbitrary[Option[User]]
      leftChatMember ← arbitrary[Option[User]]
      newChatTitle ← arbitrary[Option[String]]
      newChatPhoto ← arbitrary[Option[Vector[PhotoSize]]]
      deleteChatPhoto ← arbitrary[Option[Boolean]]
      groupChatCreated ← arbitrary[Option[Boolean]]
      superGroupChatCreated ← arbitrary[Option[Boolean]]
      channelChatCreated ← arbitrary[Option[Boolean]]
      migrateToChatId ← arbitrary[Option[Long @@ ChatId]]
      migrateFromChatId ← arbitrary[Option[Long @@ ChatId]]
      pinnedMessage ← arbitrary[Option[Message]]
    } yield Message(
      messageId, date, chat, from, forwardFrom, forwardFromChat, forwardDate, replyToMessage, text, entities, audio,
      document, photo, sticker, video, voice, caption, contact, location, venue, newChatMember, leftChatMember,
      newChatTitle, newChatPhoto, deleteChatPhoto, groupChatCreated, superGroupChatCreated, channelChatCreated,
      migrateToChatId, migrateFromChatId, pinnedMessage
    )

    Arbitrary(g)
  }
  implicit val updateArb              : Arbitrary[Update]               = Arbitrary(Gen.resultOf(Update))
  implicit val memberStatusArb        : Arbitrary[MemberStatus]         = Arbitrary(
    Gen.oneOf(MemberStatus.Creator, MemberStatus.Administrator, MemberStatus.Member, MemberStatus.Left, MemberStatus.Kicked)
  )
  implicit val chatMemberArb          : Arbitrary[ChatMember]           = Arbitrary(Gen.resultOf(ChatMember))
  implicit val chatTypeArb            : Arbitrary[ChatType]             = Arbitrary(
    Gen.oneOf(ChatType.Group, ChatType.Channel, ChatType.Private, ChatType.SuperGroup)
  )
  implicit val chatArb                : Arbitrary[Chat]                 = Arbitrary(Gen.resultOf(Chat))
  implicit val venueArb               : Arbitrary[Venue]                = Arbitrary(Gen.resultOf(Venue))
  implicit val contactAbr             : Arbitrary[Contact]              = Arbitrary(Gen.resultOf(Contact))
  implicit val messageEntityTypeAbt   : Arbitrary[MessageEntityType]    = Arbitrary(
    Gen.oneOf(Mention, Hashtag, BotCommand, Url, Email, Bold, Italic, Code, Pre, TextLink)
  )
  implicit val messageEntityArb       : Arbitrary[MessageEntity]        = Arbitrary(Gen.resultOf(MessageEntity))
  implicit val documentArb            : Arbitrary[Document]             = Arbitrary(Gen.resultOf(Document))
  implicit val photoSizeArb           : Arbitrary[PhotoSize]            = Arbitrary(Gen.resultOf(PhotoSize))
  implicit val stickerArb             : Arbitrary[Sticker]              = Arbitrary(Gen.resultOf(Sticker))
  implicit val videoArb               : Arbitrary[Video]                = Arbitrary(Gen.resultOf(Video))
  implicit val voiceArb               : Arbitrary[Voice]                = Arbitrary(Gen.resultOf(Voice))
  implicit val userProfilePhotosArb   : Arbitrary[UserProfilePhotos]    = Arbitrary(Gen.resultOf(UserProfilePhotos))

  /** [[ReplyMarkup]] */
  implicit val replyKeyboardMarkupArb : Arbitrary[ReplyKeyboardMarkup]  = Arbitrary(Gen.resultOf(ReplyKeyboardMarkup))
  implicit val inlineKeyboardMarkupArb: Arbitrary[InlineKeyboardMarkup] = Arbitrary(Gen.resultOf(InlineKeyboardMarkup))
  implicit val replyKeyboardHideArb   : Arbitrary[ReplyKeyboardHide]    = Arbitrary(Gen.resultOf(ReplyKeyboardHide))
  implicit val forceReplyArb          : Arbitrary[ForceReply]           = Arbitrary(Gen.resultOf(ForceReply))
  implicit val replyMarkupArb         : Arbitrary[ReplyMarkup]          = Arbitrary(Gen.oneOf(
    inlineKeyboardMarkupArb.arbitrary,
    replyKeyboardHideArb.arbitrary,
    forceReplyArb.arbitrary
  ))

  implicit val answerInlineQueryArb: Arbitrary[AnswerInlineQuery] = Arbitrary(Gen.resultOf(AnswerInlineQuery))

  /** [[InputMessageContent]] */
  implicit val inputTextMessageContentArb    : Arbitrary[InputTextMessageContent]     = Arbitrary(Gen.resultOf(InputTextMessageContent))
  implicit val inputLocationMessageContentArb: Arbitrary[InputLocationMessageContent] = Arbitrary(Gen.resultOf(InputLocationMessageContent))
  implicit val inputVenueMessageContentAbr   : Arbitrary[InputVenueMessageContent]    = Arbitrary(Gen.resultOf(InputVenueMessageContent))
  implicit val inputContactMessageContentArb : Arbitrary[InputContactMessageContent]  = Arbitrary(Gen.resultOf(InputContactMessageContent))
  implicit val inputMessageContentArb        : Arbitrary[InputMessageContent]         = Arbitrary(Gen.oneOf(
    inputTextMessageContentArb.arbitrary,
    inputLocationMessageContentArb.arbitrary,
    inputVenueMessageContentAbr.arbitrary,
    inputContactMessageContentArb.arbitrary
  ))

  /** [[InlineQueryResult]] */
  implicit val inlineQueryResultArticleArb    : Arbitrary[InlineQueryResultArticle]     = Arbitrary(Gen.resultOf(InlineQueryResultArticle))
  implicit val inlineQueryResultPhotoArb      : Arbitrary[InlineQueryResultPhoto]       = Arbitrary(Gen.resultOf(InlineQueryResultPhoto))
  implicit val inlineQueryResultGifArb        : Arbitrary[InlineQueryResultGif]         = Arbitrary(Gen.resultOf(InlineQueryResultGif))
  implicit val inlineQueryResultMpeg4GifArb   : Arbitrary[InlineQueryResultMpeg4Gif]    = Arbitrary(Gen.resultOf(InlineQueryResultMpeg4Gif))
  implicit val inlineQueryResultVideoArb      : Arbitrary[InlineQueryResultVideo]       = Arbitrary(Gen.resultOf(InlineQueryResultVideo))
  implicit val inlineQueryResultAudioArb      : Arbitrary[InlineQueryResultAudio]       = Arbitrary(Gen.resultOf(InlineQueryResultAudio))
  implicit val inlineQueryResultVoiceArb      : Arbitrary[InlineQueryResultVoice]       = Arbitrary(Gen.resultOf(InlineQueryResultVoice))
  implicit val inlineQueryResultDocumentArb   : Arbitrary[InlineQueryResultDocument]    = Arbitrary(Gen.resultOf(InlineQueryResultDocument))
  implicit val inlineQueryResultLocationArb   : Arbitrary[InlineQueryResultLocation]    = Arbitrary(Gen.resultOf(InlineQueryResultLocation))
  implicit val inlineQueryResultVenueArb      : Arbitrary[InlineQueryResultVenue]       = Arbitrary(Gen.resultOf(InlineQueryResultVenue))
  implicit val inlineQueryResultContactArb    : Arbitrary[InlineQueryResultContact]     = Arbitrary(Gen.resultOf(InlineQueryResultContact))
  implicit val inlineQueryResultCachedPhotoArb: Arbitrary[InlineQueryResultCachedPhoto] = Arbitrary(Gen.resultOf(InlineQueryResultCachedPhoto))
  implicit val inlineQueryResultCachedGifArb  : Arbitrary[InlineQueryResultCachedGif]   = Arbitrary(Gen.resultOf(InlineQueryResultCachedGif))
  implicit val inlineQueryResultArb           : Arbitrary[InlineQueryResult]            = Arbitrary(Gen.oneOf(
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
    inlineQueryResultCachedGifArb.arbitrary
  ))

  /** Methods */
  implicit val sendMessageArb          : Arbitrary[SendMessage]                 = Arbitrary(Gen.resultOf(SendMessage))
  implicit val forwardMessageArb       : Arbitrary[ForwardMessage[Long, Long]]  = Arbitrary(Gen.resultOf(ForwardMessage[Long, Long] _))
  implicit val answerCallbackQueryArb  : Arbitrary[AnswerCallbackQuery]         = Arbitrary(Gen.resultOf(AnswerCallbackQuery))
  implicit val getChatAdministratorsArb: Arbitrary[GetChatAdministrators[Long]] = Arbitrary(Gen.resultOf(GetChatAdministrators[Long] _))
  implicit val getChatMembersCountArb  : Arbitrary[GetChatMembersCount[Long]]   = Arbitrary(Gen.resultOf(GetChatMembersCount[Long] _))
  implicit val getChatMemberArb        : Arbitrary[GetChatMember[Long]]         = Arbitrary(Gen.resultOf(GetChatMember[Long] _))
  implicit val getChatArb              : Arbitrary[GetChat[Long]]               = Arbitrary(Gen.resultOf(GetChat[Long] _))
  implicit val chatActionArb           : Arbitrary[ChatAction]                  = Arbitrary(Gen.oneOf(
    ChatAction.Typing, ChatAction.UploadPhoto, ChatAction.RecordVideo, ChatAction.UploadVideo,
    ChatAction.RecordAudio, ChatAction.UploadAudio, ChatAction.UploadDocument, ChatAction.FindLocation
  ))
  implicit val sendChatActionArb       : Arbitrary[SendChatAction[Long]]        = Arbitrary(Gen.resultOf(SendChatAction[Long] _))
}
