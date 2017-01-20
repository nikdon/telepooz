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
  * Use this method to kick a user from a group or a supergroup. In the case of supergroups, the user will not be able
  * to return to the group on their own using invite links, etc., unless unbanned first. The bot must be an
  * administrator in the group for this to work. Returns True on success.
  *
  * @param chat_id  Unique identifier for the target group or username of the target supergroup
  *                 (in the format @supergroupusername)
  * @param user_id  Unique identifier of the target user
  */
case class KickChatMember(
    chat_id: String,
    user_id: Int
) extends Method[Response[Boolean]]
