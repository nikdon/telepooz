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
  * This object represents a general file (as opposed to Photo, VoiceMessage and AudioFile).
  *
  * @param file_id    Unique file identifier
  * @param thumb      Document thumbnail as defined by sender
  * @param file_name  Original filename as defined by sender
  * @param mime_type  MIME type of the file as defined by sender
  * @param file_size  File size
  */
case class Document(file_id: String,
                    thumb: Option[PhotoSize],
                    file_name: Option[String],
                    mime_type: Option[String],
                    file_size: Option[Int])
