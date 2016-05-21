package com.github.nikdon.dto

import com.github.nikdon.{ToModel, dto, model}
import com.github.nikdon.tags.syntax._


/**
  * This object represents one size of a photo or a file / sticker thumbnail.
  *
  * @param fileId   Unique identifier for this file
  * @param width    Photo width
  * @param height   Photo height
  * @param fileSize File size
  */
case class PhotoSize(fileId: String,
                     width: Int,
                     height: Int,
                     fileSize: Option[Int])

object PhotoSize {
  implicit val photoSizeToModel: ToModel[dto.PhotoSize, model.PhotoSize] =
    ToModel(p ⇒ model.PhotoSize(p.fileId.fileId, p.width, p.height, p.fileSize))
}
