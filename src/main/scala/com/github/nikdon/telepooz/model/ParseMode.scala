package com.github.nikdon.telepooz.model

import com.github.nikdon.telepooz._


sealed trait ParseMode extends Product with Serializable {
  def name = this.productPrefix
}

object ParseMode {

  case object HTML extends ParseMode {override val name = super.name}
  case object Markdown extends ParseMode {override val name = super.name}

  def unsafe(str: String): ParseMode = str match {
    case HTML.name     ⇒ HTML
    case Markdown.name ⇒ Markdown
    case _             ⇒ unexpected(str)
  }
}
