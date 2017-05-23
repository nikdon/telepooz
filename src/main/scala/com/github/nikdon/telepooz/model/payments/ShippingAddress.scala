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
  * This object represents a shipping address.
  *
  * @param country_code ISO 3166-1 alpha-2 country code
  * @param state        State, if applicable
  * @param city         City
  * @param street_line1 First line for the address
  * @param street_line2 Second line for the address
  * @param post_code    Address post code

  */
case class ShippingAddress(
    country_code: String,
    state: String,
    city: String,
    street_line1: String,
    street_line2: String,
    post_code: String
)
