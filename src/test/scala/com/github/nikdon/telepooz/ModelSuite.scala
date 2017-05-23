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

package com.github.nikdon.telepooz

import com.github.nikdon.telepooz.helpers.Arbitraries._
import com.github.nikdon.telepooz.helpers.CirceSuite
import com.github.nikdon.telepooz.model.EqInstances._
import com.github.nikdon.telepooz.model._
import com.github.nikdon.telepooz.model.inline.AnswerInlineQuery
import com.github.nikdon.telepooz.model.methods._
import com.github.nikdon.telepooz.json.{CirceDecoders, CirceEncoders}
import com.github.nikdon.telepooz.model.payments._

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
  checkCodec[VideoNote]("VideoNote")
  checkCodec[Invoice]("Invoice")
  checkCodec[LabeledPrice]("LabeledPrice")
  checkCodec[OrderInfo]("OrderInfo")
  checkCodec[PreCheckoutQuery]("PreCheckoutQuery")
  checkCodec[ShippingAddress]("ShippingAddress")
  checkCodec[ShippingOption]("ShippingOption")
  checkCodec[ShippingQuery]("ShippingQuery")


  checkEncodeIsNotNull[AnswerCallbackQuery]("AnswerCallbackQuery")
  checkEncodeIsNotNull[EditMessageCaption]("EditMessageCaption")
  checkEncodeIsNotNull[EditMessageReplyMarkup]("EditMessageReplyMarkup")
  checkEncodeIsNotNull[EditMessageText]("EditMessageText")
  checkEncodeIsNotNull[ForwardMessage]("ForwardMessage")
  checkEncodeIsNotNull[GetChatAdministrators]("GetChatAdministrators")
  checkEncodeIsNotNull[GetChatMembersCount]("GetChatMembersCount")
  checkEncodeIsNotNull[GetChatMember]("GetChatMember")
  checkEncodeIsNotNull[GetChat]("GetChat")
  checkEncodeIsNull[GetMe.type]("GetMe")
  checkEncodeIsNotNull[SendChatAction]("SendChatAction")
  checkEncodeIsNotNull[SendMessage]("SendMessage")

  checkEncodeIsNotNull[AnswerInlineQuery]("AnswerInlineQuery")
}
