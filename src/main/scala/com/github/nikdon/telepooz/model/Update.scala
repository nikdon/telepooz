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

import com.github.nikdon.telepooz.model.inline.{ChosenInlineQuery, InlineQuery}
import com.github.nikdon.telepooz.tags.UpdateId
import shapeless.tag.@@


/**
  * This object represents an incoming update. Only one of the optional parameters can be present in any given update.
  *
  * @param update_id            The update‘s unique identifier. Update identifiers start from a certain positive number
  *                             and increase sequentially. This ID becomes especially handy if you’re using Webhooks,
  *                             since it allows you to ignore repeated updates or to restore the correct update
  *                             sequence, should they get out of order.
  * @param message              New incoming message of any kind — text, photo, sticker, etc.
  * @param edited_message       New version of a message that is known to the bot and was edited
  * @param inline_query         New incoming inline query
  * @param chosen_inline_result The result of an inline query that was chosen by a user and sent to their chat partner
  * @param callback_query       New incoming callback query
  */
case class Update(update_id: Long @@ UpdateId,
                  message: Option[Message] = None,
                  edited_message: Option[Message] = None,
                  inline_query: Option[InlineQuery] = None,
                  chosen_inline_result: Option[ChosenInlineQuery] = None,
                  callback_query: Option[CallbackQuery] = None)
