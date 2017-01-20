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


/**
  * Use this method to send answers to an inline query. On success, True is returned.
  * No more than 50 results per query are allowed.
  *
  * @param inline_query_id      Unique identifier for the answered query
  * @param results              A JSON-serialized array of results for the inline query
  * @param cache_time           The maximum amount of time in seconds that the result of the inline query may be cached
  *                             on the server. Defaults to 300.
  * @param is_personal          Pass True, if results may be cached on the server side only for the user that sent the
  *                             query. By default, results may be returned to any user who sends the same query
  * @param next_offset          Pass the offset that a client should send in the next query with the same text to
  *                             receive more results. Pass an empty string if there are no more results or if you don‘t
  *                             support pagination. Offset length can’t exceed 64 bytes.
  * @param switch_pm_text       If passed, clients will display a button with specified text that switches the user to
  *                             a private chat with the bot and sends the bot a start message with the parameter
  *                             switch_pm_parameter
  * @param switch_pm_parameter  Parameter for the start message sent to the bot when user presses the switch button
  */
case class AnswerInlineQuery(inline_query_id: String,
                             results: Vector[InlineQueryResult],
                             cache_time: Option[Int] = None,
                             is_personal: Option[Boolean] = None,
                             next_offset: Option[String] = None,
                             switch_pm_text: Option[String] = None,
                             switch_pm_parameter: Option[String] = None)
