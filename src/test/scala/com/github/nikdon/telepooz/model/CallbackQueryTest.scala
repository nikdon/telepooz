package com.github.nikdon.telepooz.model


import com.github.nikdon.telepooz.raw.{CirceDecoders, CirceEncoders}
import com.github.nikdon.telepooz.tags
import io.circe.syntax._
import org.scalacheck.Arbitrary._
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}


class CallbackQueryTest extends FlatSpec
                                with Matchers
                                with GeneratorDrivenPropertyChecks
                                with tags.Syntax
                                with CirceEncoders
                                with CirceDecoders {
  behavior of "CallbackQuery"

  import CallbackQueryTest._

  it should "convert to a json and back to a model" in {
    forAll(callbackQueryGen) { callbackQuery ⇒
      val json = callbackQuery.asJson.noSpaces
      io.circe.parser.decode[CallbackQuery](json) foreach (res ⇒ res shouldEqual callbackQuery)
    }
  }
}

object CallbackQueryTest extends tags.Syntax {
  val callbackQueryGen = for {
    id ← arbitrary[String].map(_.queryId)
    from ← arbitrary[Option[Boolean]].map(_ ⇒ UserTest.userGen.sample)
    message ← arbitrary[Option[Boolean]].map(_ ⇒ MessageTest.fullMessageGen.sample)
    inlineMessageId ← arbitrary[Option[String]].map(_.map(_.queryId))
    data ← arbitrary[String]
  } yield CallbackQuery(id, from, message, inlineMessageId, data)
}
