package com.github.nikdon.telepooz.model


import java.time.Duration

import com.github.nikdon.telepooz.model
import com.github.nikdon.telepooz.tags.FileId
import shapeless.tag.@@


case class Video(fileId: String @@ FileId,
                 width: Int,
                 height: Int,
                 duration: Duration,
                 thumb: Option[model.PhotoSize],
                 mimeType: Option[String],
                 fileSize: Option[Int])
