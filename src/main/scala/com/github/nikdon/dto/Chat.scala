package com.github.nikdon.dto

import com.github.nikdon.model.ChatType
import com.github.nikdon.tags.syntax._
import com.github.nikdon.{ToModel, model}


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
case class Chat(id: Int,
                `type`: String,
                title: Option[String],
                userName: Option[String],
                firstName: Option[String],
                lastName: Option[String])

object Chat {
  implicit val toModel: ToModel[Chat, model.Chat] =
    ToModel(c ⇒ model.Chat(c.id.chatId, ChatType.unsafe(c.`type`), c.title, c.userName, c.firstName, c.lastName))
}
