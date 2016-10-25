package com.github.nikdon.telepooz.model

import com.github.nikdon.telepooz.tags.FileId
import shapeless.tag.@@


/**
  * This object represents a file ready to be downloaded. The file can be downloaded via the link
  * [[https://api.telegram.org/file/bot<token>/<file_path>]]. It is guaranteed that the link will be
  * valid for at least 1 hour. When the link expires, a new one can be requested by calling `GetFile`.
  *
  * @param file_id   Unique identifier for this file
  * @param file_size File size, if known
  * @param file_path File path. Use [[https://api.telegram.org/file/bot<token>/<file_path>]] to get the file.
  */
case class File(file_id: String @@ FileId,
                file_size: Option[Int] = None,
                file_path: Option[String] = None)
