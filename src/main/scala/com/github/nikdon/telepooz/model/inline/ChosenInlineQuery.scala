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

package com.github.nikdon.telepooz.model.inline

import com.github.nikdon.telepooz.model.{Location, User}
import com.github.nikdon.telepooz.tags.{MessageId, ResultId}
import shapeless.tag.@@


/**
  * Represents a result of an inline query that was chosen by the user and sent to their chat partner.
  *
  * @param result_id          The unique identifier for the result that was chosen
  * @param from               The user that chose the result
  * @param location           Sender location, only for bots that require user location
  * @param inline_message_id  Identifier of the sent inline message. Available only if there is an inline keyboard
  *                           attached to the message. Will be also received in callback queries and can be used
  *                           to edit the message.
  * @param query              The query that was used to obtain the result
  */
case class ChosenInlineQuery(result_id: String @@ ResultId,
                             from: User,
                             location: Option[Location] = None,
                             inline_message_id: Option[String @@ MessageId] = None,
                             query: String)
