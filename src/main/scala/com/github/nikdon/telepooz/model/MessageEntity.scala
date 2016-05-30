package com.github.nikdon.telepooz.model

import com.github.nikdon.telepooz._


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


sealed trait MessageEntityType extends Product with Serializable {
  def name: String = this.productPrefix
}

object MessageEntityType {

  case object Mention extends MessageEntityType {override val name = super.name}
  case object Hashtag extends MessageEntityType {override val name = super.name}
  case object BotCommand extends MessageEntityType {override val name = super.name}
  case object Url extends MessageEntityType {override val name = super.name}
  case object Email extends MessageEntityType {override val name = super.name}
  case object Bold extends MessageEntityType {override val name = super.name}
  case object Italic extends MessageEntityType {override val name = super.name}
  case object Code extends MessageEntityType {override val name = super.name}
  case object Pre extends MessageEntityType {override val name = super.name}
  case object TextLink extends MessageEntityType {override val name = super.name}

  def unsafe(str: String): MessageEntityType = str match {
    case Mention.name    ⇒ Mention
    case Hashtag.name    ⇒ Hashtag
    case BotCommand.name ⇒ BotCommand
    case Url.name        ⇒ Url
    case Email.name      ⇒ Email
    case Bold.name       ⇒ Bold
    case Italic.name     ⇒ Italic
    case Code.name       ⇒ Code
    case Pre.name        ⇒ Pre
    case TextLink.name   ⇒ TextLink
    case _               ⇒ unexpected(str)
  }
}
