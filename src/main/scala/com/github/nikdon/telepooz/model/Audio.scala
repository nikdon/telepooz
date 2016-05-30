package com.github.nikdon.telepooz.model

import com.github.nikdon.telepooz.{ToDTO, dto}
import com.github.nikdon.telepooz.tags.FileId
import shapeless.tag.@@
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
case class Audio(fileId: String @@ FileId,
                 duration: Duration,
                 performer: Option[String],
                 title: Option[String],
                 mimeType: Option[String],
                 fileSize: Option[Int])

object Audio {
  implicit val toDTO: ToDTO[Audio, dto.Audio] =
    ToDTO(a ⇒ dto.Audio(a.fileId, a.duration.getSeconds.toInt, a.performer, a.title, a.mimeType, a.fileSize))
}
