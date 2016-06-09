package com.github.nikdon.telepooz.model


import com.github.nikdon.telepooz.raw.{CirceDecoders, CirceEncoders}
import com.github.nikdon.telepooz.tags
import io.circe.syntax._
import org.scalacheck.Arbitrary._
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}


class UpdateTest extends FlatSpec
                         with Matchers
                         with GeneratorDrivenPropertyChecks
                         with tags.Syntax
                         with CirceEncoders
                         with CirceDecoders {
  behavior of "Update"

  import UpdateTest._

  it should "convert to a json and back to a model" in {
    forAll(updateGen) { update ⇒
      val json = update.asJson.noSpaces
      io.circe.parser.decode[Update](json) foreach (res ⇒ res shouldEqual update)
    }
  }
}

object UpdateTest extends tags.Syntax {
  val updateGen = for {
    updateId ← arbitrary[Long].map(_.updateId)
    message ← arbitrary[Option[Boolean]].map(_ ⇒ MessageTest.fullMessageGen.sample)
    editedMessage ← arbitrary[Option[Boolean]].map(_ ⇒ MessageTest.fullMessageGen.sample)
    // inlineQuery ← TODO
    // chosenInlineResult TODO
    callbackQuery ← arbitrary[Option[Boolean]].map(_ ⇒ CallbackQueryTest.callbackQueryGen.sample)
  } yield Update(updateId, message, editedMessage, None, None, callbackQuery)
}
