package com.github.nikdon.telepooz.examples

import com.github.nikdon.telepooz.engine.{MockApiRequestExecutor, Polling, Reactor, Telepooz}


/** This bot produce 1000 messages every tick and try to respond on them */
object SelfBot extends Telepooz with App {

  implicit val are = new MockApiRequestExecutor(1000)
  val poller = new Polling
  val reactor = new Reactor {
    /** Initialize as lazy val */
    lazy val reactions: Map[String, Reaction] = Map()
  }

  instance.run((are, poller, reactor))
}
