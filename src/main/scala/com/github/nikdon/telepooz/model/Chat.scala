package com.github.nikdon.telepooz.model

import com.github.nikdon.telepooz._
import com.github.nikdon.telepooz.tags.ChatId
import shapeless.tag._


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
  id: Long @@ ChatId,
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
