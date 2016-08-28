package com.github.nikdon.telepooz.model.methods

import com.github.nikdon.telepooz.IsResourceId
import com.github.nikdon.telepooz.model.ReplyMarkup
import com.github.nikdon.telepooz.tags.{ChatId, MessageId}
import shapeless.tag.@@


/**
  * Use this method to edit only the reply markup of messages sent by the bot or via the bot (for inline bots). On
  * success, if edited message is sent by the bot, the edited Message is returned, otherwise True is returned.
  *
  * @param chat_id            Required if inline_message_id is not specified. Unique identifier for the target chat or
  *                           username of the target channel (in the format @channelusername)
  * @param message_id         Required if inline_message_id is not specified. Unique identifier of the sent message
  * @param inline_message_id  Required if chat_id and message_id are not specified. Identifier of the inline message
  * @param reply_markup       A JSON-serialized object for an inline keyboard.
  */
case class EditMessageReplyMarkup[A: IsResourceId](
  chat_id: A @@ ChatId,
  message_id: Long @@ MessageId,
  inline_message_id: String @@ MessageId,
  reply_markup: Option[ReplyMarkup] = None
)
