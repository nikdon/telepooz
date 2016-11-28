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
import com.github.nikdon.telepooz.tags.{ChatId, MessageId, UserId}
import shapeless.tag._


/**
  * Use this method to set the score of the specified user in a game. On success, if the message was sent by the bot,
  * returns the edited Message, otherwise returns True. Returns an error, if the new score is not greater than the
  * user's current score in the chat.
  *
  * @param user_id            User identifier
  * @param score              New score, must be positive
  * @param chat_id            Required if inline_message_id is not specified. Unique identifier for the target chat
  *                           (or username of the target channel in the format @channelusername)
  * @param message_id         Required if inline_message_id is not specified. Unique identifier of the sent message
  * @param inline_message_id  Required if chat_id and message_id are not specified. Identifier of the inline message
  * @param edit_message       Pass True, if the game message should be automatically edited to include the current scoreboard
  */
case class SetGameScore[A: IsResourceId](
  user_id: Long @@ UserId,
  score: Int,
  chat_id: A @@ ChatId,
  message_id: Option[Long @@ MessageId] = None,
  inline_message_id: Option[String @@ MessageId] = None,
  edit_message: Option[Boolean] = None
)
