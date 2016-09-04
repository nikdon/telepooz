package com.github.nikdon.telepooz.engine

import akka.Done
import akka.actor.ActorSystem
import akka.event.Logging
import akka.stream.scaladsl.Source
import akka.stream.{ActorMaterializer, Materializer}
import cats.data.ReaderT

import scala.concurrent.{ExecutionContextExecutor, Future}


trait Telepooz {

  implicit val system: ActorSystem                = ActorSystem("Telepooz")
  implicit val executor: ExecutionContextExecutor = system.dispatcher
  implicit val materializer: Materializer         = ActorMaterializer()

  implicit val logger = Logging(system, getClass)

  def instance = ReaderT[Future, (ApiRequestExecutor, Polling, Reactor), Done] {
    case (are, poller, reactor) ⇒
      Source.fromGraph(poller.pollGraph).runWith(reactor.react)
  }

}
