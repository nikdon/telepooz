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

import com.github.nikdon.telepooz.model.{Message, Response}

/**
  * Use this method to forward messages of any kind. On success, the sent Message is returned.
  *
  * @param chat_id              Unique identifier for the target chat or username of the target channel
  *                             (in the format @channelusername)
  * @param from_chat_id         Unique identifier for the chat where the original message was sent (or channel username
  *                             in the format @channelusername)
  * @param message_id           Unique message identifier
  * @param disable_notification Sends the message silently. iOS users will not receive a notification, Android users
  *                             will receive a notification with no sound.
  */
case class ForwardMessage(
    chat_id: String,
    from_chat_id: String,
    message_id: Long,
    disable_notification: Option[Boolean] = None
) extends Method[Response[Message]]
