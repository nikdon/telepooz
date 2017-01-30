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

package com.github.nikdon.telepooz.model.methods

import com.github.nikdon.telepooz.model.Response

/**
  * Use this method to specify a url and receive incoming updates via an outgoing webhook.
  * Whenever there is an update for the bot, we will send an HTTPS POST request to the specified url,
  * containing a JSON-serialized Update. In case of an unsuccessful request, we will give up after a
  * reasonable amount of attempts. Returns true.
  *
  * If you'd like to make sure that the Webhook request comes from Telegram, we recommend using a secret path in the
  * URL, e.g. https://www.example.com/<token>. Since nobody else knows your bot‘s token, you can be pretty sure it’s us.
  *
  * @param url              HTTPS url to send updates to. Use an empty string to remove webhook integration
//  * @param certificate      Upload your public key certificate so that the root certificate in use can be checked.
//                            See our self-signed guide for details.
  * @param max_connections  Maximum allowed number of simultaneous HTTPS connections to the webhook for update
  *                         delivery, 1-100. Defaults to 40. Use lower values to limit the load on your bot‘s server,
  *                         and higher values to increase your bot’s throughput.
  * @param allowed_updates  List the types of updates you want your bot to receive.
  *                         For example, specify [“message”, “edited_channel_post”, “callback_query”] to only
  *                         receive updates of these types. See Update for a complete list of available update types.
  *                         Specify an empty list to receive all updates regardless of type (default).
  *                         If not specified, the previous setting will be used.
  */
case class SetWebhook(
    url: String
    /*, certificate: InputFile */,
    max_connections: Option[Int] = Some(40),
    allowed_updates: Option[List[String]] = None
) extends Method[Response[Boolean]]
