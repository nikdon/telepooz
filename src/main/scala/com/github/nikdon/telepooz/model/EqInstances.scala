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

package com.github.nikdon.telepooz.model

import cats.Eq
import com.github.nikdon.telepooz.model.inline.AnswerInlineQuery
import com.github.nikdon.telepooz.model.methods._
import com.github.nikdon.telepooz.model.payments._

object EqInstances {
  implicit val eqAnimation: Eq[Animation]                       = Eq.fromUniversalEquals
  implicit val eqAudio: Eq[Audio]                               = Eq.fromUniversalEquals
  implicit val eqCallbackQuery: Eq[CallbackQuery]               = Eq.fromUniversalEquals
  implicit val eqChatMember: Eq[ChatMember]                     = Eq.fromUniversalEquals
  implicit val eqChat: Eq[Chat]                                 = Eq.fromUniversalEquals
  implicit val eqContact: Eq[Contact]                           = Eq.fromUniversalEquals
  implicit val eqDocument: Eq[Document]                         = Eq.fromUniversalEquals
  implicit val eqFile: Eq[File]                                 = Eq.fromUniversalEquals
  implicit val eqForceReply: Eq[ForceReply]                     = Eq.fromUniversalEquals
  implicit val eqGameHighScore: Eq[GameHighScore]               = Eq.fromUniversalEquals
  implicit val eqGame: Eq[Game]                                 = Eq.fromUniversalEquals
  implicit val eqInlineKeyboardButton: Eq[InlineKeyboardButton] = Eq.fromUniversalEquals
  implicit val eqInlineKeyboardMarkup: Eq[InlineKeyboardMarkup] = Eq.fromUniversalEquals
  implicit val eqKeyboardButton: Eq[KeyboardButton]             = Eq.fromUniversalEquals
  implicit val eqLocation: Eq[Location]                         = Eq.fromUniversalEquals
  implicit val eqMessageEntity: Eq[MessageEntity]               = Eq.fromUniversalEquals
  implicit val eqMessage: Eq[Message]                           = Eq.fromUniversalEquals
  implicit val eqPhotoSize: Eq[PhotoSize]                       = Eq.fromUniversalEquals
  implicit val eqReplyKeyboardHide: Eq[ReplyKeyboardHide]       = Eq.fromUniversalEquals
  implicit val eqReplyKeyboardMarkup: Eq[ReplyKeyboardMarkup]   = Eq.fromUniversalEquals
  implicit val eqSticker: Eq[Sticker]                           = Eq.fromUniversalEquals
  implicit val eqUpdate: Eq[Update]                             = Eq.fromUniversalEquals
  implicit val eqUserProfilePhotos: Eq[UserProfilePhotos]       = Eq.fromUniversalEquals
  implicit val eqUser: Eq[User]                                 = Eq.fromUniversalEquals
  implicit val eqVenue: Eq[Venue]                               = Eq.fromUniversalEquals
  implicit val eqVoice: Eq[Voice]                               = Eq.fromUniversalEquals
  implicit val eqVideo: Eq[Video]                               = Eq.fromUniversalEquals
  implicit val eqVideoNote: Eq[VideoNote]                       = Eq.fromUniversalEquals

  // Payments
  implicit val invoiceEq: Eq[Invoice]                     = Eq.fromUniversalEquals
  implicit val labeledPriceEq: Eq[LabeledPrice]           = Eq.fromUniversalEquals
  implicit val orderInfoEq: Eq[OrderInfo]                 = Eq.fromUniversalEquals
  implicit val preCheckoutQueryEq: Eq[PreCheckoutQuery]   = Eq.fromUniversalEquals
  implicit val shippingAddressEq: Eq[ShippingAddress]     = Eq.fromUniversalEquals
  implicit val shippingOptionEq: Eq[ShippingOption]       = Eq.fromUniversalEquals
  implicit val shippingQueryEq: Eq[ShippingQuery]         = Eq.fromUniversalEquals
  implicit val successfulPaymentEq: Eq[SuccessfulPayment] = Eq.fromUniversalEquals

  implicit val eqAnswerCallbackQuery: Eq[AnswerCallbackQuery]       = Eq.fromUniversalEquals
  implicit val eqEditMessageCaption: Eq[EditMessageCaption]         = Eq.fromUniversalEquals
  implicit val eqEditMessageReplyMarkup: Eq[EditMessageReplyMarkup] = Eq.fromUniversalEquals
  implicit val eqEditMessageText: Eq[EditMessageText]               = Eq.fromUniversalEquals
  implicit val eqForwardMessage: Eq[ForwardMessage]                 = Eq.fromUniversalEquals
  implicit val eqGetChatAdministrators: Eq[GetChatAdministrators]   = Eq.fromUniversalEquals
  implicit val eqGetChatMembersCount: Eq[GetChatMembersCount]       = Eq.fromUniversalEquals
  implicit val eqGetChatMember: Eq[GetChatMember]                   = Eq.fromUniversalEquals
  implicit val eqGetChat: Eq[GetChat]                               = Eq.fromUniversalEquals
  implicit val eqGetMe: Eq[GetMe.type]                              = Eq.fromUniversalEquals
  implicit val eqSendChatAction: Eq[SendChatAction]                 = Eq.fromUniversalEquals
  implicit val eqSendMessage: Eq[SendMessage]                       = Eq.fromUniversalEquals

  implicit val eqAnswerInlineQuery: Eq[AnswerInlineQuery] = Eq.fromUniversalEquals
}
