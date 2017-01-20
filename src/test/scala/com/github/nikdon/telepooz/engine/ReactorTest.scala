package com.github.nikdon.telepooz.engine

import akka.Done
import akka.actor.ActorSystem
import akka.event.Logging
import akka.pattern.pipe
import akka.stream.scaladsl.Source
import akka.stream.{ActorMaterializer, Materializer}
import akka.testkit.TestProbe
import cats.instances.all._
import cats.syntax.all._
import com.github.nikdon.telepooz.helpers.Arbitraries._
import com.github.nikdon.telepooz.model._
import org.scalacheck.Arbitrary.arbitrary
import org.scalatest.concurrent.{ScalaFutures, ScaledTimeSpans}
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{BeforeAndAfterAll, FlatSpec, Matchers}

import scala.concurrent.duration._
import scala.concurrent.{ExecutionContextExecutor, Future}


class ReactorTest
    extends FlatSpec
    with Matchers
    with GeneratorDrivenPropertyChecks
    with ScalaFutures
    with BeforeAndAfterAll
    with ScaledTimeSpans {

  override def spanScaleFactor: Double = 10.0

  implicit val system: ActorSystem                = ActorSystem("ReactorTestSystem")
  implicit val executor: ExecutionContextExecutor = system.dispatcher
  implicit val materializer: Materializer         = ActorMaterializer()
  implicit val logger                             = Logging(system, getClass)
  implicit val are                                = new ApiRequestExecutor() {}

  behavior of "Reactor"

  it should "handle input Update" in {

    val reactor = new Reactor() {
      val reactions: CommandBasedReactions = CommandBasedReactions()
    }

    forAll { id: Long ⇒
      val future = Source.single(Update(id)).runWith(reactor.react)
      whenReady(future) { res ⇒
        res shouldBe Done
      }
    }
  }

  it should "react on the update with `/test` command" in {
    val probe = TestProbe()

    val triggeringReactor = new Reactor() {
      val reactions = CommandBasedReactions().on("/test")(implicit message ⇒ args ⇒ Future.successful("test") pipeTo probe.ref)
    }

    forAll { (update: Update, message: Message) ⇒
      val msg    = message.copy(text = Some("/test"))
      val upd    = update.copy(message = Some(msg))
      val future = Source.single(upd).runWith(triggeringReactor.react)

      whenReady(future) { res ⇒
        res shouldBe Done
        probe.expectMsg(100.millis, "test")
      }
    }
  }

  it should "react on different updates with commands" in {
    val probe = TestProbe()

    val triggeringReactor = new Reactor() {
      val reactions = CommandBasedReactions()
        .on("/test1")(implicit message ⇒ args ⇒ Future.successful("test-1") pipeTo probe.ref)
        .on("/test2")(implicit message ⇒ args ⇒ Future.successful("test-2") pipeTo probe.ref)
    }

    val upds: Option[List[Update]] = List(1, 2)
      .map(i ⇒ arbitrary[Message].sample.map(_.copy(text = Some(s"/test$i"))))
      .map(m ⇒ arbitrary[Update].sample.map(_.copy(message = m)))
      .sequence

    upds match {
      case Some(us) ⇒
        val f = Source(us).runWith(triggeringReactor.react)
        whenReady(f) { res ⇒
          res shouldBe Done
          probe.expectMsgAllOf("test-1", "test-2")
        }
      case None ⇒ throw new AssertionError("Source was not initialized")
    }
  }

  it should "not react on the update without proper command" in {
    val probe = TestProbe()

    val triggeringReactor = new Reactor() {
      val reactions = CommandBasedReactions().on("/test")(implicit message ⇒ args ⇒ Future.successful("test") pipeTo probe.ref)
    }

    val update = arbitrary[Update].sample.get
    val msg    = update.message.map(_.copy(text = Some("/nottest")))
    val upd    = update.copy(message = msg)
    val future = Source.single(upd).runWith(triggeringReactor.react)

    whenReady(future) { res ⇒
      res shouldBe Done
      probe.expectNoMsg(100.millis)
    }
  }

  override protected def afterAll(): Unit = {
    system.terminate()
  }
}
