package com.github.nikdon.telepooz.model

import com.github.nikdon.telepooz.tags.FileId
import com.github.nikdon.telepooz.{ToDTO, dto, model}
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

object PhotoSize {
  implicit val photoSizeToDTO: ToDTO[model.PhotoSize, dto.PhotoSize] =
    ToDTO(p ⇒ dto.PhotoSize(p.fileId, p.width, p.height, p.fileSize))
}
