package com.github.nikdon.model

import com.github.nikdon._
import com.github.nikdon.tags.ChatId
import com.github.nikdon.{ToDTO, dto, model}
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

object Chat {
  implicit val chatToDTO: ToDTO[model.Chat, dto.Chat] =
    ToDTO(c ⇒ dto.Chat(c.id, c.`type`.name, c.title, c.userName, c.firstName, c.lastName))
}


sealed trait ChatType extends Product with Serializable {
  def name: String = this.productPrefix.toLowerCase
}

object ChatType {

  case object Private extends ChatType
  case object Group extends ChatType
  case object SuperGroup extends ChatType
  case object Channel extends ChatType

  def unsafe(str: String): ChatType = str match {
    case "private"    ⇒ Private
    case "group"      ⇒ Group
    case "supergroup" ⇒ SuperGroup
    case "channel"    ⇒ Channel
    case _            ⇒ unexpected(str)
  }
}
