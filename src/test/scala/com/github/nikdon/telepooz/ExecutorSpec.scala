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

package com.github.nikdon.telepooz

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import cats.free.Free
import cats.implicits._
import com.github.nikdon.telepooz.api._
import com.github.nikdon.telepooz.engine.MockApiRequestExecutor
import com.github.nikdon.telepooz.model.methods.{ChatAction, Method}
import com.github.nikdon.telepooz.model.{Message, Response, methods}
import com.github.nikdon.telepooz.json.CirceEncoders
import com.github.nikdon.telepooz.model.payments.LabeledPrice
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
      a <- methods.GetMe
      c <- methods.AnswerCallbackQuery("query")
      d <- methods.ForwardMessage("123", "321", 0L)
      e <- methods.GetChat("123")
      f <- methods.GetChatAdministrators("123")
      g <- methods.GetChatMember("123", 321)
      h <- methods.GetChatMembersCount("123")
      i <- methods.GetFile("file")
      j <- methods.GetUpdates()
      k <- methods.GetUserProfilePhotos(123)
      l <- methods.KickChatMember("123", 321)
      m <- methods.LeaveChat("123")
      n <- methods.SendAudio("123", "auau")
      o <- methods.SendChatAction("123", ChatAction.FindLocation)
      p <- methods.SendContact("123", "123321", "John")
      q <- methods.SendDocument("123", "doc")
      r <- methods.SendLocation("123", 0.0, 0.0)
      s <- methods.SendPhoto("123", "photo")
      t <- methods.SendSticker("123", "sticker")
      u <- methods.SendVenue("123", 0.0, 0.0, "zero", "pole")
      v <- methods.SendVideo("123", "video")
      _ <- methods.SendVideoNote("123", "video")
      w <- methods.SendVoice("123", "voice")
      x <- methods.UnbanChatMember("123", 321)
      _ <- methods.DeleteMessage("123", 321)
      b <- methods.SendMessage("123", a.result.fold("empty")(_.first_name))
      _ <- methods.EditMessageReplyMarkup("123L", 333L, "bb")
      _ <- methods.EditMessageCaption("123L", 333L, "aa")
      _ <- methods.EditMessageText("123L", 333L, "cc", "TEST")
      _ <- methods.GetWebhookInfo
      _ <- methods.SetGameScore(123L, 100500, "game_chat_id")
      _ <- methods.GetGameHighScores(123L)

      _ <- methods.payments.AnswerPreCheckoutQuery("id", ok = true)
      _ <- methods.payments.AnswerShippingQuery("id", ok = true)
      _ <- methods.payments.SendInvoice(123,
                                        "title",
                                        "desc",
                                        "payload",
                                        "token",
                                        "start_param",
                                        "RUB",
                                        List(LabeledPrice("label", 123)))
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
      a ← methods.EditMessageReplyMarkup("123L", 333L, "bb")
      b ← methods.EditMessageCaption("123L", 333L, "aa")
      c ← methods.EditMessageText("123L", 333L, "cc", "TEST")
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
