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
  * This object contains basic information about an invoice.
  *
  * @param title            Product name
  * @param description      Product description
  * @param start_parameter  Unique bot deep-linking parameter that can be used to generate this invoice
  * @param currency         Three-letter ISO 4217 currency code
  * @param total_amount     Total price in the smallest units of the currency (integer, not float/double).
  *                         For example, for a price of US$ 1.45 pass amount = 145.
  *                         See the exp parameter in currencies.json, it shows the number of digits
  *                         past the decimal point for each currency (2 for the majority of currencies).
  */
case class Invoice(
    title: String,
    description: String,
    start_parameter: String,
    currency: String,
    total_amount: Int
)
