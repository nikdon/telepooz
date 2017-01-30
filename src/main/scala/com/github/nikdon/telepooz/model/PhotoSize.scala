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
  * This object represents one size of a photo or a file / sticker thumbnail.
  *
  * @param file_id    Unique identifier for this file
  * @param width      Photo width
  * @param height     Photo height
  * @param file_size  File size
  */
case class PhotoSize(file_id: String, width: Int, height: Int, file_size: Option[Int])
