package com.github.nikdon.telepooz.model

import com.github.nikdon.telepooz.tags.FileId
import shapeless.tag.@@


/**
  * This object represents a file ready to be downloaded. The file can be downloaded via the link
  * [[https://api.telegram.org/file/bot<token>/<file_path>]]. It is guaranteed that the link will be
  * valid for at least 1 hour. When the link expires, a new one can be requested by calling [[GetFile]].
  *
  * @param fileId   Unique identifier for this file
  * @param fileSize File size, if known
  * @param filePath File path. Use [[https://api.telegram.org/file/bot<token>/<file_path>]] to get the file.
  */
case class File(fileId: String @@ FileId,
                fileSize: Option[Int],
                filePath: Option[String])
