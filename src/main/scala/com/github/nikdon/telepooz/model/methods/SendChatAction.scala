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

package com.github.nikdon.telepooz.model.methods

import com.github.nikdon.telepooz.IsResourceId
import com.github.nikdon.telepooz.tags.ChatId
import shapeless.tag._


/**
  * Use this method when you need to tell the user that something is happening on the bot's side. The status is set for
  * 5 seconds or less (when a message arrives from your bot, Telegram clients clear its typing status).
  *
  * @param chat_id  Unique identifier for the target chat or username of the target channel
  *                 (in the format @channelusername)
  * @param action   Type of action to broadcast. Choose one, depending on what the user is about to receive:
  *                 typing for text messages, upload_photo for photos, record_video or upload_video for videos,
  *                 record_audio or upload_audio for audio files, upload_document for general files, find_location
  *                 for location data.
  */
case class SendChatAction[A: IsResourceId](chat_id: A @@ ChatId,
                                           action: ChatAction)


sealed trait ChatAction extends Product with Serializable {def name: String = this.productPrefix}

object ChatAction {
  case object Typing extends ChatAction {override val name = super.name}
  case object UploadPhoto extends ChatAction {override val name = super.name}
  case object RecordVideo extends ChatAction {override val name = super.name}
  case object UploadVideo extends ChatAction {override val name = super.name}
  case object RecordAudio extends ChatAction {override val name = super.name}
  case object UploadAudio extends ChatAction {override val name = super.name}
  case object UploadDocument extends ChatAction {override val name = super.name}
  case object FindLocation extends ChatAction {override val name = super.name}
}
