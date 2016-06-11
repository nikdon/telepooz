package com.github.nikdon.telepooz

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import com.github.nikdon.telepooz.ToRawRequest.syntax._
import com.github.nikdon.telepooz.engine.MockApiRequestExecutor
import com.github.nikdon.telepooz.raw.CirceEncoders
import com.github.nikdon.telepooz.tags.syntax._
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers, OptionValues}
import cats.implicits._
import com.github.nikdon.telepooz.model.Response
import org.scalatest.concurrent.ScalaFutures


class ExecutorSpec extends FlatSpec
                           with ScalaFutures
                           with OptionValues
                           with Matchers
                           with GeneratorDrivenPropertyChecks
                           with CirceEncoders {
  behavior of "Executor"

  implicit val system = ActorSystem("ExecutorSpecActorSys")
  implicit val executor = system.dispatcher
  implicit val materializer = ActorMaterializer()
  val mockApiReqExe = new MockApiRequestExecutor

  it should "allow to compose a requests" in {
    val req = for {
      a ← api.execute(model.methods.GetMe.toRawRequest)
      b ← api.execute(model.methods.SendMessage(123.chatId, a.result.fold("empty")(_.first_name)).toRawRequest)
    } yield b

    val res = req.foldMap(mockApiReqExe)

    whenReady(res){ m ⇒
      m shouldBe a [Response[_]]
      m.ok shouldEqual true
      m.result shouldBe defined
      m.result.value shouldBe a [model.Message]
    }
  }
}


