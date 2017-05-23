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

import com.github.nikdon.telepooz.model.User

/**
  * This object contains information about an incoming shipping query.
  *
  * @param id               Unique query identifier
  * @param from             User who sent the query
  * @param invoice_payload  Bot specified invoice payload
  * @param shipping_address User specified shipping address
  */
case class ShippingQuery(
    id: String,
    from: User,
    invoice_payload: String,
    shipping_address: ShippingAddress
)


