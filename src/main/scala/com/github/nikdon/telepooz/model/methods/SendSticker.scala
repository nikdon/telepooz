package com.github.nikdon.telepooz.model.methods

import com.github.nikdon.telepooz.IsResourceId
import com.github.nikdon.telepooz.model.ReplyMarkup
import com.github.nikdon.telepooz.tags.{ChatId, FileId, MessageId}
import shapeless.tag.@@


/**
  * Use this method to send .webp stickers. On success, the sent Message is returned.
  *
  * @param chat_id              Unique identifier for the target chat or username of the target channel
  *                             (in the format @channelusername)
  * @param sticker              Sticker to send. You can either pass a file_id as String to resend a sticker that is
  *                             already on the Telegram servers, or upload a new sticker using multipart/form-data.
  * @param disable_notification Sends the message silently. iOS users will not receive a notification, Android users
  *                             will receive a notification with no sound.
  * @param reply_to_message_id  If the message is a reply, ID of the original message
  * @param reply_markup         Additional interface options. A JSON-serialized object for an inline keyboard, custom
  *                             reply keyboard, instructions to hide reply keyboard or to force a reply from the user.
  */
case class SendSticker[A: IsResourceId](chat_id: A @@ ChatId,
                                        sticker: String @@ FileId, // TODO Add file upload
                                        disable_notification: Option[Boolean] = None,
                                        reply_to_message_id: Option[Long @@ MessageId] = None,
                                        reply_markup: Option[ReplyMarkup] = None)
