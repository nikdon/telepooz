package com.github.nikdon.telepooz.model


import com.github.nikdon.telepooz.raw.{CirceDecoders, CirceEncoders}
import com.github.nikdon.telepooz.tags
import io.circe.syntax._
import org.scalacheck.Arbitrary._
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}


class ReplyKeyboardHideTest extends FlatSpec
                                    with Matchers
                                    with GeneratorDrivenPropertyChecks
                                    with tags.Syntax
                                    with CirceEncoders
                                    with CirceDecoders {
  behavior of "ReplyKeyboardHide"

  import ReplyKeyboardHideTest._

  it should "convert to a json and back to a model" in {
    forAll(replyKeyboardHideGen) { replyKeyboardHide ⇒
      val json = replyKeyboardHide.asJson.noSpaces
      io.circe.parser.decode[ReplyKeyboardMarkup](json) foreach (res ⇒ res shouldEqual replyKeyboardHide)
    }
  }
}

object ReplyKeyboardHideTest extends tags.Syntax {
  val replyKeyboardHideGen = for {
    hideKeyboard ← arbitrary[Boolean]
    selective ← arbitrary[Option[Boolean]]
  } yield ReplyKeyboardHide(hideKeyboard, selective)
}

