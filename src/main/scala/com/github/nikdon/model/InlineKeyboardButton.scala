package com.github.nikdon.model

import com.github.nikdon.{ToDTO, dto}


/**
  * This object represents one button of an inline keyboard. You must use exactly one of the optional fields.
  *
  * @param text               Label text on the button
  * @param url                HTTP url to be opened when button is pressed
  * @param callbackData       Data to be sent in a callback query to the bot when button is pressed, 1-64 bytes
  * @param switchInlineQuery  If set, pressing the button will prompt the user to select one of their chats,
  *                           open that chat and insert the bot‘s username and the specified inline
  *                           query in the input field. Can be empty, in which case just the bot’s username
  *                           will be inserted.
  */
case class InlineKeyboardButton(text: String,
                                url: Option[String],
                                callbackData: Option[String],
                                switchInlineQuery: Option[String])

object InlineKeyboardButton {
  implicit val toDTO: ToDTO[InlineKeyboardButton, dto.InlineKeyboardButton] =
    ToDTO(i ⇒ dto.InlineKeyboardButton(i.text, i.url, i.callbackData, i.switchInlineQuery))
}
