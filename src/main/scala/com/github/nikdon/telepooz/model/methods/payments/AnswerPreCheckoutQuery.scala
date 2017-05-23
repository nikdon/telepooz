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

/**
  * Once the user has confirmed their payment and shipping details, the Bot API sends the final
  * confirmation in the form of an Update with the field pre_checkout_query. Use this method to
  * respond to such pre-checkout queries. On success, True is returned. Note: The Bot API must
  * receive an answer within 10 seconds after the pre-checkout query was sent.
  *
  * @param pre_checkout_query_id  Unique identifier for the query to be answered
  * @param ok                     Specify True if everything is alright (goods are available, etc.) and
  *                               the bot is ready to proceed with the order. Use False if there are any problems.
  * @param error_message          Required if ok is False. Error message in human readable form that explains
  *                               the reason for failure to proceed with the checkout (e.g. "Sorry,
  *                               somebody just bought the last of our amazing black T-shirts while you
  *                               were busy filling out your payment details. Please choose a different color or
  *                               garment!"). Telegram will display this message to the user.
  */
case class AnswerPreCheckoutQuery(
    pre_checkout_query_id: String,
    ok: Boolean,
    error_message: Option[String] = None
) extends Method[Response[Boolean]]
