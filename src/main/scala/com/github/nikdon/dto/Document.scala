package com.github.nikdon.dto

import com.github.nikdon.{ToModel, dto, model}
import com.github.nikdon.tags.syntax._
import com.github.nikdon.ToModel.syntax._


/**
  * This object represents a general file (as opposed to [[Photo]], [[VoiceMessage]] and [[AudioFile]]).
  *
  * @param fileId   Unique file identifier
  * @param thumb    Document thumbnail as defined by sender
  * @param fileName Original filename as defined by sender
  * @param mimeType MIME type of the file as defined by sender
  * @param fileSize File size
  */
case class Document(fileId: String,
                    thumb: Option[dto.PhotoSize],
                    fileName: Option[String],
                    mimeType: Option[String],
                    fileSize: Option[Int])

object Document {
  implicit val toModel: ToModel[Document, model.Document] =
    ToModel(d ⇒ model.Document(d.fileId.fileId, d.thumb.map(_.toModel), d.fileName, d.mimeType, d.fileSize))
}
