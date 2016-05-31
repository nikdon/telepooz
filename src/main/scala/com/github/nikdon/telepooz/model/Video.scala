package com.github.nikdon.telepooz.model


import java.time.Duration

import com.github.nikdon.telepooz.model
import com.github.nikdon.telepooz.tags.FileId
import shapeless.tag.@@


/**
  * This object represents a video file.
  *
  * @param fileId   Unique identifier for this file
  * @param width    Video width as defined by sender
  * @param height   Video height as defined by sender
  * @param duration Duration of the video in seconds as defined by sender
  * @param thumb    Video thumbnail
  * @param mimeType Mime type of a file as defined by sender
  * @param fileSize File size
  */
case class Video(fileId: String @@ FileId,
                 width: Int,
                 height: Int,
                 duration: Duration,
                 thumb: Option[model.PhotoSize],
                 mimeType: Option[String],
                 fileSize: Option[Int])
