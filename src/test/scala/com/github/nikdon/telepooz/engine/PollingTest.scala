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
