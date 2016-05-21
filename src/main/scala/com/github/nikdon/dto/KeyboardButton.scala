package com.github.nikdon.dto

import com.github.nikdon.{ToModel, model}


/**
  * This object represents one button of the reply keyboard.
  * For simple text buttons String can be used instead of this object to specify text of the button.
  * Optional fields are mutually exclusive.
  *
  * @param text             Text of the button.
  *                         If none of the optional fields are used, it will be
  *                         sent to the bot as a message when the button is pressed
  * @param requestContact   If True, the user's phone number will be sent as a
  *                         contact when the button is pressed. Available in private chats only
  * @param requestLocation  If True, the user's current location will be sent when
  *                         the button is pressed. Available in private chats only
  */
case class KeyboardButton(text: String,
                          requestContact: Option[Boolean],
                          requestLocation: Option[Boolean])

object KeyboardButton {
  implicit val toModel: ToModel[KeyboardButton, model.KeyboardButton] =
    ToModel(k ⇒ model.KeyboardButton(k.text, k.requestContact, k.requestLocation))
}
