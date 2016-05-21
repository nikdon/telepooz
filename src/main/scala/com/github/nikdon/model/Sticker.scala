package com.github.nikdon.model

import com.github.nikdon.ToDTO.syntax._
import com.github.nikdon.tags.FileId
import com.github.nikdon.{ToDTO, dto, model}
import shapeless.tag.@@


/**
  * This object represents a sticker.
  *
  * @param fileId   Unique identifier for this file
  * @param width    Sticker width
  * @param height   Sticker height
  * @param thumb    Sticker thumbnail in .webp or .jpg format
  * @param emoji    Emoji associated with the sticker
  * @param fileSize File size
  */
case class Sticker(fileId: String @@ FileId,
                   width: Int,
                   height: Int,
                   thumb: Option[model.PhotoSize],
                   emoji: Option[String],
                   fileSize: Option[Int])

object Sticker {
  implicit val toDTO: ToDTO[Sticker, dto.Sticker] =
    ToDTO(s ⇒ dto.Sticker(s.fileId, s.width, s.height, s.thumb.map(_.toDTO), s.emoji, s.fileSize))
}
