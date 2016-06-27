package com.github.nikdon.telepooz.engine

import akka.Done
import akka.actor.ActorSystem
import akka.event.Logging
import akka.pattern.pipe
import akka.stream.scaladsl.Source
import akka.stream.{ActorMaterializer, Materializer}
import akka.testkit.TestProbe
import com.github.nikdon.telepooz.model._
import com.github.nikdon.telepooz.tags
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{BeforeAndAfterAll, FlatSpec, Matchers}

import scala.concurrent.duration._
import scala.concurrent.{ExecutionContextExecutor, Future}


class ReactorTest extends FlatSpec
                          with Matchers
                          with GeneratorDrivenPropertyChecks
                          with tags.Syntax
                          with ScalaFutures
                          with BeforeAndAfterAll {

  implicit val system: ActorSystem = ActorSystem("ReactorTestSystem")
  implicit val executor: ExecutionContextExecutor = system.dispatcher
  implicit val materializer: Materializer = ActorMaterializer()
  implicit val logger = Logging(system, getClass)
  implicit val are = new ApiRequestExecutor() {}


  behavior of "Reactor"

  it should "handle input Update" in {

    val reactor = new Reactor() {
      /** Override as lazy val */
      override lazy val reactions: Map[String, Reaction] = Map()
    }

    forAll { id: Long ⇒
      val future = Source.single(Update(id.updateId)).runWith(reactor.react)
      whenReady(future) {
        res ⇒ res shouldBe Done
      }
    }
  }

  it should "react on the update with `/test` command" in {
    val probe = TestProbe()

    val triggeringReactor = new Reactor() {
      /** Override as lazy val */
      override lazy val reactions: Map[String, Reaction] = Map(
        "/test" → (implicit message ⇒ args ⇒ {
          Future.successful("test") pipeTo probe.ref
        })
      )
    }

    forAll(UpdateTest.updateGen) { update ⇒
      val msg = update.message.map(_.copy(text = Some("/test")))
      val upd = update.copy(message = msg)
      val future = Source.single(upd).runWith(triggeringReactor.react)

      whenReady(future) { res ⇒
        res shouldBe Done
        probe.expectMsg(100.millis, "test")
      }
    }
  }

  it should "not react on the update without proper command" in {
    val probe = TestProbe()

    val triggeringReactor = new Reactor() {
      /** Override as lazy val */
      override lazy val reactions: Map[String, Reaction] = Map(
        "/test" → (implicit message ⇒ args ⇒ {
          Future.successful("test") pipeTo probe.ref
        })
      )
    }

    val update = UpdateTest.updateGen.sample.get
    val msg = update.message.map(_.copy(text = Some("/nottest")))
    val upd = update.copy(message = msg)
    val future = Source.single(upd).runWith(triggeringReactor.react)

    whenReady(future) { res ⇒
      res shouldBe Done
      probe.expectNoMsg(100.millis)
    }
  }

  override protected def afterAll(): Unit = {
    system.terminate().futureValue
  }
}
