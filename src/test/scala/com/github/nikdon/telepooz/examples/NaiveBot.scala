package com.github.nikdon.telepooz.examples

import com.github.nikdon.telepooz.engine.{ApiRequestExecutor, Polling, Reactor, Telepooz}


/** Just an example of how the bot might look like */
object NaiveBot extends Telepooz with App {

  implicit val are = new ApiRequestExecutor {}
  val poller  = new Polling
  val reactor = new Reactor {

    /** Initialize as lazy val */
    lazy val reactions: Map[String, Reaction] = Map(
      "/start" → (implicit message ⇒ args ⇒ reply("You are started!")),
      "/test" → (implicit message ⇒ args ⇒ reply("Hi there!"))
    )
  }

  instance.run((are, poller, reactor))
}
