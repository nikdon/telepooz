package com.github.nikdon.dto

import java.time.Duration

import com.github.nikdon.ToModel.syntax._
import com.github.nikdon.tags.syntax._
import com.github.nikdon.{ToModel, dto, model}


/**
  * This object represents a video file.
  *
  * @param file_id    Unique identifier for this file
  * @param width      Video width as defined by sender
  * @param height     Video height as defined by sender
  * @param duration   Duration of the video in seconds as defined by sender
  * @param thumb      Video thumbnail
  * @param mime_type  Mime type of a file as defined by sender
  * @param file_size  File size
  */
case class Video(file_id: String,
                 width: Int,
                 height: Int,
                 duration: Int,
                 thumb: Option[dto.PhotoSize],
                 mime_type: Option[String],
                 file_size: Option[Int])

object Video {
  implicit val toModel: ToModel[Video, model.Video] =
    ToModel(v ⇒ model.Video(v.file_id.fileId,
                            v.width,
                            v.height,
                            Duration.ofSeconds(v.duration.toLong),
                            v.thumb.map(_.toModel),
                            v.mime_type,
                            v.file_size))
}
