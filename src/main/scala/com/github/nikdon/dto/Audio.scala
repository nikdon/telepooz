package com.github.nikdon.dto

import com.github.nikdon.{ToModel, model}
import com.github.nikdon.tags.syntax._
import java.time.Duration


/**
  * This object represents an audio file to be treated as music by the Telegram clients.
  *
  * @param fileId     Unique identifier for this file
  * @param duration   Duration of the audio in seconds as defined by sender
  * @param performer  Performer of the audio as defined by sender or by audio tags
  * @param title      Title of the audio as defined by sender or by audio tags
  * @param mimeType   MIME type of the file as defined by sender
  * @param fileSize   File size
  */
case class Audio(fileId: String,
                 duration: Int,
                 performer: Option[String],
                 title: Option[String],
                 mimeType: Option[String],
                 fileSize: Option[Int])

object Audio {
  implicit val toModel: ToModel[Audio, model.Audio] =
    ToModel(a ⇒ model.Audio(a.fileId.fileId, Duration.ofSeconds(a.duration.toLong), a.performer, a.title, a.mimeType, a.fileSize))
}
