package com.github.nikdon.telepooz.model.methods

import com.github.nikdon.telepooz.IsResourceId
import com.github.nikdon.telepooz.model.{ParseMode, ReplyMarkup}
import com.github.nikdon.telepooz.tags.{ChatId, MessageId}
import shapeless.tag.@@


/**
  * A simple method for testing your bot's auth token. Requires no parameters.
  * Returns basic information about the bot in form of a User object.
  */
case object GetMe

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
                                         replyMarkup: Option[ReplyMarkup] = None)

/**
  * Use this method to forward messages of any kind. On success, the sent Message is returned.
  *
  * @param chatId               Unique identifier for the target chat or username of the target channel (in the format @channelusername)
  * @param fromChatId           Unique identifier for the chat where the original message was sent (or channel username in the format @channelusername)
  * @param messageId            Unique message identifier
  * @param disableNotification  Sends the message silently. iOS users will not receive a notification, Android users
  *                             will receive a notification with no sound.
  */
case class ForwardMessage[A : IsResourceId, B : IsResourceId](chatId: A @@ ChatId,
                                                              fromChatId: B @@ ChatId,
                                                              messageId: Int @@ MessageId,
                                                              disableNotification: Option[Boolean] = None)


/**
  * Use this method to receive incoming updates using long polling (wiki). An Array of Update objects is returned.
  *
  * @param offset   Identifier of the first update to be returned. Must be greater by one than the highest among
  *                 the identifiers of previously received updates. By default, updates starting with the earliest
  *                 unconfirmed update are returned. An update is considered confirmed as soon as getUpdates is
  *                 called with an offset higher than its update_id. The negative offset can be specified to retrieve
  *                 updates starting from -offset update from the end of the updates queue. All previous updates
  *                 will forgotten.
  * @param limit    Limits the number of updates to be retrieved. Values between 1—100 are accepted. Defaults to 100.
  * @param timeout  Timeout in seconds for long polling. Defaults to 0, i.e. usual short polling
  */
case class GetUpdates(offset: Option[Int] = None,
                      limit: Option[Int] = Some(100),
                      timeout: Option[Int] = Some(0))
