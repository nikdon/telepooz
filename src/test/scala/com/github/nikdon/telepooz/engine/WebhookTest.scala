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

import akka.http.scaladsl.Http
import akka.http.scaladsl.model.ContentTypes
import akka.http.scaladsl.testkit.ScalatestRouteTest
import akka.stream.scaladsl.{Sink, Source}
import com.github.nikdon.telepooz.helpers.Arbitraries._
import com.github.nikdon.telepooz.model.Update
import com.github.nikdon.telepooz.json.CirceEncoders
import io.circe.syntax._
import org.scalacheck.Arbitrary.arbitrary
import org.scalatest.concurrent.{ScalaFutures, ScaledTimeSpans}
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}

class WebhookTest
    extends FlatSpec
    with Matchers
    with GeneratorDrivenPropertyChecks
    with ScalatestRouteTest
    with ScalaFutures
    with ScaledTimeSpans {

  override def spanScaleFactor: Double = 500.0

  val encoders = new CirceEncoders {}
  import encoders._

  val are = new MockApiRequestExecutor()
  val whs = new Webhook("", "::0", 8080)(are, materializer).source

  behavior of "Webhooks"

  it should "handle incoming request with updates" in {
    val httpRequest =
      Post("http://0.0.0.0:8080/")
        .withEntity(ContentTypes.`application/json`, arbitrary[Update].sample.get.asJson.noSpaces)

    val t = whs.concat(Source.fromFuture(Http().singleRequest(httpRequest))).take(1).runWith(Sink.seq)

    whenReady(t) { updates ⇒
      updates.size shouldBe 1
      updates.foreach(u ⇒ u shouldBe an[Update])
    }
  }
}
