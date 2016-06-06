package com.github.nikdon.telepooz.model

import com.github.nikdon.telepooz.tags.QueryId
import shapeless.tag.@@


/**
  * This object represents an incoming callback query from a callback button in an inline keyboard.
  * If the button that originated the query was attached to a message sent by the bot,
  * the field message will be presented. If the button was attached to a message
  * sent via the bot (in inline mode), the field [[inline_message_id]] will be presented.
  *
  * @param id                 Unique identifier for this query
  * @param from               Sender
  * @param message            Message with the callback button that originated the query.
  *                           Note that message content and message date will not be available if the message is too old
  * @param inline_message_id  Identifier of the message sent via the bot in inline mode, that originated the query
  * @param data               Data associated with the callback button.
  *                           Be aware that a bad client can send arbitrary data in this field
  */
case class CallbackQuery(id: String @@ QueryId,
                         from: Option[User],
                         message: Option[Message],
                         inline_message_id: Option[String @@ QueryId],
                         data: String)
