package com.github.nikdon.telepooz

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import cats.free.Free
import cats.implicits._
import com.github.nikdon.telepooz.ToRawRequest.syntax._
import com.github.nikdon.telepooz.engine.MockApiRequestExecutor
import com.github.nikdon.telepooz.model.methods.ChatAction
import com.github.nikdon.telepooz.model.{Message, Response, methods}
import com.github.nikdon.telepooz.raw.{CirceEncoders, RawRequest}
import com.github.nikdon.telepooz.tags.syntax._
import org.scalatest.concurrent.{Eventually, ScalaFutures}
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{BeforeAndAfterAll, FlatSpec, Matchers, OptionValues}

import scala.concurrent.duration._
import scala.language.postfixOps


class ExecutorSpec extends FlatSpec
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

  implicit val system = ActorSystem("ExecutorSpecActorSys")
  implicit val executor = system.dispatcher
  implicit val materializer = ActorMaterializer()
  val mockApiReqExe = new MockApiRequestExecutor

  it should "allow to compose requests" in {
    val req: Free[RawRequest, Response[Message]] = for {
      a ← api.execute(methods.GetMe.toRawRequest)
      c ← api.execute(methods.AnswerCallbackQuery("query".queryId).toRawRequest)
      d ← api.execute(methods.ForwardMessage("123".chatId, "321".chatId, 0L.messageId).toRawRequest)
      e ← api.execute(methods.GetChat("123".chatId).toRawRequest)
      f ← api.execute(methods.GetChatAdministrators("123".chatId).toRawRequest)
      g ← api.execute(methods.GetChatMember("123".chatId, 321.userId).toRawRequest)
      h ← api.execute(methods.GetChatMembersCount("123".chatId).toRawRequest)
      i ← api.execute(methods.GetFile("file".fileId).toRawRequest)
      j ← api.execute(methods.GetUpdates().toRawRequest)
      k ← api.execute(methods.GetUserProfilePhotos(123.userId).toRawRequest)
      l ← api.execute(methods.KickChatMember("123".chatId, 321.userId).toRawRequest)
      m ← api.execute(methods.LeaveChat("123".chatId).toRawRequest)
      n ← api.execute(methods.SendAudio("123".chatId, "auau".fileId).toRawRequest)
      o ← api.execute(methods.SendChatAction("123".chatId, ChatAction.FindLocation).toRawRequest)
      p ← api.execute(methods.SendContact("123".chatId, "123321", "John").toRawRequest)
      q ← api.execute(methods.SendDocument("123".chatId, "doc".fileId).toRawRequest)
      r ← api.execute(methods.SendLocation("123".chatId, 0.0, 0.0).toRawRequest)
      s ← api.execute(methods.SendPhoto("123".chatId, "photo".fileId).toRawRequest)
      t ← api.execute(methods.SendSticker("123".chatId, "sticker".fileId).toRawRequest)
      u ← api.execute(methods.SendVenue("123".chatId, 0.0, 0.0, "zero", "pole").toRawRequest)
      v ← api.execute(methods.SendVideo("123".chatId, "video".fileId).toRawRequest)
      w ← api.execute(methods.SendVoice("123".chatId, "voice".fileId).toRawRequest)
      x ← api.execute(methods.UnbanChatMember("123".chatId, 321.userId).toRawRequest)
      b ← api.execute(methods.SendMessage("123".chatId, a.result.fold("empty")(_.first_name)).toRawRequest)
      _ ← api.execute(methods.EditMessageReplyMarkup(123L.chatId, 333L.messageId, "bb".messageId).toRawRequest)
      _ ← api.execute(methods.EditMessageCaption(123L.chatId, 333L.messageId, "aa".messageId).toRawRequest)
      _ ← api.execute(methods.EditMessageText(123L.chatId, 333L.messageId, "cc".messageId, "TEST").toRawRequest)
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
      a ← api.execute(methods.EditMessageReplyMarkup(123L.chatId, 333L.messageId, "bb".messageId).toRawRequest)
      b ← api.execute(methods.EditMessageCaption(123L.chatId, 333L.messageId, "aa".messageId).toRawRequest)
      c ← api.execute(methods.EditMessageText(123L.chatId, 333L.messageId, "cc".messageId, "TEST").toRawRequest)
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


