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
import shapeless.tag.@@


/**
  * Use this method to get data for high score tables. Will return the score of the specified user and several of his
  * neighbors in a game. On success, returns an Array of GameHighScore objects.
  *
  * @param user_id            Target user id
  * @param chat_id            Required if inline_message_id is not specified. Unique identifier for the target chat
  *                           (or username of the target channel in the format @channelusername)
  * @param message_id         Required if inline_message_id is not specified. Unique identifier of the sent message
  * @param inline_message_id  	Required if chat_id and message_id are not specified. Identifier of the inline message
  */
case class GetGameHighScores[A: IsResourceId](
  user_id: Long @@ UserId,
  chat_id: Option[A @@ ChatId] = None,
  message_id: Option[Long @@ MessageId] = None,
  inline_message_id: Option[String @@ MessageId] = None
)
