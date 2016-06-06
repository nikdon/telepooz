package com.github.nikdon.telepooz.model

import java.time.Duration

import com.github.nikdon.telepooz.tags.FileId
import shapeless.tag.@@


/**
  * This object represents a voice note.
  *
  * @param file_id    Unique identifier for this file
  * @param duration   Duration of the audio in seconds as defined by sender
  * @param mime_type  MIME type of the file as defined by sender
  * @param file_size  File size
  */
case class Voice(file_id: String @@ FileId,
                 duration: Duration,
                 mime_type: String,
                 file_size: Int)
