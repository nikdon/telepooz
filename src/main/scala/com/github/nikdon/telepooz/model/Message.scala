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

package com.github.nikdon.telepooz.model

import java.util.Date


/**
  * This object represents a message.
  *
  * @param message_id               Unique message identifier
  * @param date                     Date the message was sent in Unix time
  * @param chat                     Conversation the message belongs to
  * @param from                     Sender, can be empty for messages sent to channels
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
  * @param new_chat_photo           A chat photo was changed to this value
  * @param delete_chat_photo        Service message: the chat photo was deleted
  * @param group_chat_created       Service message: the group has been created
  * @param supergroup_chat_created  Service message: the supergroup has been created
  * @param channel_chat_created     Service message: the channel has been created
  * @param migrate_to_chat_id       The group has been migrated to a supergroup with the specified identifier, not exceeding 1e13 by absolute value
  * @param migrate_from_chat_id     The supergroup has been migrated from a group with the specified identifier, not exceeding 1e13 by absolute value
  * @param pinned_message           Specified message was pinned. Note that the Message object in this field
  *                                 will not contain further reply_to_message fields even if it is itself a reply.
  */
case class Message(message_id: Long,
                   date: Date,
                   chat: Chat,
                   from: Option[User] = None,
                   forward_from: Option[User] = None,
                   forward_from_chat: Option[Chat] = None,
                   forward_date: Option[Date] = None,
                   reply_to_message: Option[Message] = None,
                   text: Option[String] = None,
                   entities: Option[Vector[MessageEntity]] = None,
                   audio: Option[Audio] = None,
                   document: Option[Document] = None,
                   game: Option[Game] = None,
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
                   migrate_to_chat_id: Option[Long] = None,
                   migrate_from_chat_id: Option[Long] = None,
                   pinned_message: Option[Message] = None)
