package com.github.nikdon.dto

import com.github.nikdon.tags.syntax._
import com.github.nikdon.{ToModel, model}


/**
  * This object represents a file ready to be downloaded. The file can be downloaded via the link
  * [[https://api.telegram.org/file/bot<token>/<file_path>]]. It is guaranteed that the link will be
  * valid for at least 1 hour. When the link expires, a new one can be requested by calling [[GetFile]].
  *
  * @param fileId   Unique identifier for this file
  * @param fileSize File size, if known
  * @param filePath File path. Use [[https://api.telegram.org/file/bot<token>/<file_path>]] to get the file.
  */
case class File(fileId: String,
                fileSize: Option[Int],
                filePath: Option[String])

object File {
  implicit val toModel: ToModel[File, model.File] =
    ToModel(f ⇒ model.File(f.fileId.fileId, f.fileSize, f.filePath))
}
