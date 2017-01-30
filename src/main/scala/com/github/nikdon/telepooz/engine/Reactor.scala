/*
 * Copyright 2016 Nikolay Donets
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.nikdon.telepooz.engine

import akka.Done
import akka.event.LoggingAdapter
import akka.stream.scaladsl.Sink
import cats.implicits._
import com.github.nikdon.telepooz.api._
import com.github.nikdon.telepooz.model.{methods, _}
import com.github.nikdon.telepooz.json.CirceEncoders
import com.typesafe.config.{Config, ConfigFactory}

import scala.concurrent.{ExecutionContext, Future}

abstract class Reactions {
  def react(m: Message)(implicit ec: ExecutionContext): Future[Done.type]
}

case class CommandBasedReactions(
    private val r: Map[String, Message ⇒ CommandBasedReactions.Arguments ⇒ CommandBasedReactions.IO] = Map.empty)
    extends Reactions {
  def on(command: String)(reaction: Message ⇒ CommandBasedReactions.Arguments ⇒ CommandBasedReactions.IO) =
    CommandBasedReactions(r + (command → reaction))

  def react(m: Message)(implicit ec: ExecutionContext): Future[Done.type] = {
    val maybeReaction: Option[CommandBasedReactions.IO] = for {
      text ← m.text
      Array(cmd, args @ _ *) = text.trim.split(" ")
      reaction ← r.get(cmd)
    } yield reaction(m)(args)

    maybeReaction.fold(Future.successful(Done))(_.map(_ ⇒ Done))
  }
}

object CommandBasedReactions {
  type Arguments = Seq[String]
  type IO        = Future[_]
}

abstract class Reactor(implicit are: ApiRequestExecutor, ec: ExecutionContext, logger: LoggingAdapter)
    extends CirceEncoders {

  val reactions: Reactions

  private[this] val config: Config   = ConfigFactory.load()
  private[this] val parallelism: Int = config.getInt("telegram.polling.parallelism")

  /** Sink that for each incoming `Update` reacts according to an actions in `reactions` */
  val react: Sink[Update, Future[Done]] = Sink.foreachParallel(parallelism)(update ⇒
    update.message match {
      case None ⇒ logger.debug(s"Update ${update.update_id} with empty message")
      case Some(m) ⇒
        logger.debug(s"Reacting on update ${update.update_id}")
        reactions.react(m)
  })

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
    * @return                       A future with `Response`
    */
  def reply(text: String,
            parseMode: Option[ParseMode] = None,
            disableWebPagePreview: Option[Boolean] = None,
            disableNotification: Option[Boolean] = None,
            replyToMessageId: Option[Long] = None,
            replyMarkup: Option[ReplyMarkup] = Some(ReplyKeyboardHide(hide_keyboard = true, None)))(
      implicit message: Message): Future[Response[Message]] = {
    val m = methods.SendMessage(
      message.chat.id,
      text = text,
      parse_mode = parseMode,
      disable_web_page_preview = disableWebPagePreview,
      disable_notification = disableNotification,
      reply_to_message_id = replyToMessageId,
      reply_markup = replyMarkup
    )
    m.foldMap(are)
  }

}
