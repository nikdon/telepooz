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
  * Use this method for your bot to leave a group, supergroup or channel. Returns True on success.
  *
  * @param chat_id  Unique identifier for the target chat or username of the target supergroup or channel
  *                 (in the format @channelusername)
  */
case class LeaveChat(chat_id: String)  extends Method[Response[Boolean]]
