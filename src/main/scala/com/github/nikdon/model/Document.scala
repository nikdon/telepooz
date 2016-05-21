package com.github.nikdon.model

import com.github.nikdon.ToDTO
import com.github.nikdon.ToDTO.syntax._
import com.github.nikdon.dto
import com.github.nikdon.tags.FileId
import shapeless.tag.@@


/**
  * This object represents a general file (as opposed to [[Photo]], [[VoiceMessage]] and [[AudioFile]]).
  *
  * @param fileId   Unique file identifier
  * @param thumb    Document thumbnail as defined by sender
  * @param fileName Original filename as defined by sender
  * @param mimeType MIME type of the file as defined by sender
  * @param fileSize File size
  */
case class Document(fileId: String @@ FileId,
                    thumb: Option[PhotoSize],
                    fileName: Option[String],
                    mimeType: Option[String],
                    fileSize: Option[Int])

object Document {
  implicit val toDTO: ToDTO[Document, dto.Document] =
    ToDTO(d ⇒ dto.Document(d.fileId, d.thumb.map(_.toDTO), d.fileName, d.mimeType, d.fileSize))
}
