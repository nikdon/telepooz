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

import akka.actor.ActorSystem
import akka.event.Logging
import akka.stream.scaladsl.{Sink, Source}
import akka.stream.{ActorMaterializer, Materializer}
import com.github.nikdon.telepooz.model.Update
import org.scalatest.concurrent.{ScalaFutures, ScaledTimeSpans}
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{BeforeAndAfterAll, FlatSpec, Matchers}

import scala.concurrent.ExecutionContextExecutor


class PollingTest
    extends FlatSpec
    with Matchers
    with GeneratorDrivenPropertyChecks
    with ScalaFutures
    with BeforeAndAfterAll
    with ScaledTimeSpans {

  override def spanScaleFactor: Double = 20.0

  implicit val system: ActorSystem                = ActorSystem("PollingTestSystem")
  implicit val executor: ExecutionContextExecutor = system.dispatcher
  implicit val materializer: Materializer         = ActorMaterializer()
  implicit val logger                             = Logging(system, getClass)
  implicit val are                                = new ApiRequestExecutor() {}

  behavior of "Polling"

  it should "emit updates" in {
    val n            = 5
    implicit val are = new MockApiRequestExecutor(nUpdates = n)
    val poller       = new Polling
    val f            = Source.fromGraph(poller.pollGraph).take(n.toLong).runWith(Sink.seq)

    whenReady(f) { res ⇒
      res.size shouldBe 5
      res.foreach(u ⇒ u shouldBe an[Update])
    }
  }

  override protected def afterAll(): Unit = {
    system.terminate()
  }
}
