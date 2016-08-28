package com.github.nikdon.telepooz.model.methods

import com.github.nikdon.telepooz.IsResourceId
import com.github.nikdon.telepooz.model.{ParseMode, ReplyMarkup}
import com.github.nikdon.telepooz.tags.{ChatId, MessageId}
import shapeless.tag.@@


/**
  * Use this method to edit text messages sent by the bot or via the bot (for inline bots). On success, if edited
  * message is sent by the bot, the edited Message is returned, otherwise True is returned.
  *
  * @param chat_id                  Required if inline_message_id is not specified. Unique identifier for the target
  *                                 chat or username of the target channel (in the format @channelusername)
  * @param message_id               Required if inline_message_id is not specified. Unique identifier of the sent message
  * @param inline_message_id        Required if chat_id and message_id are not specified. Identifier of the inline message
  * @param text                     New text of the message
  * @param parse_mode               Send Markdown or HTML, if you want Telegram apps to show bold, italic, fixed-width
  *                                 text or inline URLs in your bot's message.
  * @param disable_web_page_preview Disables link previews for links in this message
  * @param reply_markup             A JSON-serialized object for an inline keyboard.
  */
case class EditMessageText[A: IsResourceId](
  chat_id: A @@ ChatId,
  message_id: Long @@ MessageId,
  inline_message_id: String @@ MessageId,
  text: String,
  parse_mode: Option[ParseMode] = None,
  disable_web_page_preview: Option[Boolean] = None,
  reply_markup: Option[ReplyMarkup] = None
)
