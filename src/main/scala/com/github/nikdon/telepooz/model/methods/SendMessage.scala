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

import com.github.nikdon.telepooz.model.{Message, ParseMode, ReplyMarkup, Response}


/**
  * Use this method to send text messages. On success, the sent Message is returned.
  *
  * @param chat_id                    Unique identifier for the target chat or username of the target channel
  *                                   (in the format @channelusername)
  * @param text                       Text of the message to be sent
  * @param parse_mode                 Send Markdown or HTML, if you want Telegram apps to show bold, italic,
  *                                   fixed-width text or inline URLs in your bot's message.
  * @param disable_web_page_preview   Disables link previews for links in this message
  * @param disable_notification       Sends the message silently. iOS users will not receive a notification,
  *                                   Android users will receive a notification with no sound.
  * @param reply_to_message_id        If the message is a reply, ID of the original message
  * @param reply_markup               Additional interface options. A JSON-serialized object for an inline keyboard,
  *                                   custom reply keyboard, instructions to hide reply keyboard or to force a reply
  *                                   from the user.
  */
case class SendMessage(
  chat_id: Long,
  text: String,
  parse_mode: Option[ParseMode] = None,
  disable_web_page_preview: Option[Boolean] = None,
  disable_notification: Option[Boolean] = None,
  reply_to_message_id: Option[Long] = None,
  reply_markup: Option[ReplyMarkup] = None
) extends Method[Response[Message]]
