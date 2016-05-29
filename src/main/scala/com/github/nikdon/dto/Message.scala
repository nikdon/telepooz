package com.github.nikdon.dto

import java.util.Date

import com.github.nikdon.ToModel.syntax._
import com.github.nikdon.tags.syntax._
import com.github.nikdon.{ToModel, model}


/**
  * This object represents a message.
  *
  * @param message_id               Unique message identifier
  * @param from                     Sender, can be empty for messages sent to channels
  * @param date                     Date the message was sent in Unix time
  * @param chat                     Conversation the message belongs to
  * @param forward_from             For forwarded messages, sender of the original message
  * @param forward_from_chat        For messages forwarded from a channel, information about the original channel
  * @param forward_date             For forwarded messages, date the original message was sent in Unix time
  * @param reply_to_message         For replies, the original message. Note that the Message object in this
  *                                 field will not contain further reply_to_message fields even if it itself is a reply.
  * @param text                     For text messages, the actual UTF-8 text of the message, 0-4096 characters.
  * @param entities                 For text messages, special entities like usernames, URLs, bot commands, etc. that appear in the text
  * @param audio                    Message is an audio file, information about the file
  * @param document                 Message is a general file, information about the file
  * @param photo                    Message is a photo, available sizes of the photo
  * @param sticker                  Message is a sticker, information about the sticker
  * @param video                    Message is a video, information about the video
  * @param voice                    Message is a voice message, information about the file
  * @param caption                  Caption for the document, photo or video, 0-200 characters
  * @param contact                  Message is a shared contact, information about the contact
  * @param location                 Message is a shared location, information about the location
  * @param venue                    Message is a venue, information about the venue
  * @param new_chat_member          A new member was added to the group, information about them (this member may be the bot itself)
  * @param left_chat_member         A member was removed from the group, information about them (this member may be the bot itself)
  * @param new_chat_title           A chat title was changed to this value
  * @param new_chat_photo           A chat photo was change to this value
  * @param delete_chat_photo        Service message: the chat photo was deleted
  * @param group_chat_created       Service message: the group has been created
  * @param supergroup_chat_created  Service message: the supergroup has been created
  * @param channel_chat_created     Service message: the channel has been created
  * @param migrate_to_chat_id       The group has been migrated to a supergroup with the specified identifier, not exceeding 1e13 by absolute value
  * @param migrate_from_chat_id     The supergroup has been migrated from a group with the specified identifier, not exceeding 1e13 by absolute value
  * @param pinned_message           Specified message was pinned. Note that the Message object in this field
  *                                 will not contain further reply_to_message fields even if it is itself a reply.
  */
case class Message(message_id: Int,
                   from: Option[User] = None,
                   date: Integer,
                   chat: Chat,
                   forward_from: Option[User] = None,
                   forward_from_chat: Option[Chat] = None,
                   forward_date: Option[Int] = None,
                   reply_to_message: Option[Message] = None,
                   text: Option[String] = None,
                   entities: Option[Vector[MessageEntity]] = None,
                   audio: Option[Audio] = None,
                   document: Option[Document] = None,
                   photo: Option[Vector[PhotoSize]] = None,
                   sticker: Option[Sticker] = None,
                   video: Option[Video] = None,
                   voice: Option[Voice] = None,
                   caption: Option[String] = None,
                   contact: Option[Contact] = None,
                   location: Option[Location] = None,
                   venue: Option[Venue] = None,
                   new_chat_member: Option[User] = None,
                   left_chat_member: Option[User] = None,
                   new_chat_title: Option[String] = None,
                   new_chat_photo: Option[Vector[PhotoSize]] = None,
                   delete_chat_photo: Option[Boolean] = None,
                   group_chat_created: Option[Boolean] = None,
                   supergroup_chat_created: Option[Boolean] = None,
                   channel_chat_created: Option[Boolean] = None,
                   migrate_to_chat_id: Option[Int] = None,
                   migrate_from_chat_id: Option[Int] = None,
                   pinned_message: Option[Message] = None)

object Message {
  implicit val toModel: ToModel[Message, model.Message] =
    ToModel(m ⇒ model.Message(m.message_id.messageId,
                              m.from.map(_.toModel),
                              new Date(m.date.toLong),
                              m.chat.toModel,
                              m.forward_from.map(_.toModel),
                              m.forward_from_chat.map(_.toModel),
                              m.forward_date.map(i ⇒ new Date(i.toLong)),
                              m.reply_to_message.map(_.toModel),
                              m.text,
                              m.entities.map(_.map(_.toModel)),
                              m.audio.map(_.toModel),
                              m.document.map(_.toModel),
                              m.photo.map(_.map(_.toModel)),
                              m.sticker.map(_.toModel),
                              m.video.map(_.toModel),
                              m.voice.map(_.toModel),
                              m.caption,
                              m.contact.map(_.toModel),
                              m.location.map(_.toModel),
                              m.venue.map(_.toModel),
                              m.new_chat_member.map(_.toModel),
                              m.left_chat_member.map(_.toModel),
                              m.new_chat_title,
                              m.new_chat_photo.map(_.map(_.toModel)),
                              m.delete_chat_photo,
                              m.group_chat_created,
                              m.supergroup_chat_created,
                              m.channel_chat_created,
                              m.migrate_to_chat_id.map(_.chatId),
                              m.migrate_from_chat_id.map(_.chatId),
                              m.pinned_message.map(_.toModel)))
}
