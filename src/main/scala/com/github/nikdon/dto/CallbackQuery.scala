package com.github.nikdon.dto

import com.github.nikdon.{ToModel, model}
import com.github.nikdon.ToModel.syntax._
import com.github.nikdon.tags.syntax._


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
case class CallbackQuery(id: String,
                         from: Option[User] = None,
                         message: Option[Message] = None,
                         inline_message_id: Option[String] = None,
                         data: String)

object CallbackQuery {
  implicit val toModel: ToModel[CallbackQuery, model.CallbackQuery] =
    ToModel(c ⇒ model.CallbackQuery(c.id.queryId,
                                    c.from.map(_.toModel),
                                    c.message.map(_.toModel),
                                    c.inline_message_id.map(_.queryId),
                                    c.data))
}
