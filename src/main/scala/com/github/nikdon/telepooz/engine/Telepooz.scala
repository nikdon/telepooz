package com.github.nikdon.telepooz.engine

import akka.Done
import akka.stream.scaladsl._
import com.github.nikdon.telepooz.api
import com.github.nikdon.telepooz.model._
import com.github.nikdon.telepooz.tags.syntax._
import com.github.nikdon.telepooz.tags.MessageId
import com.github.nikdon.telepooz.ToRawRequest.syntax._
import shapeless.tag.@@

import scala.concurrent.Future


trait Telepooz extends Poller with Reactor { apiRequestExecutor: ApiRequestExecutor ⇒
  def run(): Future[Done] = Source.fromGraph(pollGraph).runWith(react)

  /**
    * Helper method for convenient sending message in response to the incoming one
    *
    * @param text                   Text of the message to be sent
    * @param parseMode              Send Markdown or HTML, if you want Telegram apps to show bold, italic,
    *                               fixed-width text or inline URLs in your bot's message.
    * @param disableWebPagePreview  Disables link previews for links in this message
    * @param disableNotification    Sends the message silently. iOS users will not receive a notification,
    *                               Android users will receive a notification with no sound.
    * @param replyToMessageId       If the message is a reply, ID of the original message
    * @param replyMarkup            Additional interface options. A JSON-serialized object for an inline keyboard,
    *                               custom reply keyboard, instructions to hide reply keyboard or to force a reply
    *                               from the user.
    * @param message                The message the reply is for.
    * @return                       A future with [[Response]]
    */
  def reply(text: String,
            parseMode: Option[ParseMode] = None,
            disableWebPagePreview: Option[Boolean] = None,
            disableNotification: Option[Boolean] = None,
            replyToMessageId: Option[Long @@ MessageId] = None,
            replyMarkup: Option[ReplyMarkup] = Some(ReplyKeyboardHide(hide_keyboard = true, None)))
           (implicit message: Message): Future[Response[Message]] = {
    val m = methods.SendMessage(message.chat.id.toLong.chatId, // As chat id might be only a Long
                                text = text,
                                parse_mode = parseMode,
                                disable_web_page_preview = disableWebPagePreview,
                                disable_notification = disableNotification,
                                reply_to_message_id = replyToMessageId,
                                reply_markup = replyMarkup)
    api.execute(m.toRawRequest).foldMap(apiRequestExecutor)
  }

}
