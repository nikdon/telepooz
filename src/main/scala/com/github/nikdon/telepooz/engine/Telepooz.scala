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

package com.github.nikdon.telepooz.engine

import akka.Done
import akka.actor.ActorSystem
import akka.event.{LogSource, Logging, LoggingAdapter}
import akka.stream.scaladsl.Source
import akka.stream.{ActorMaterializer, Materializer}
import cats.data.ReaderT

import scala.concurrent.{ExecutionContextExecutor, Future}

trait Telepooz {

  implicit val system: ActorSystem                = ActorSystem("Telepooz")
  implicit val executor: ExecutionContextExecutor = system.dispatcher
  implicit val materializer: Materializer         = ActorMaterializer()

  implicit val logger: LoggingAdapter = Logging(system, getClass)(LogSource.fromClass)

  def instance = ReaderT[Future, (ApiRequestExecutor, Polling, Reactor), Done] {
    case (are, poller, reactor) ⇒
      Source.fromGraph(poller.pollGraph).runWith(reactor.react)
  }

  def webhook = ReaderT[Future, (Webhook, Reactor), Done] {
    case (wh, reactor) ⇒ wh.source.runWith(reactor.react)
  }
}
