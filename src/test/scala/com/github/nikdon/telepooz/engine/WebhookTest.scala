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

  override def spanScaleFactor: Double = 50.0

  val encoders = new CirceEncoders {}
  import encoders._

  val are = new MockApiRequestExecutor()
  val whs = new Webhook("http://127.0.0.1:8080", "::0", 8080)(are, materializer).source

  behavior of "Webhooks"

  it should "handle incoming request with updates" in {
    val httpRequest =
      Post("http://127.0.0.1:8080")
        .withEntity(ContentTypes.`application/json`, arbitrary[Update].sample.get.asJson.noSpaces)

    val t = for {
      upds ← whs.concat(Source.fromFuture(Http().singleRequest(httpRequest))).take(1).runWith(Sink.seq)
    } yield upds

    whenReady(t) { updates ⇒
      updates.size shouldBe 1
      updates.foreach(u ⇒ u shouldBe an[Update])
    }
  }
}
