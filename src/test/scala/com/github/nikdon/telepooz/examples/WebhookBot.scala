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

package com.github.nikdon.telepooz.examples

import akka.stream.ActorMaterializer
import com.github.nikdon.telepooz.engine._

object WebhookBot extends Telepooz with App {

  implicit val asm = ActorMaterializer()
  implicit val are = new MockApiRequestExecutor(1) {}
  val poller       = new Webhook(endpoint = "test", interface = "127.0.0.1")
  val reactor = new Reactor {
    val reactions = CommandBasedReactions()
      .on("/start")(implicit message ⇒
        args ⇒ {
          println(s"You are started! $args")
          reply("You are started!")
      })
      .on("/test")(implicit message ⇒
        args ⇒ {
          println(s"You are tested! $args")
          reply("You are tested!")
      })
  }

  webhook.run((poller, reactor))
}
