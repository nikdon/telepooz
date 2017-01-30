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
  * This object represents a file ready to be downloaded. The file can be downloaded via the link
  * [[https://api.telegram.org/file/bot<token>/<file_path>]]. It is guaranteed that the link will be
  * valid for at least 1 hour. When the link expires, a new one can be requested by calling `GetFile`.
  *
  * @param file_id   Unique identifier for this file
  * @param file_size File size, if known
  * @param file_path File path. Use [[https://api.telegram.org/file/bot<token>/<file_path>]] to get the file.
  */
case class File(file_id: String,
                file_size: Option[Int] = None,
                file_path: Option[String] = None)
