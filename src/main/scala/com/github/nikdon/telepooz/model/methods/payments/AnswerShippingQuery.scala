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

import com.github.nikdon.telepooz.model.Response
import com.github.nikdon.telepooz.model.methods.Method
import com.github.nikdon.telepooz.model.payments.ShippingOption

/**
  * If you sent an invoice requesting a shipping address and the parameter is_flexible was specified,
  * the Bot API will send an `Update` with a shipping_query field to the bot. Use this method to reply
  * to shipping queries. On success, True is returned.
  *
  * @param shipping_query_id  Unique identifier for the query to be answered
  * @param ok                 Specify True if delivery to the specified address is possible and False if
  *                           there are any problems (for example, if delivery to the specified address
  *                           is not possible)
  * @param shipping_options   Required if ok is True. A JSON-serialized array of available shipping options.
  * @param error_message      Required if ok is False. Error message in human readable form that explains
  *                           why it is impossible to complete the order (e.g. "Sorry, delivery to your desired
  *                           address is unavailable'). Telegram will display this message to the user.
  */
case class AnswerShippingQuery(
  shipping_query_id: String,
  ok: Boolean,
  shipping_options: Option[List[ShippingOption]] = None,
  error_message: Option[String] = None
) extends Method[Response[Boolean]]
