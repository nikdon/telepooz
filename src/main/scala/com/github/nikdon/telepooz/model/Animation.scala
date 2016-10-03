package com.github.nikdon.telepooz.model

import com.github.nikdon.telepooz.tags.FileId
import shapeless.tag.@@


/**
  * You can provide an animation for your game so that it looks stylish in chats (check out Lumberjack for an example).
  * This object represents an animation file to be displayed in the message containing a game.
  *
  * @param file_id   Unique file identifier
  * @param thumb     Animation thumbnail as defined by sender
  * @param file_name Original animation filename as defined by sender
  * @param mime_type MIME type of the file as defined by sender
  * @param file_size File size
  */
case class Animation(
  file_id: String @@ FileId,
  thumb: Option[PhotoSize] = None,
  file_name: Option[String] = None,
  mime_type: Option[String] = None,
  file_size: Option[Int] = None
)
