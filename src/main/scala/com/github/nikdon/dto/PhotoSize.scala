package com.github.nikdon.dto

import com.github.nikdon.{ToModel, dto, model}
import com.github.nikdon.tags.syntax._


/**
  * This object represents one size of a photo or a file / sticker thumbnail.
  *
  * @param file_id   Unique identifier for this file
  * @param width     Photo width
  * @param height    Photo height
  * @param file_size File size
  */
case class PhotoSize(file_id: String,
                     width: Int,
                     height: Int,
                     file_size: Option[Int])

object PhotoSize {
  implicit val photoSizeToModel: ToModel[dto.PhotoSize, model.PhotoSize] =
    ToModel(p ⇒ model.PhotoSize(p.file_id.fileId, p.width, p.height, p.file_size))
}
