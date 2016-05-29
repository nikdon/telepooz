package com.github.nikdon

object utils {

  def snakecase(s: String): String = s.foldLeft(new StringBuilder) {
    case (b, c) if Character.isUpperCase(c) ⇒ b.append("_").append(Character.toLowerCase(c))
    case (b, c)                             ⇒ b.append(c)
  }.toString

}
