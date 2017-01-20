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

import com.github.nikdon.telepooz._


/**
  * This object represents a chat.
  *
  * @param id         Unique identifier for this chat, not exceeding 1e13 by absolute value
  * @param `type`     Type of chat, can be either “private”, “group”, “supergroup” or “channel”
  * @param title      Title, for channels and group chats
  * @param username   Username, for private chats and channels if available
  * @param first_name First name of the other party in a private chat
  * @param last_name  Last name of the other party in a private chat
  * @param all_members_are_admins True if a group has ‘All Members Are Admins’ enabled.
  */
case class Chat(
  id: String,
  `type`: ChatType,
  title: Option[String] = None,
  username: Option[String] = None,
  first_name: Option[String] = None,
  last_name: Option[String] = None,
  all_members_are_admins: Option[Boolean] = None
)


sealed trait ChatType extends Product with Serializable {
  def name: String = this.productPrefix
}

object ChatType {

  case object Private extends ChatType {override val name: String = super.name}
  case object Group extends ChatType {override val name: String = super.name}
  case object SuperGroup extends ChatType {override val name: String = super.name}
  case object Channel extends ChatType {override val name: String = super.name}

  def unsafe(str: String): ChatType = str match {
    case Private.name    ⇒ Private
    case Group.name      ⇒ Group
    case SuperGroup.name ⇒ SuperGroup
    case Channel.name    ⇒ Channel
    case _               ⇒ unexpected(str)
  }
}
