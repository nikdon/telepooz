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

package com.github.nikdon.telepooz.model.methods.payments

import com.github.nikdon.telepooz.model.{InlineKeyboardMarkup, Message, Response}
import com.github.nikdon.telepooz.model.methods.Method
import com.github.nikdon.telepooz.model.payments.LabeledPrice

/**
  *
  *
  * @param chat_id 	              Unique identifier for the target private chat
  * @param title 	                Product name
  * @param description 	          Product description
  * @param payload 	              Bot-defined invoice payload, 1-128 bytes. This will not be displayed to the user, use for your internal processes.
  * @param provider_token 	      Payments provider token, obtained via Botfather
  * @param start_parameter 	      Unique deep-linking parameter that can be used to generate this invoice when used as a start parameter
  * @param currency 	            Three-letter ISO 4217 currency code, see more on currencies
  * @param prices                 Price breakdown, a list of components (e.g. product price, tax, discount, delivery cost, delivery tax, bonus, etc.)
  * @param photo_url 	            URL of the product photo for the invoice. Can be a photo of the goods or a marketing image for a service. People like it better when they see what they are paying for.
  * @param photo_size 	          Photo size
  * @param photo_width 	          Photo width
  * @param photo_height 	        Photo height
  * @param need_name 	            Pass True, if you require the user's full name to complete the order
  * @param need_phone_number 	    Pass True, if you require the user's phone number to complete the order
  * @param need_email 	          Pass True, if you require the user's email to complete the order
  * @param need_shipping_address  Pass True, if you require the user's shipping address to complete the order
  * @param is_flexible 	          Pass True, if the final price depends on the shipping method
  * @param disable_notification 	Sends the message silently. Users will receive a notification with no sound.
  * @param reply_to_message_id 	  If the message is a reply, ID of the original message
  * @param reply_markup 	        A JSON-serialized object for an inline keyboard. If empty, one 'Pay total price' button will be shown. If not empty, the first button must be a Pay button.
  */
case class SendInvoice(
    chat_id: Int,
    title: String,
    description: String,
    payload: String,
    provider_token: String,
    start_parameter: String,
    currency: String,
    prices: List[LabeledPrice],
    photo_url: Option[String] = None,
    photo_size: Option[Int] = None,
    photo_width: Option[Int] = None,
    photo_height: Option[Int] = None,
    need_name: Option[Boolean] = None,
    need_phone_number: Option[Boolean] = None,
    need_email: Option[Boolean] = None,
    need_shipping_address: Option[Boolean] = None,
    is_flexible: Option[Boolean] = None,
    disable_notification: Option[Boolean] = None,
    reply_to_message_id: Option[Int] = None,
    reply_markup: Option[InlineKeyboardMarkup] = None
) extends Method[Response[Message]]
