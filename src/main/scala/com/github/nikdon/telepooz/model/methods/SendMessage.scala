package com.github.nikdon.telepooz.model.methods

import com.github.nikdon.telepooz.model.{ParseMode, ReplyMarkup}
import com.github.nikdon.telepooz.tags.{ChatId, MessageId}
import shapeless.tag.@@


/**
  * Use this method to send text messages. On success, the sent Message is returned.
  *
  * @param chat_id                    Unique identifier for the target chat or username of the target channel
  *                                   (in the format @channelusername)
  * @param text                       Text of the message to be sent
  * @param parse_mode                 Send Markdown or HTML, if you want Telegram apps to show bold, italic,
  *                                   fixed-width text or inline URLs in your bot's message.
  * @param disable_web_page_preview   Disables link previews for links in this message
  * @param disable_notification       Sends the message silently. iOS users will not receive a notification,
  *                                   Android users will receive a notification with no sound.
  * @param reply_to_message_id        If the message is a reply, ID of the original message
  * @param reply_markup               Additional interface options. A JSON-serialized object for an inline keyboard,
  *                                   custom reply keyboard, instructions to hide reply keyboard or to force a reply
  *                                   from the user.
  */
case class SendMessage(chat_id: Long @@ ChatId,
                       text: String,
                       parse_mode: Option[ParseMode] = None,
                       disable_web_page_preview: Option[Boolean] = None,
                       disable_notification: Option[Boolean] = None,
                       reply_to_message_id: Option[Long @@ MessageId] = None,
                       reply_markup: Option[ReplyMarkup] = None)
