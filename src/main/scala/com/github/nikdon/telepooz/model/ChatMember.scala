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
  * This object contains information about one member of the chat.
  *
  * @param user   Information about the user
  * @param status The member's status in the chat. Can be “creator”, “administrator”, “member”, “left” or “kicked”
  */
case class ChatMember(user: User,
                      status: MemberStatus)

sealed trait MemberStatus extends Product with Serializable {def name: String = productPrefix}
object MemberStatus {
  case object Creator extends MemberStatus {override val name = super.name}
  case object Administrator extends MemberStatus {override val name = super.name}
  case object Member extends MemberStatus {override val name = super.name}
  case object Left extends MemberStatus {override val name = super.name}
  case object Kicked extends MemberStatus {override val name = super.name}

  def unsafe(str: String): MemberStatus = str match {
    case Creator.name       ⇒ Creator
    case Administrator.name ⇒ Administrator
    case Member.name        ⇒ Member
    case Left.name          ⇒ Left
    case Kicked.name        ⇒ Kicked
    case _                  ⇒ unexpected(str)
  }
}
