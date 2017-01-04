/*
 * Copyright 2016 Nikolay Donets
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.nikdon.telepooz.model


/**
  * This object represents one button of an inline keyboard. You must use exactly one of the optional fields.
  *
  * @param text                 Label text on the button
  * @param url                  HTTP url to be opened when button is pressed
  * @param callback_data        Data to be sent in a callback query to the bot when button is pressed, 1-64 bytes
  * @param switch_inline_query  If set, pressing the button will prompt the user to select one of their chats,
  *                             open that chat and insert the bot‘s username and the specified inline
  *                             query in the input field. Can be empty, in which case just the bot’s username
  *                             will be inserted.
  * @param switch_inline_query_current_chat If set, pressing the button will insert the bot‘s username and the specified
  *                                         inline query in the current chat's input field. Can be empty, in which case
  *                                         only the bot’s username will be inserted.
  * @param callback_game        Description of the game that will be launched when the user presses the button.
  *                             NOTE: This type of button must always be the first button in the first row.
  */
case class InlineKeyboardButton(text: String,
                                url: Option[String] = None,
                                callback_data: Option[String] = None,
                                switch_inline_query: Option[String] = None,
                                switch_inline_query_current_chat: Option[String] = None,
                                callback_game: Option[CallbackGame] = None)
