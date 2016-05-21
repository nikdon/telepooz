package com.github.nikdon.dto

import com.github.nikdon.ToModel.syntax._
import com.github.nikdon.{ToModel, model}


/**
  * This object represents an inline keyboard that appears right next to the message it belongs to.
  *
  * @param keyboard Array of button rows, each represented by an Array of [[InlineKeyboardButton]] objects
  */
case class InlineKeyboardMarkup(keyboard: Vector[Vector[InlineKeyboardButton]])

object InlineKeyboardMarkup {
  implicit val toModel: ToModel[InlineKeyboardMarkup, model.InlineKeyboardMarkup] =
    ToModel(r ⇒ model.InlineKeyboardMarkup(r.keyboard.map(_.map(_.toModel))))
}
