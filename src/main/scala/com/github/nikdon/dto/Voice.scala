package com.github.nikdon.dto

import java.time.Duration

import com.github.nikdon.tags.syntax._
import com.github.nikdon.{ToModel, model}


/**
  * This object represents a voice note.
  *
  * @param file_id    Unique identifier for this file
  * @param duration   Duration of the audio in seconds as defined by sender
  * @param mime_type  MIME type of the file as defined by sender
  * @param file_size  File size
  */
case class Voice(file_id: String,
                 duration: Int,
                 mime_type: String,
                 file_size: Int)

object Voice {
  implicit val toModel: ToModel[Voice, model.Voice] =
    ToModel(v ⇒ model.Voice(v.file_id.fileId,
                            Duration.ofSeconds(v.duration.toLong),
                            v.mime_type,
                            v.file_size))
}
