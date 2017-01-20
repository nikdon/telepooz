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

import com.github.nikdon.telepooz.model.{File, Response}

/**
  * Use this method to get basic info about a file and prepare it for downloading. For the moment, bots can download
  * files of up to 20MB in size. On success, a File object is returned. The file can then be downloaded via the link
  * https://api.telegram.org/file/bot<token>/<file_path>, where <file_path> is taken from the response. It is guaranteed
  * that the link will be valid for at least 1 hour. When the link expires, a new one can be requested by calling
  * getFile again.
  *
  * @param file_id  File identifier to get info about
  */
case class GetFile(file_id: String) extends Method[Response[File]]
