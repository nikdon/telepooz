package com.github.nikdon.telepooz.model.methods

import com.github.nikdon.telepooz.model.{ParseMode, ReplyMarkup}
import com.github.nikdon.telepooz.tags.{ChatId, MessageId}
import com.github.nikdon.telepooz.{IsResourceId, model}
import shapeless.tag.@@


sealed trait Method[Result] extends Product with Serializable {
  def name: String = this.productPrefix
}


/**
  * A simple method for testing your bot's auth token. Requires no parameters.
  * Returns basic information about the bot in form of a User object.
  */
case object GetMe extends Method[model.User] {
}

/**
  * Use this method to send text messages. On success, the sent Message is returned.
  *
  * @param chatId                 Unique identifier for the target chat or username of the target channel (in the format @channelusername)
  * @param text                   Text of the message to be sent
  * @param parseMode              Send Markdown or HTML, if you want Telegram apps to show bold, italic,
  *                               fixed-width text or inline URLs in your bot's message.
  * @param disableWebPagePreview  Disables link previews for links in this message
  * @param disableNotification    Sends the message silently. iOS users will not receive a notification,
  *                               Android users will receive a notification with no sound.
  * @param replyToMessageId       If the message is a reply, ID of the original message
  * @param replyMarkup            Additional interface options. A JSON-serialized object for an inline keyboard,
  *                               custom reply keyboard, instructions to hide reply keyboard or to force a reply from the user.
  */
case class SendMessage[A : IsResourceId](chatId: A @@ ChatId,
                                         text: String,
                                         parseMode: Option[ParseMode] = None,
                                         disableWebPagePreview: Option[Boolean] = None,
                                         disableNotification: Option[Boolean] = None,
                                         replyToMessageId: Option[Int @@ MessageId] = None,
                                         replyMarkup: Option[ReplyMarkup] = None) extends Method[model.Message]

object SendMessage {
}


/**
  * Use this method to forward messages of any kind. On success, the sent Message is returned.
  *
  * @param chatId               Unique identifier for the target chat or username of the target channel (in the format @channelusername)
  * @param fromChatId           Unique identifier for the chat where the original message was sent (or channel username in the format @channelusername)
  * @param disableNotification  Sends the message silently. iOS users will not receive a notification, Android users
  *                             will receive a notification with no sound.
  * @param messageId            Unique message identifier
  */
case class ForwardMessage[A : IsResourceId](chatId: A @@ ChatId,
                                            fromChatId: A @@ ChatId,
                                            disableNotification: Option[Boolean] = None,
                                            messageId: Int @@ MessageId) extends Method[model.Message]

object ForwardMessage {
}
