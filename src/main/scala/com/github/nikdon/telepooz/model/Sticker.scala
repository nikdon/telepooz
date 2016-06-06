package com.github.nikdon.telepooz.model

import com.github.nikdon.telepooz.model
import com.github.nikdon.telepooz.tags.FileId
import shapeless.tag.@@


/**
  * This object represents a sticker.
  *
  * @param file_id    Unique identifier for this file
  * @param width      Sticker width
  * @param height     Sticker height
  * @param thumb      Sticker thumbnail in .webp or .jpg format
  * @param emoji      Emoji associated with the sticker
  * @param file_size  File size
  */
case class Sticker(file_id: String @@ FileId,
                   width: Int,
                   height: Int,
                   thumb: Option[model.PhotoSize],
                   emoji: Option[String],
                   file_size: Option[Int])
