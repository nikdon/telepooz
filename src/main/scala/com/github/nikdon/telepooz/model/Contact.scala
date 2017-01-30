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
  * This object represents a phone contact.
  *
  * @param phone_number  Contact's phone number
  * @param first_name    Contact's first name
  * @param last_name     Contact's last name
  * @param user_id       Contact's user identifier in Telegram
  */
case class Contact(phone_number: String,
                   first_name: String,
                   last_name: Option[String],
                   user_id: Option[Int])
