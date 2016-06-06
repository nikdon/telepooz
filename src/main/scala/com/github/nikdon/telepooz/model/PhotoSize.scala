package com.github.nikdon.telepooz.model

import com.github.nikdon.telepooz.tags.FileId
import shapeless.tag.@@


/**
  * This object represents one size of a photo or a file / sticker thumbnail.
  *
  * @param file_id    Unique identifier for this file
  * @param width      Photo width
  * @param height     Photo height
  * @param file_size  File size
  */
case class PhotoSize(file_id: String @@ FileId,
                     width: Int,
                     height: Int,
                     file_size: Option[Int])
