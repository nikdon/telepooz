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
  * This object represents one button of the reply keyboard.
  * For simple text buttons String can be used instead of this object to specify text of the button.
  * Optional fields are mutually exclusive.
  *
  * @param text               Text of the button.
  *                           If none of the optional fields are used, it will be
  *                           sent to the bot as a message when the button is pressed
  * @param request_contact    If True, the user's phone number will be sent as a
  *                           contact when the button is pressed. Available in private chats only
  * @param request_location   If True, the user's current location will be sent when
  *                           the button is pressed. Available in private chats only
  */
case class KeyboardButton(text: String,
                          request_contact: Option[Boolean],
                          request_location: Option[Boolean])
