package com.github.nikdon.dto

import com.github.nikdon.{ToModel, model}
import com.github.nikdon.tags.syntax._
import java.time.Duration


/**
  * This object represents an audio file to be treated as music by the Telegram clients.
  *
  * @param file_id    Unique identifier for this file
  * @param duration   Duration of the audio in seconds as defined by sender
  * @param performer  Performer of the audio as defined by sender or by audio tags
  * @param title      Title of the audio as defined by sender or by audio tags
  * @param mime_type  MIME type of the file as defined by sender
  * @param file_size  File size
  */
case class Audio(file_id: String,
                 duration: Int,
                 performer: Option[String] = None,
                 title: Option[String] = None,
                 mime_type: Option[String] = None,
                 file_size: Option[Int] = None)

object Audio {
  implicit val toModel: ToModel[Audio, model.Audio] =
    ToModel(a ⇒ model.Audio(a.file_id.fileId,
                            Duration.ofSeconds(a.duration.toLong),
                            a.performer,
                            a.title,
                            a.mime_type,
                            a.file_size))
}
