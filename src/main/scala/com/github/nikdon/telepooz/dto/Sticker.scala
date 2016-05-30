package com.github.nikdon.telepooz.dto

import com.github.nikdon.telepooz.ToModel.syntax._
import com.github.nikdon.telepooz.tags.syntax._
import com.github.nikdon.telepooz.{ToModel, dto, model}


/**
  * This object represents a sticker.
  *
  * @param file_id   Unique identifier for this file
  * @param width     Sticker width
  * @param height    Sticker height
  * @param thumb     Sticker thumbnail in .webp or .jpg format
  * @param emoji     Emoji associated with the sticker
  * @param file_size File size
  */
case class Sticker(file_id: String,
                   width: Int,
                   height: Int,
                   thumb: Option[dto.PhotoSize],
                   emoji: Option[String],
                   file_size: Option[Int])

object Sticker {
  implicit val toModel: ToModel[Sticker, model.Sticker] =
    ToModel(s ⇒ model.Sticker(s.file_id.fileId, s.width, s.height, s.thumb.map(_.toModel), s.emoji, s.file_size))
}
