package com.github.nikdon.telepooz

import com.github.nikdon.telepooz.helpers.Arbitraries._
import com.github.nikdon.telepooz.helpers.CirceSuite
import com.github.nikdon.telepooz.model.EqInstances._
import com.github.nikdon.telepooz.model._
import com.github.nikdon.telepooz.model.inline.AnswerInlineQuery
import com.github.nikdon.telepooz.model.methods._
import com.github.nikdon.telepooz.raw.{CirceDecoders, CirceEncoders}

class ModelSuite extends CirceSuite with CirceDecoders with CirceEncoders {
  checkCodec[Animation]("Animation")
  checkCodec[Audio]("Audio")
  checkCodec[CallbackQuery]("CallbackQuery")
  checkCodec[ChatMember]("ChatMember")
  checkCodec[Chat]("Chat")
  checkCodec[Contact]("Contact")
  checkCodec[Document]("Document")
  checkCodec[File]("File")
  checkCodec[GameHighScore]("GameHighScore")
  checkCodec[Game]("Game")
  checkCodec[InlineKeyboardButton]("InlineKeyboardButton")
  checkCodec[KeyboardButton]("KeyboardButton")
  checkCodec[Location]("Location")
  checkCodec[MessageEntity]("MessageEntity")
  checkCodec[Message]("Message")
  checkCodec[PhotoSize]("PhotoSize")
  checkCodec[Sticker]("Sticker")
  checkCodec[Update]("Update")
  checkCodec[UserProfilePhotos]("UserProfilePhotos")
  checkCodec[User]("User")
  checkCodec[Venue]("Venue")
  checkCodec[Voice]("Voice")
  checkCodec[Video]("Video")

  checkEncodeIsNotNull[AnswerCallbackQuery]("AnswerCallbackQuery")
  checkEncodeIsNotNull[EditMessageCaption[Long]]("EditMessageCaption")
  checkEncodeIsNotNull[EditMessageReplyMarkup[Long]]("EditMessageReplyMarkup")
  checkEncodeIsNotNull[EditMessageText[Long]]("EditMessageText")
  checkEncodeIsNotNull[ForwardMessage[Long, Long]]("ForwardMessage")
  checkEncodeIsNotNull[GetChatAdministrators[Long]]("GetChatAdministrators")
  checkEncodeIsNotNull[GetChatMembersCount[Long]]("GetChatMembersCount")
  checkEncodeIsNotNull[GetChatMember[Long]]("GetChatMember")
  checkEncodeIsNotNull[GetChat[Long]]("GetChat")
  checkEncodeIsNull[GetMe.type]("GetMe")
  checkEncodeIsNotNull[SendChatAction[Long]]("SendChatAction")
  checkEncodeIsNotNull[SendMessage[Long]]("SendMessage")

  checkEncodeIsNotNull[AnswerInlineQuery]("AnswerInlineQuery")
}
