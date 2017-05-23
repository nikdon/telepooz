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

import java.time.Duration

import com.github.nikdon.telepooz.model.{Message, ReplyMarkup, Response}

/**
  * As of v.4.0, Telegram clients support rounded square mp4 videos of up to 1 minute long.
  * Use this method to send video messages. On success, the sent Message is returned.
  *
  * @param chat_id              Unique identifier for the target chat or username of the target channel
  *                             (in the format @channelusername)
  * @param video                Video note to send. Pass a file_id as String to send a video note that
  *                             exists on the Telegram servers (recommended) or upload a new video using
  *                             multipart/form-data.
  * @param duration             Duration of sent video in seconds
  * @param length               Video width and height
  * @param disable_notification Sends the message silently. iOS users will not receive a notification,
  *                             Android users will receive a notification with no sound.
  * @param reply_to_message_id  If the message is a reply, ID of the original message
  * @param reply_markup         Additional interface options. A JSON-serialized object for an inline keyboard,
  *                             custom reply keyboard, instructions to remove reply keyboard or to force a reply
  *                             from the user.
  */
case class SendVideoNote(
    chat_id: String,
    video: String, // TODO Add file upload
    duration: Option[Duration] = None,
    length: Option[Int] = None,
    disable_notification: Option[Boolean] = None,
    reply_to_message_id: Option[Long] = None,
    reply_markup: Option[ReplyMarkup] = None
) extends Method[Response[Message]]
