package com.github.nikdon.telepooz.model

/**
  * This object represents a chat photo.
  *
  * @param small_file_id  Unique file identifier of small (160x160) chat photo. This file_id can be used only for photo download.
  * @param big_file_id  Unique file identifier of big (640x640) chat photo. This file_id can be used only for photo download.
  */
final case class ChatPhoto(
    small_file_id: String,
    big_file_id: String
)
