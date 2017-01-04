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
  * Contains information about the current status of a webhook.
  *
  * @param url                    Webhook URL, may be empty if webhook is not set up
  * @param has_custom_certificate True, if a custom certificate was provided for webhook certificate checks
  * @param pending_update_count   Number of updates awaiting delivery
  * @param last_error_date        Unix time for the most recent error that happened when trying to deliver an update via
  *                               webhook
  * @param last_error_message     Error message in human-readable format for the most recent error that happened when
  *                               trying to deliver an update via webhook
  */
case class WebhookInfo(
  url: String,
  has_custom_certificate: Boolean,
  pending_update_count: Long,
  last_error_date: Long,
  last_error_message: String
)
