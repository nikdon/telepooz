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
import com.github.nikdon.telepooz.tags.QueryId
import shapeless.tag.@@


/**
  * This object represents an incoming inline query. When the user sends an empty query,
  * your bot could return some default or trending results.
  *
  * @param id       Unique identifier for this query
  * @param from     Sender
  * @param location Sender location, only for bots that request user location
  * @param query    Text of the query (up to 512 characters)
  * @param offset   Offset of the results to be returned, can be controlled by the bot
  */
case class InlineQuery(id: String @@ QueryId,
                       from: User,
                       location: Option[Location] = None,
                       query: String,
                       offset: String)
