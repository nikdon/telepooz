package com.github.nikdon.telepooz.model.methods

import com.github.nikdon.telepooz.model.{ReplyMarkup, ParseMode}
import com.github.nikdon.telepooz.dto
import com.github.nikdon.telepooz.tags.{ChatId, MessageId}
import com.github.nikdon.telepooz.{IsResourceId, ToDTO, model}
import shapeless.tag.@@


sealed trait Method[Result] extends Product with Serializable {
  def name: String = this.productPrefix
}


/**
  * A simple method for testing your bot's auth token. Requires no parameters.
  * Returns basic information about the bot in form of a User object.
  */
case object GetMe extends Method[model.User] {
  implicit val getMeToDTO: ToDTO[GetMe.type, dto.methods.GetMe.type] = ToDTO(m ⇒ dto.methods.GetMe)
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
  implicit def sendMessageToDTO[A : IsResourceId]: ToDTO[SendMessage[A], dto.methods.SendMessage[A]] =
    ToDTO(m ⇒ dto.methods.SendMessage[A](m.chatId,
                                         m.text,
                                         m.parseMode.map(_.name),
                                         m.disableWebPagePreview,
                                         m.disableNotification,
                                         m.replyToMessageId,
                                         m.replyMarkup.map(ReplyMarkup.convertToDTO)))
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
  implicit def forwardMessageToDTO[A: IsResourceId]: ToDTO[ForwardMessage[A], dto.methods.ForwardMessage[A]] =
    ToDTO(m ⇒ dto.methods.ForwardMessage[A](m.chatId,
                                            m.fromChatId,
                                            m.disableNotification,
                                            m.messageId))
}
