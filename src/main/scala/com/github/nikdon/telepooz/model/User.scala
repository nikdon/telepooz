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

/**
  * This object represents a Telegram user or bot.
  *
  * @param id             Unique identifier for this user or bot
  * @param first_name     User‘s or bot’s first name
  * @param last_name      User‘s or bot’s last name
  * @param username       User‘s or bot’s username
  * @param language_code  IETF language tag of the user's language
  */
case class User(id: Int,
                first_name: String,
                last_name: Option[String] = None,
                username: Option[String] = None,
                language_code: Option[String] = None)
