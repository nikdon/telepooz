package com.github.nikdon.dto

import com.github.nikdon.ToModel.syntax._
import com.github.nikdon.tags.syntax._
import com.github.nikdon.{ToModel, dto, model}


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
case class Sticker(fileId: String,
                   width: Int,
                   height: Int,
                   thumb: Option[dto.PhotoSize],
                   emoji: Option[String],
                   fileSize: Option[Int])

object Sticker {
  implicit val toModel: ToModel[Sticker, model.Sticker] =
    ToModel(s ⇒ model.Sticker(s.fileId.fileId, s.width, s.height, s.thumb.map(_.toModel), s.emoji, s.fileSize))
}
