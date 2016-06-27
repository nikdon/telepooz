package com.github.nikdon.telepooz.engine

import akka.Done
import akka.actor.ActorSystem
import akka.event.Logging
import akka.stream.scaladsl.Source
import akka.stream.{ActorMaterializer, Materializer}
import com.github.nikdon.telepooz.model.Update
import com.github.nikdon.telepooz.tags
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}

import scala.concurrent.ExecutionContextExecutor


class ReactorTest extends FlatSpec
                          with Matchers
                          with GeneratorDrivenPropertyChecks
                          with tags.Syntax
                          with ScalaFutures {

  implicit val system: ActorSystem = ActorSystem("ReactorTestSystem")
  implicit val executor: ExecutionContextExecutor = system.dispatcher
  implicit val materializer: Materializer = ActorMaterializer()
  implicit val logger = Logging(system, getClass)
  implicit val are = new ApiRequestExecutor() {}

  val reactor = new Reactor() {
    /** Override as lazy val */
    override lazy val reactions: Map[String, Reaction] = Map()
  }


  behavior of "Reactor"

  it should "handle input Updates" in {
    forAll { id: Long ⇒
      val future = Source.single(Update(id.updateId)).runWith(reactor.react)
      whenReady(future) {
        res ⇒ res shouldBe Done
      }
    }
  }
}
