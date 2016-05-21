package com.github.nikdon.dto

import java.time.Duration

import com.github.nikdon.tags.syntax._
import com.github.nikdon.{ToModel, model}


/**
  * This object represents a voice note.
  *
  * @param fileId   Unique identifier for this file
  * @param duration Duration of the audio in seconds as defined by sender
  * @param mimeType MIME type of the file as defined by sender
  * @param fileSize File size
  */
case class Voice(fileId: String,
                 duration: Int,
                 mimeType: String,
                 fileSize: Int)

object Voice {
  implicit val toModel: ToModel[Voice, model.Voice] =
    ToModel(v ⇒ model.Voice(v.fileId.fileId,
                            Duration.ofSeconds(v.duration.toLong),
                            v.mimeType,
                            v.fileSize))
}
