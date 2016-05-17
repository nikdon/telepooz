package com.github.nikdon.model

import com.github.nikdon.tags.FileId
import shapeless.tag.@@


/**
  * This object represents one size of a photo or a file / sticker thumbnail.
  *
  * @param fileId   Unique identifier for this file
  * @param width    Photo width
  * @param height   Photo height
  * @param fileSize File size
  */
case class PhotoSize(fileId: String @@ FileId,
                     width: Int,
                     height: Int,
                     fileSize: Option[Int])
