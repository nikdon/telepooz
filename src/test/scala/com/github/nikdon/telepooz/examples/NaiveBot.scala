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

import com.github.nikdon.telepooz.engine.{ApiRequestExecutor, Polling, CommandBasedReactions, Reactor, Telepooz}


/** Just an example of how the bot might look like */
object NaiveBot extends Telepooz with App {

  implicit val are = new ApiRequestExecutor {}
  val poller       = new Polling
  val reactor      = new Reactor {
    val reactions = CommandBasedReactions()
      .on("/start")(implicit message ⇒ args ⇒ reply("You are started!"))
      .on("/test")(implicit message ⇒ args ⇒ reply("Hi there!"))
  }

  instance.run((are, poller, reactor))
}
