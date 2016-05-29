package com.github.nikdon.dto.methods

import com.github.nikdon.dto.ReplyMarkup
import com.github.nikdon.{IsResourceId, dto}


sealed trait Method[Result] extends Product with Serializable {
  def name: String = this.productPrefix
}

/**
  * A simple method for testing your bot's auth token. Requires no parameters.
  * Returns basic information about the bot in form of a User object.
  */
case object GetMe extends Method[dto.User]

/**
  * Use this method to send text messages. On success, the sent Message is returned.
  *
  * @param chat_id                  Unique identifier for the target chat or username of the target channel (in the format @channelusername)
  * @param text                     Text of the message to be sent
  * @param parse_mode               Send Markdown or HTML, if you want Telegram apps to show bold, italic,
  *                                 fixed-width text or inline URLs in your bot's message.
  * @param disable_web_page_preview Disables link previews for links in this message
  * @param disable_notification     Sends the message silently. iOS users will not receive a notification,
  *                                 Android users will receive a notification with no sound.
  * @param reply_to_message_id      If the message is a reply, ID of the original message
  * @param reply_markup             Additional interface options. A JSON-serialized object for an inline keyboard,
  *                                 custom reply keyboard, instructions to hide reply keyboard or to force a reply from the user.
  */
case class SendMessage[A : IsResourceId](chat_id: A,
                                         text: String,
                                         parse_mode: Option[String] = None,
                                         disable_web_page_preview: Option[Boolean] = None,
                                         disable_notification: Option[Boolean] = None,
                                         reply_to_message_id: Option[Int] = None,
                                         reply_markup: Option[ReplyMarkup] = None) extends Method[dto.Message]
