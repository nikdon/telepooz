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

package com.github.nikdon.telepooz.model.methods

import com.github.nikdon.telepooz.IsResourceId
import com.github.nikdon.telepooz.model.ReplyMarkup
import com.github.nikdon.telepooz.tags.{ChatId, MessageId}
import shapeless.tag.@@


/**
  * Use this method to edit only the reply markup of messages sent by the bot or via the bot (for inline bots). On
  * success, if edited message is sent by the bot, the edited Message is returned, otherwise True is returned.
  *
  * @param chat_id            Required if inline_message_id is not specified. Unique identifier for the target chat or
  *                           username of the target channel (in the format @channelusername)
  * @param message_id         Required if inline_message_id is not specified. Unique identifier of the sent message
  * @param inline_message_id  Required if chat_id and message_id are not specified. Identifier of the inline message
  * @param reply_markup       A JSON-serialized object for an inline keyboard.
  */
case class EditMessageReplyMarkup[A: IsResourceId](
  chat_id: A @@ ChatId,
  message_id: Long @@ MessageId,
  inline_message_id: String @@ MessageId,
  reply_markup: Option[ReplyMarkup] = None
)
