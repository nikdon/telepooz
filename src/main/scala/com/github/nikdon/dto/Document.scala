package com.github.nikdon.dto

import com.github.nikdon.{ToModel, dto, model}
import com.github.nikdon.tags.syntax._
import com.github.nikdon.ToModel.syntax._


/**
  * This object represents a general file (as opposed to [[Photo]], [[VoiceMessage]] and [[AudioFile]]).
  *
  * @param file_id    Unique file identifier
  * @param thumb      Document thumbnail as defined by sender
  * @param file_name  Original filename as defined by sender
  * @param mime_type  MIME type of the file as defined by sender
  * @param file_size  File size
  */
case class Document(file_id: String,
                    thumb: Option[dto.PhotoSize] = None,
                    file_name: Option[String] = None,
                    mime_type: Option[String] = None,
                    file_size: Option[Int] = None)

object Document {
  implicit val toModel: ToModel[Document, model.Document] =
    ToModel(d ⇒ model.Document(d.file_id.fileId, d.thumb.map(_.toModel), d.file_name, d.mime_type, d.file_size))
}
