package com.github.nikdon.model

import com.github.nikdon.ToDTO.syntax._
import com.github.nikdon.{ToDTO, dto}


/**
  * This object represents an inline keyboard that appears right next to the message it belongs to.
  *
  * @param keyboard Array of button rows, each represented by an Array of [[InlineKeyboardButton]] objects
  */
case class InlineKeyboardMarkup(keyboard: Vector[Vector[InlineKeyboardButton]])

object InlineKeyboardMarkup {
  implicit val toDTO: ToDTO[InlineKeyboardMarkup, dto.InlineKeyboardMarkup] =
    ToDTO(i ⇒ dto.InlineKeyboardMarkup(i.keyboard.map(_.map(_.toDTO))))
}
