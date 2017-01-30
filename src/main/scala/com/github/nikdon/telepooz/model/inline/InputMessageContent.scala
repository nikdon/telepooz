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

package com.github.nikdon.telepooz.model.inline

import com.github.nikdon.telepooz.model.ParseMode


/** This object represents the content of a message to be sent as a result of an inline query. */
sealed trait InputMessageContent


/**
  * Represents the content of a text message to be sent as the result of an inline query.
  *
  * @param message_text             Text of the message to be sent, 1-4096 characters
  * @param parse_mode               Send Markdown or HTML, if you want Telegram apps to show bold, italic,
  *                                 fixed-width text or inline URLs in your bot's message.
  * @param disable_web_page_preview Disables link previews for links in the sent message
  */
case class InputTextMessageContent(message_text: String,
                                   parse_mode: Option[ParseMode] = None,
                                   disable_web_page_preview: Option[Boolean] = None) extends InputMessageContent


/**
  * Represents the content of a location message to be sent as the result of an inline query.
  *
  * @param latitude   Latitude of the location in degrees
  * @param longitude  Longitude of the location in degrees
  */
case class InputLocationMessageContent(latitude: Double, longitude: Double) extends InputMessageContent


/**
  * Represents the content of a venue message to be sent as the result of an inline query.
  *
  * @param latitude       Latitude of the venue in degrees
  * @param longitude      Longitude of the venue in degrees
  * @param title          Name of the venue
  * @param address        Address of the venue
  * @param foursquare_id  Foursquare identifier of the venue, if known
  */
case class InputVenueMessageContent(latitude: Double,
                                    longitude: Double,
                                    title: String,
                                    address: String,
                                    foursquare_id: Option[String]) extends InputMessageContent


/**
  * Represents the content of a contact message to be sent as the result of an inline query.
  *
  * @param phone_number Contact's phone number
  * @param first_name   Contact's first name
  * @param last_name    Contact's last name
  */
case class InputContactMessageContent(phone_number: String,
                                      first_name: String,
                                      last_name: Option[String] = None) extends InputMessageContent
