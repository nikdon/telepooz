package com.github.nikdon.telepooz

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import cats.free.Free
import cats.implicits._
import com.github.nikdon.telepooz.engine.MockApiRequestExecutor
import com.github.nikdon.telepooz.model.methods.{ChatAction, Method}
import com.github.nikdon.telepooz.model.{Message, Response, methods}
import com.github.nikdon.telepooz.raw.CirceEncoders
import org.scalatest.concurrent.{Eventually, ScalaFutures}
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{BeforeAndAfterAll, FlatSpec, Matchers, OptionValues}

import scala.concurrent.duration._
import scala.language.postfixOps

class ExecutorSpec
    extends FlatSpec
    with ScalaFutures
    with OptionValues
    with Eventually
    with Matchers
    with GeneratorDrivenPropertyChecks
    with CirceEncoders
    with BeforeAndAfterAll {

  behavior of "Executor"

  override protected def afterAll(): Unit = {
    system.registerOnTermination(println("Actor System terminated"))
    system.whenTerminated
  }

  implicit val system       = ActorSystem("ExecutorSpecActorSys")
  implicit val executor     = system.dispatcher
  implicit val materializer = ActorMaterializer()
  val mockApiReqExe         = new MockApiRequestExecutor

  it should "allow to compose requests" in {
    val req: Free[Method, Response[Message]] = for {
      a ← api.execute(methods.GetMe)
      c ← api.execute(methods.AnswerCallbackQuery("query"))
      d ← api.execute(methods.ForwardMessage("123", "321", 0L))
      e ← api.execute(methods.GetChat("123"))
      f ← api.execute(methods.GetChatAdministrators("123"))
      g ← api.execute(methods.GetChatMember("123", 321))
      h ← api.execute(methods.GetChatMembersCount("123"))
      i ← api.execute(methods.GetFile("file"))
      j ← api.execute(methods.GetUpdates())
      k ← api.execute(methods.GetUserProfilePhotos(123))
      l ← api.execute(methods.KickChatMember("123", 321))
      m ← api.execute(methods.LeaveChat("123"))
      n ← api.execute(methods.SendAudio("123", "auau"))
      o ← api.execute(methods.SendChatAction("123", ChatAction.FindLocation))
      p ← api.execute(methods.SendContact("123", "123321", "John"))
      q ← api.execute(methods.SendDocument("123", "doc"))
      r ← api.execute(methods.SendLocation("123", 0.0, 0.0))
      s ← api.execute(methods.SendPhoto("123", "photo"))
      t ← api.execute(methods.SendSticker("123", "sticker"))
      u ← api.execute(methods.SendVenue("123", 0.0, 0.0, "zero", "pole"))
      v ← api.execute(methods.SendVideo("123", "video"))
      w ← api.execute(methods.SendVoice("123", "voice"))
      x ← api.execute(methods.UnbanChatMember("123", 321))
      b ← api.execute(methods.SendMessage("123", a.result.fold("empty")(_.first_name)))
      _ ← api.execute(methods.EditMessageReplyMarkup("123L", 333L, "bb"))
      _ ← api.execute(methods.EditMessageCaption("123L", 333L, "aa"))
      _ ← api.execute(methods.EditMessageText("123L", 333L, "cc", "TEST"))
    } yield b

    val res = req.foldMap(mockApiReqExe)

    eventually(timeout(10 seconds), interval(500 millis)) {
      whenReady(res) { m ⇒
        m shouldBe a[Response[_]]
        m.ok shouldEqual true
        m.result shouldBe defined
        m.result.value shouldBe a[model.Message]
      }
    }
  }

  it should "allow to update messages" in {
    val req = for {
      a ← api.execute(methods.EditMessageReplyMarkup("123L", 333L, "bb"))
      b ← api.execute(methods.EditMessageCaption("123L", 333L, "aa"))
      c ← api.execute(methods.EditMessageText("123L", 333L, "cc", "TEST"))
    } yield c

    val res = req.foldMap(mockApiReqExe)

    eventually(timeout(10 seconds), interval(500 millis)) {
      whenReady(res) { m ⇒
        m shouldBe a[Response[_]]
        m.ok shouldEqual true
        m.result shouldBe defined
        m.result.value shouldBe a[Either[_, _]]
        m.result.value.isRight shouldBe true
      }
    }
  }
}
