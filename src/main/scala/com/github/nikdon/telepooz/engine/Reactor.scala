package com.github.nikdon.telepooz.engine

import akka.Done
import akka.event.LoggingAdapter
import akka.stream.scaladsl.Sink
import com.github.nikdon.telepooz.model
import com.github.nikdon.telepooz.model.Update

import scala.concurrent.Future


trait Reactor { poller: Poller ⇒

  protected def logger: LoggingAdapter

  type Args = Seq[String]
  type Reaction = model.Message ⇒ Args ⇒ Future[Done.type]

  /** This map contains a reactions on incoming messages */
  def reactions: Map[String, Reaction]

  /** Sink that for each incoming [[Update]] reacts according to an actions in [[reactions]] */
  def react = Sink.foreach[Update](update ⇒ update.message match {
    case None ⇒ logger.debug(s"Update ${update.update_id} with empty message")
    case Some(m) ⇒
      val maybeReaction = for {
        text ← m.text
        Array(cmd, args@_*) = text.trim.split(" ")
        reaction ← reactions.get(cmd.toLowerCase)
      } yield reaction(m)(args)

      maybeReaction.fold({
        logger.debug(s"No reaction found for the update ${update.update_id}")
        Future.successful(Done)
      })(identity)
  })

}
