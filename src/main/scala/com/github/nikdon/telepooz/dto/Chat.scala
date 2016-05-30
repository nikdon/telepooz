package com.github.nikdon.telepooz.dto

import com.github.nikdon.telepooz.model.ChatType
import com.github.nikdon.telepooz.tags.syntax._
import com.github.nikdon.telepooz.{ToModel, model}


/**
  * This object represents a chat.
  *
  * @param id         Unique identifier for this chat, not exceeding 1e13 by absolute value
  * @param `type`     Type of chat, can be either “private”, “group”, “supergroup” or “channel”
  * @param title      Title, for channels and group chats
  * @param user_name  Username, for private chats and channels if available
  * @param first_name First name of the other party in a private chat
  * @param last_name  Last name of the other party in a private chat
  */
case class Chat(id: Int,
                `type`: String,
                title: Option[String] = None,
                user_name: Option[String] = None,
                first_name: Option[String] = None,
                last_name: Option[String] = None)

object Chat {
  implicit val toModel: ToModel[Chat, model.Chat] =
    ToModel(c ⇒ model.Chat(c.id.chatId, ChatType.unsafe(c.`type`), c.title, c.user_name, c.first_name, c.last_name))
}
