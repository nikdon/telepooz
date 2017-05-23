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

import com.github.nikdon.telepooz.model.Response

/**
  * Use this method to delete a message. A message can only be deleted if it was sent less than 48 hours ago.
  * Any such recently sent outgoing message may be deleted. Additionally, if the bot is an administrator in a
  * group chat, it can delete any message. If the bot is an administrator in a supergroup, it can delete messages
  * from any other user and service messages about people joining or leaving the group (other types of service
  * messages may only be removed by the group creator). In channels, bots can only remove their own messages.
  * Returns True on success.
  *
  * @param chat_id    Unique identifier for the target chat or username of the target channel
  *                   (in the format @channelusername)
  * @param message_id Identifier of the message to delete
  */
case class DeleteMessage(chat_id: String, message_id: Long) extends Method[Response[Boolean]]
