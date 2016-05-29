package com.github.nikdon.model

import com.github.nikdon._


sealed trait ParseMode extends Product with Serializable {
  def name = this.productPrefix.toLowerCase
}

object ParseMode {
  def unsafe(str: String): ParseMode = str.toLowerCase match {
    case "html"     ⇒ HTML
    case "markdown" ⇒ Markdown
    case _          ⇒ unexpected(str)
  }
}

case object HTML extends ParseMode
case object Markdown extends ParseMode
