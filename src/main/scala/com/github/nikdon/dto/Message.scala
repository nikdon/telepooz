package com.github.nikdon.dto

import java.util.Date

import com.github.nikdon.ToModel.syntax._
import com.github.nikdon.tags.syntax._
import com.github.nikdon.{ToModel, model}


/**
  * This object represents a message.
  *
  * @param messageId              Unique message identifier
  * @param from                   Sender, can be empty for messages sent to channels
  * @param date                   Date the message was sent in Unix time
  * @param chat                   Conversation the message belongs to
  * @param forwardFrom            For forwarded messages, sender of the original message
  * @param forwardFromChat        For messages forwarded from a channel, information about the original channel
  * @param forwardDate            For forwarded messages, date the original message was sent in Unix time
  * @param replyToMessage         For replies, the original message. Note that the Message object in this
  *                               field will not contain further reply_to_message fields even if it itself is a reply.
  * @param text                   For text messages, the actual UTF-8 text of the message, 0-4096 characters.
  * @param entities               For text messages, special entities like usernames, URLs, bot commands, etc. that appear in the text
  * @param audio                  Message is an audio file, information about the file
  * @param document               Message is a general file, information about the file
  * @param photo                  Message is a photo, available sizes of the photo
  * @param sticker                Message is a sticker, information about the sticker
  * @param video                  Message is a video, information about the video
  * @param voice                  Message is a voice message, information about the file
  * @param caption                Caption for the document, photo or video, 0-200 characters
  * @param contact                Message is a shared contact, information about the contact
  * @param location               Message is a shared location, information about the location
  * @param venue                  Message is a venue, information about the venue
  * @param newChatMember          A new member was added to the group, information about them (this member may be the bot itself)
  * @param leftChatMember         A member was removed from the group, information about them (this member may be the bot itself)
  * @param newChatTitle           A chat title was changed to this value
  * @param newChatPhoto           A chat photo was change to this value
  * @param deleteChatPhoto        Service message: the chat photo was deleted
  * @param groupChatCreated       Service message: the group has been created
  * @param superGroupChatCreated  Service message: the supergroup has been created
  * @param channelChatCreated     Service message: the channel has been created
  * @param migrateToChatId        The group has been migrated to a supergroup with the specified identifier, not exceeding 1e13 by absolute value
  * @param migrateFromChatId      The supergroup has been migrated from a group with the specified identifier, not exceeding 1e13 by absolute value
  * @param pinnedMessage          Specified message was pinned. Note that the Message object in this field
  *                               will not contain further reply_to_message fields even if it is itself a reply.
  */
case class Message(messageId: Int,
                   from: Option[User],
                   date: Integer,
                   chat: Chat,
                   forwardFrom: Option[User],
                   forwardFromChat: Option[Chat],
                   forwardDate: Option[Int],
                   replyToMessage: Option[Message],
                   text: Option[String],
                   entities: Option[Vector[MessageEntity]],
                   audio: Option[Audio],
                   document: Option[Document],
                   photo: Option[Vector[PhotoSize]],
                   sticker: Option[Sticker],
                   video: Option[Video],
                   voice: Option[Voice],
                   caption: Option[String],
                   contact: Option[Contact],
                   location: Option[Location],
                   venue: Option[Venue],
                   newChatMember: Option[User],
                   leftChatMember: Option[User],
                   newChatTitle: Option[String],
                   newChatPhoto: Option[Vector[PhotoSize]],
                   deleteChatPhoto: Option[Boolean],
                   groupChatCreated: Option[Boolean],
                   superGroupChatCreated: Option[Boolean],
                   channelChatCreated: Option[Boolean],
                   migrateToChatId: Option[Int],
                   migrateFromChatId: Option[Int],
                   pinnedMessage: Option[Message])

object Message {
  implicit val toModel: ToModel[Message, model.Message] =
    ToModel(m ⇒ model.Message(m.messageId.messageId,
                              m.from.map(_.toModel),
                              new Date(m.date.toLong),
                              m.chat.toModel,
                              m.forwardFrom.map(_.toModel),
                              m.forwardFromChat.map(_.toModel),
                              m.forwardDate.map(i ⇒ new Date(i.toLong)),
                              m.replyToMessage.map(_.toModel),
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
                              m.newChatMember.map(_.toModel),
                              m.leftChatMember.map(_.toModel),
                              m.newChatTitle,
                              m.newChatPhoto.map(_.map(_.toModel)),
                              m.deleteChatPhoto,
                              m.groupChatCreated,
                              m.superGroupChatCreated,
                              m.channelChatCreated,
                              m.migrateToChatId.map(_.chatId),
                              m.migrateFromChatId.map(_.chatId),
                              m.pinnedMessage.map(_.toModel)))
}
