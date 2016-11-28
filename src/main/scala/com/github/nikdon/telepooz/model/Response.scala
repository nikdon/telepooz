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
  * The response contains a JSON object, which always has a Boolean field ‘ok’ and may have an optional String
  * field ‘description’ with a human-readable description of the result. If ‘ok’ equals true, the request was
  * successful and the result of the query can be found in the ‘result’ field. In case of an unsuccessful request,
  * ‘ok’ equals false and the error is explained in the ‘description’. An Integer ‘error_code’ field is also
  * returned, but its contents are subject to change in the future.
  *
  * @param ok           Status of the request
  * @param result       The result of the query
  * @param description  Explanation of a possible error
  * @param error_code   The error code
  * @tparam T           A type of the expected model
  */
case class Response[T](ok: Boolean,
                       result: Option[T],
                       description: Option[String] = None,
                       error_code: Option[Int] = None)
