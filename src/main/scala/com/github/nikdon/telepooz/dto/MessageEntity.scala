package com.github.nikdon.telepooz.dto

import com.github.nikdon.telepooz.model.MessageEntityType
import com.github.nikdon.telepooz.{ToModel, dto, model}


/**
  * This object represents one special entity in a text message. For example, hashtags, usernames, URLs, etc.
  *
  * @param `type` Type of the entity. One of mention (@username), hashtag,
  *               bot_command, url, email, bold (bold text), italic (italic text),
  *               code (monowidth string), pre (monowidth block), text_link (for clickable text URLs)
  * @param offset Offset in UTF-16 code units to the start of the entity
  * @param length Length of the entity in UTF-16 code units
  * @param url    For “text_link” only, url that will be opened after user taps on the text
  */
case class MessageEntity(`type`: String,
                         offset: Int,
                         length: Int,
                         url: Option[String])

object MessageEntity {
  implicit val messageEntityToModel: ToModel[dto.MessageEntity, model.MessageEntity] =
    ToModel(m ⇒ model.MessageEntity(MessageEntityType.unsafe(m.`type`), m.offset, m.length, m.url))
}
