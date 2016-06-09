package com.github.nikdon.telepooz.model.methods


import java.time.Duration

import com.github.nikdon.telepooz.IsResourceId
import com.github.nikdon.telepooz.model.ReplyMarkup
import com.github.nikdon.telepooz.tags.{ChatId, FileId, MessageId}
import shapeless.tag.@@


/**
  * Use this method to send audio files, if you want Telegram clients to display the file as a playable voice message.
  * For this to work, your audio must be in an .ogg file encoded with OPUS (other formats may be sent as Audio or
  * Document). On success, the sent Message is returned. Bots can currently send voice messages of up to 50 MB in size,
  * this limit may be changed in the future.
  *
  * @param chat_id              Unique identifier for the target chat or username of the target channel
  *                             (in the format @channelusername)
  * @param voice                Audio file to send. You can either pass a file_id as String to resend an audio that is
  *                             already on the Telegram servers, or upload a new audio file using multipart/form-data.
  * @param duration             Duration of the audio in seconds
  * @param disable_notification Sends the message silently. iOS users will not receive a notification, Android users
  *                             will receive a notification with no sound.
  * @param reply_to_message_id  If the message is a reply, ID of the original message
  * @param reply_markup         Additional interface options. A JSON-serialized object for an inline keyboard, custom
  *                             reply keyboard, instructions to hide reply keyboard or to force a reply from the user.
  */
case class SendVoice[A: IsResourceId](chat_id: A @@ ChatId,
                                      voice: String @@ FileId, // TODO Add file upload
                                      duration: Option[Duration] = None,
                                      disable_notification: Option[Boolean] = None,
                                      reply_to_message_id: Option[Long @@ MessageId] = None,
                                      reply_markup: Option[ReplyMarkup] = None)
