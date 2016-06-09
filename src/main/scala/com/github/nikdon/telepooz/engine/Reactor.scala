package com.github.nikdon.telepooz.engine

import akka.event.LoggingAdapter
import akka.stream.scaladsl.Sink
import com.github.nikdon.telepooz.model
import com.github.nikdon.telepooz.model.Update


trait Reactor { poller: Poller ⇒

  protected def logger: LoggingAdapter

  type Reaction = model.Message ⇒ Seq[String] ⇒ Unit
  def reactions: Map[String, Reaction]

  /** Sink that for each incoming [[Update]] reacts according to an actions in [[reactions]] */
  def react = Sink.foreach[Update](update ⇒ update.message match {
    case None ⇒ logger.debug(s"Update ${update.update_id} with empty message")
    case Some(m) ⇒
      val reaction = for {
        text ← m.text
        Array(cmd, args@_*) = text.trim.split(" ")
        reaction ← reactions.get(cmd.toLowerCase)
      } yield reaction(m)(args)
      reaction.fold(logger.debug(s"No reaction found for the update ${update.update_id}"))(identity)
  })

}
