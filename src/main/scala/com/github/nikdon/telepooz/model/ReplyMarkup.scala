package com.github.nikdon.telepooz.model


sealed trait ReplyMarkup extends Product with Serializable

/**
  * This object represents a custom keyboard with reply options (see Introduction to bots for details and examples).
  *
  * @param keyboard         Array of button rows, each represented by an Array of [[KeyboardButton]] objects
  * @param resizeKeyboard   Requests clients to resize the keyboard vertically for optimal
  *                         fit (e.g., make the keyboard smaller if there are just two rows of buttons).
  *                         Defaults to false, in which case the custom keyboard is always of the same
  *                         height as the app's standard keyboard.
  * @param oneTimeKeyBoard  Requests clients to hide the keyboard as soon as it's been used.
  *                         The keyboard will still be available, but clients will automatically display
  *                         the usual letter-keyboard in the chat – the user can press a special
  *                         button in the input field to see the custom keyboard again. Defaults to false.
  * @param selective        Use this parameter if you want to show the keyboard to specific users only.
  *                         Targets:
  *                         1) users that are @mentioned in the text of the Message object;
  *                         2) if the bot's message is a reply (has reply_to_message_id), sender of the original message.
  */
case class ReplyKeyboardMarkup(keyboard: Vector[Vector[KeyboardButton]],
                               resizeKeyboard: Option[Boolean],
                               oneTimeKeyBoard: Option[Boolean],
                               selective: Option[Boolean]) extends ReplyMarkup

/**
  * This object represents an inline keyboard that appears right next to the message it belongs to.
  *
  * @param keyboard Array of button rows, each represented by an Array of [[InlineKeyboardButton]] objects
  */
case class InlineKeyboardMarkup(keyboard: Vector[Vector[InlineKeyboardButton]]) extends ReplyMarkup

/**
  * Upon receiving a message with this object, Telegram clients will display a reply
  * interface to the user (act as if the user has selected the bot‘s message and tapped ’Reply').
  * This can be extremely useful if you want to create user-friendly step-by-step
  * interfaces without having to sacrifice privacy mode.
  *
  * @param forceReply Shows reply interface to the user, as if they manually selected the bot‘s message and tapped ’Reply'
  * @param selective  Use this parameter if you want to force reply from specific users only.
  *                   Targets:
  *                     1) users that are @mentioned in the text of the Message object;
  *                     2) if the bot's message is a reply (has reply_to_message_id), sender of the original message.
  */
case class ForceReply(forceReply: Boolean, selective: Option[Boolean]) extends ReplyMarkup
