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
  * This object contains information about one member of a chat.
  *
  * @param user  Information about the user
  * @param status  The member's status in the chat. Can be “creator”, “administrator”, “member”, “restricted”, “left” or “kicked”
  * @param until_date  Optional. Restictred and kicked only. Date when restrictions will be lifted for this user, unix time
  * @param can_be_edited  Optional. Administrators only. True, if the bot is allowed to edit administrator privileges of that user
  * @param can_change_info  Optional. Administrators only. True, if the administrator can change the chat title, photo and other settings
  * @param can_post_messages  Optional. Administrators only. True, if the administrator can post in the channel, channels only
  * @param can_edit_messages  Optional. Administrators only. True, if the administrator can edit messages of other users, channels only
  * @param can_delete_messages  Optional. Administrators only. True, if the administrator can delete messages of other users
  * @param can_invite_users  Optional. Administrators only. True, if the administrator can invite new users to the chat
  * @param can_restrict_members  Optional. Administrators only. True, if the administrator can restrict, ban or unban chat members
  * @param can_pin_messages  Optional. Administrators only. True, if the administrator can pin messages, supergroups only
  * @param can_promote_members  Optional. Administrators only. True, if the administrator can add new administrators with a subset of his own privileges or demote administrators that he has promoted, directly or indirectly (promoted by administrators that were appointed by the user)
  * @param can_send_messages  Optional. Restricted only. True, if the user can send text messages, contacts, locations and venues
  * @param can_send_media_messages  Optional. Restricted only. True, if the user can send audios, documents, photos, videos, video notes and voice notes, implies can_send_messages
  * @param can_send_other_messages  Optional. Restricted only. True, if the user can send animations, games, stickers and use inline bots, implies can_send_media_messages
  * @param can_add_web_page_previews  Optional. Restricted only. True, if user may add web page previews to his messages, implies can_send_media_messages
  */
final case class ChatMember(
    user: User,
    status: MemberStatus,
    until_date: Option[Long] = None,
    can_be_edited: Option[Boolean] = None,
    can_change_info: Option[Boolean] = None,
    can_post_messages: Option[Boolean] = None,
    can_edit_messages: Option[Boolean] = None,
    can_delete_messages: Option[Boolean] = None,
    can_invite_users: Option[Boolean] = None,
    can_restrict_members: Option[Boolean] = None,
    can_pin_messages: Option[Boolean] = None,
    can_promote_members: Option[Boolean] = None,
    can_send_messages: Option[Boolean] = None,
    can_send_media_messages: Option[Boolean] = None,
    can_send_other_messages: Option[Boolean] = None,
    can_add_web_page_previews: Option[Boolean] = None
)

sealed trait MemberStatus extends Product with Serializable { def name: String = productPrefix }
object MemberStatus {
  case object Creator       extends MemberStatus { override val name = super.name }
  case object Administrator extends MemberStatus { override val name = super.name }
  case object Member        extends MemberStatus { override val name = super.name }
  case object Left          extends MemberStatus { override val name = super.name }
  case object Kicked        extends MemberStatus { override val name = super.name }

  def unsafe(str: String): MemberStatus = str match {
    case Creator.name       ⇒ Creator
    case Administrator.name ⇒ Administrator
    case Member.name        ⇒ Member
    case Left.name          ⇒ Left
    case Kicked.name        ⇒ Kicked
    case _                  ⇒ unexpected(str)
  }
}
