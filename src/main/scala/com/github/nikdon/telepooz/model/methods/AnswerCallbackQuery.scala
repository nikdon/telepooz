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

import com.github.nikdon.telepooz.tags.QueryId
import shapeless.tag.@@


/**
  * Use this method to send answers to callback queries sent from inline keyboards. The answer will be displayed to the
  * user as a notification at the top of the chat screen or as an alert. On success, True is returned.
  *
  * @param callback_query_id  Unique identifier for the query to be answered
  * @param text               Text of the notification. If not specified, nothing will be shown to the user
  * @param show_alert         If true, an alert will be shown by the client instead of a notification at the top of the
  *                           chat screen. Defaults to false.
  * @param url                URL that will be opened by the user's client. If you have created a Game and accepted the
  *                           conditions via @Botfather, specify the URL that opens your game – note that this will only
  *                           work if the query comes from a callback_game button.
  */
case class AnswerCallbackQuery(callback_query_id: String @@ QueryId,
                               text: Option[String] = None,
                               show_alert: Option[Boolean] = None,
                               url: Option[String] = None)
