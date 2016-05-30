package com.github.nikdon.telepooz.model

import com.github.nikdon.telepooz.{ToDTO, dto, model}
import com.github.nikdon.telepooz.utils._


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
case class MessageEntity(`type`: MessageEntityType,
                         offset: Int,
                         length: Int,
                         url: Option[String])

object MessageEntity {
  implicit val messageEntityToDTO: ToDTO[model.MessageEntity, dto.MessageEntity] =
    ToDTO(m ⇒ dto.MessageEntity(m.`type`.name, m.offset, m.length, m.url))
}


sealed trait MessageEntityType extends Product with Serializable {
  def name: String = snakecase { this.productPrefix }
}

object MessageEntityType {

  case object Mention extends MessageEntityType
  case object Hashtag extends MessageEntityType
  case object BotCommand extends MessageEntityType
  case object Url extends MessageEntityType
  case object Email extends MessageEntityType
  case object Bold extends MessageEntityType
  case object Italic extends MessageEntityType
  case object Code extends MessageEntityType
  case object Pre extends MessageEntityType
  case object TextLink extends MessageEntityType

  def unsafe(str: String): MessageEntityType = str match {
    case "mention"     ⇒ Mention
    case "hashtag"     ⇒ Hashtag
    case "bot_command" ⇒ BotCommand
    case "url"         ⇒ Url
    case "email"       ⇒ Email
    case "bold"        ⇒ Bold
    case "italic"      ⇒ Italic
    case "code"        ⇒ Code
    case "pre"         ⇒ Pre
    case "text_link"   ⇒ TextLink
    case _             ⇒ sys.error("unexpected")
  }
}
