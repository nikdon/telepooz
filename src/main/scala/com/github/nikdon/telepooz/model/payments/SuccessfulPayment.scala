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

package com.github.nikdon.telepooz.model.payments

/**
  * This object contains basic information about a successful payment.
  *
  * @param currency                   Three-letter ISO 4217 currency code
  * @param total_amount               Total price in the smallest units of the currency (integer, not float/double).
  *                                   For example, for a price of US$ 1.45 pass amount = 145.
  *                                   See the exp parameter in currencies.json, it shows the number of digits past
  *                                   the decimal point for each currency (2 for the majority of currencies).
  * @param invoice_payload            Bot specified invoice payload
  * @param shipping_option_id         Optional. Identifier of the shipping option chosen by the user
  * @param order_info                 Optional. Order info provided by the user
  * @param telegram_payment_charge_id Telegram payment identifier
  * @param provider_payment_charge_id Provider payment identifier
  */
case class SuccessfulPayment(
    currency: String,
    total_amount: Int,
    invoice_payload: String,
    shipping_option_id: Option[String] = None,
    order_info: Option[OrderInfo] = None,
    telegram_payment_charge_id: String,
    provider_payment_charge_id: String
)
