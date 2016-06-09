package com.github.nikdon.telepooz.model.methods

import com.github.nikdon.telepooz.IsResourceId
import com.github.nikdon.telepooz.tags.{ChatId, MessageId}
import shapeless.tag.@@


/**
  * Use this method to forward messages of any kind. On success, the sent Message is returned.
  *
  * @param chat_id              Unique identifier for the target chat or username of the target channel
  *                             (in the format @channelusername)
  * @param from_chat_id         Unique identifier for the chat where the original message was sent (or channel username
  *                             in the format @channelusername)
  * @param message_id           Unique message identifier
  * @param disable_notification Sends the message silently. iOS users will not receive a notification, Android users
  *                             will receive a notification with no sound.
  */
case class ForwardMessage[A: IsResourceId, B: IsResourceId](chat_id: A @@ ChatId,
                                                            from_chat_id: B @@ ChatId,
                                                            message_id: Long @@ MessageId,
                                                            disable_notification: Option[Boolean] = None)
