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


import java.time.Duration

import com.github.nikdon.telepooz.model
import com.github.nikdon.telepooz.tags.FileId
import shapeless.tag.@@


/**
  * This object represents a video file.
  *
  * @param file_id    Unique identifier for this file
  * @param width      Video width as defined by sender
  * @param height     Video height as defined by sender
  * @param duration   Duration of the video in seconds as defined by sender
  * @param thumb      Video thumbnail
  * @param mime_type  Mime type of a file as defined by sender
  * @param file_size  File size
  */
case class Video(file_id: String @@ FileId,
                 width: Int,
                 height: Int,
                 duration: Duration,
                 thumb: Option[model.PhotoSize],
                 mime_type: Option[String],
                 file_size: Option[Int])
