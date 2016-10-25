package com.github.nikdon.telepooz.model

import com.github.nikdon.telepooz.tags.FileId
import shapeless.tag.@@


/**
  * This object represents a general file (as opposed to Photo, VoiceMessage and AudioFile).
  *
  * @param file_id    Unique file identifier
  * @param thumb      Document thumbnail as defined by sender
  * @param file_name  Original filename as defined by sender
  * @param mime_type  MIME type of the file as defined by sender
  * @param file_size  File size
  */
case class Document(file_id: String @@ FileId,
                    thumb: Option[PhotoSize],
                    file_name: Option[String],
                    mime_type: Option[String],
                    file_size: Option[Int])
