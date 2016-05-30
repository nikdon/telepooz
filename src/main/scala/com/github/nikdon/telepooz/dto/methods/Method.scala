package com.github.nikdon.telepooz.dto.methods

import com.github.nikdon.telepooz.dto.ReplyMarkup
import com.github.nikdon.telepooz.raw.{CirceEncoders, RawRequest}
import com.github.nikdon.telepooz.{IsResourceId, ToRaw, dto}
import io.circe.Encoder
import io.circe.syntax._


sealed trait Method[Result] extends Product with Serializable {
  def name: String = this.productPrefix
}

/**
  * A simple method for testing your bot's auth token. Requires no parameters.
  * Returns basic information about the bot in form of a User object.
  */
case object GetMe extends Method[dto.User] {
  implicit val getMeMethodToRaw: ToRaw[GetMe.type, RawRequest.GetMe.type] =
    ToRaw(m => RawRequest.GetMe)
}

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

object SendMessage extends CirceEncoders {
  implicit def sendMessageMethodToRaw[A : IsResourceId : Encoder]: ToRaw[SendMessage[A], RawRequest.SendMessage] =
    ToRaw(m ⇒ RawRequest.SendMessage(m.asJson))
}

/**
  * Use this method to forward messages of any kind. On success, the sent Message is returned.
  *
  * @param chat_id              Unique identifier for the target chat or username of the target channel (in the format @channelusername)
  * @param from_chat_id         Unique identifier for the chat where the original message was sent (or channel username in the format @channelusername)
  * @param disable_notification Sends the message silently. iOS users will not receive a notification, Android users
  *                             will receive a notification with no sound.
  * @param message_id           Unique message identifier
  */
case class ForwardMessage[A : IsResourceId](chat_id: A,
                                            from_chat_id: A,
                                            disable_notification: Option[Boolean] = None,
                                            message_id: Int) extends Method[dto.Message]

object ForwardMessage extends CirceEncoders {
  implicit def forwardMessageToRaw[A : IsResourceId : Encoder]: ToRaw[ForwardMessage[A], RawRequest.ForwardMessage] =
    ToRaw(m ⇒ RawRequest.ForwardMessage(m.asJson))
}