package com.github.nikdon.telepooz.model

import com.github.nikdon.telepooz.model
import com.github.nikdon.telepooz.tags.FileId
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
