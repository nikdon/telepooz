package com.github.nikdon.telepooz.model


import com.github.nikdon.telepooz.raw.{CirceDecoders, CirceEncoders}
import com.github.nikdon.telepooz.tags
import io.circe.syntax._
import org.scalacheck.Arbitrary._
import org.scalacheck.Gen
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}


class ReplyKeyboardMarkupTest extends FlatSpec
                                      with Matchers
                                      with GeneratorDrivenPropertyChecks
                                      with tags.Syntax
                                      with CirceEncoders
                                      with CirceDecoders {
  behavior of "ReplyKeyboardMarkup"

  import ReplyKeyboardMarkupTest._

  it should "convert to a json and back to a model" in {
    forAll(replyKeyboardMarkupGen) { replyKeyboardMarkup ⇒
      val json = replyKeyboardMarkup.asJson.noSpaces
      io.circe.parser.decode[ReplyKeyboardMarkup](json) foreach (res ⇒ res shouldEqual replyKeyboardMarkup)
    }
  }
}

object ReplyKeyboardMarkupTest extends tags.Syntax {
  val replyKeyboardMarkupGen = for {
    keyboard ← Gen.nonEmptyListOf(Gen.nonEmptyListOf(KeyboardButtonTest.keyboardButtonGen).map(_.toVector)).map(_.toVector)
    resizeKeyboard ← arbitrary[Option[Boolean]]
    oneTimeKeyboard ← arbitrary[Option[Boolean]]
    selective ← arbitrary[Option[Boolean]]
  } yield ReplyKeyboardMarkup(keyboard, resizeKeyboard, oneTimeKeyboard, selective)
}
