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


/**
  * This object represents an incoming callback query from a callback button in an inline keyboard.
  * If the button that originated the query was attached to a message sent by the bot,
  * the field message will be presented. If the button was attached to a message
  * sent via the bot (in inline mode), the field [[inline_message_id]] will be presented.
  *
  * @param id                 Unique identifier for this query
  * @param from               Sender
  * @param message            Message with the callback button that originated the query.
  *                           Note that message content and message date will not be available if the message is too old
  * @param inline_message_id  Identifier of the message sent via the bot in inline mode, that originated the query
  * @param data               Data associated with the callback button.
  *                           Be aware that a bad client can send arbitrary data in this field
  */
case class CallbackQuery(id: String,
                         from: Option[User] = None,
                         message: Option[Message] = None,
                         inline_message_id: Option[String] = None,
                         data: String)
