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

/**
  * You can provide an animation for your game so that it looks stylish in chats (check out Lumberjack for an example).
  * This object represents an animation file to be displayed in the message containing a game.
  *
  * @param file_id   Unique file identifier
  * @param thumb     Animation thumbnail as defined by sender
  * @param file_name Original animation filename as defined by sender
  * @param mime_type MIME type of the file as defined by sender
  * @param file_size File size
  */
case class Animation(
    file_id: String,
    thumb: Option[PhotoSize] = None,
    file_name: Option[String] = None,
    mime_type: Option[String] = None,
    file_size: Option[Int] = None
)
