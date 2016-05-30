package com.github.nikdon.telepooz.model

import com.github.nikdon.telepooz._
import com.github.nikdon.telepooz.tags.ChatId
import shapeless.tag._


/**
  * This object represents a chat.
  *
  * @param id        Unique identifier for this chat, not exceeding 1e13 by absolute value
  * @param `type`    Type of chat, can be either “private”, “group”, “supergroup” or “channel”
  * @param title     Title, for channels and group chats
  * @param userName  Username, for private chats and channels if available
  * @param firstName First name of the other party in a private chat
  * @param lastName  Last name of the other party in a private chat
  */
case class Chat(id: Int @@ ChatId,
                `type`: ChatType,
                title: Option[String],
                userName: Option[String],
                firstName: Option[String],
                lastName: Option[String])


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
