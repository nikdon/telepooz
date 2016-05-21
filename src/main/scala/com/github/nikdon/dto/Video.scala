package com.github.nikdon.dto

import java.time.Duration

import com.github.nikdon.ToModel.syntax._
import com.github.nikdon.tags.syntax._
import com.github.nikdon.{ToModel, dto, model}


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
case class Video(fileId: String,
                 width: Int,
                 height: Int,
                 duration: Int,
                 thumb: Option[dto.PhotoSize],
                 mimeType: Option[String],
                 fileSize: Option[Int])

object Video {
  implicit val toModel: ToModel[Video, model.Video] =
    ToModel(v ⇒ model.Video(v.fileId.fileId,
                            v.width,
                            v.height,
                            Duration.ofSeconds(v.duration.toLong),
                            v.thumb.map(_.toModel),
                            v.mimeType,
                            v.fileSize))
}
