package com.github.nikdon.telepooz.model


import com.github.nikdon.telepooz.raw.{CirceDecoders, CirceEncoders}
import com.github.nikdon.telepooz.tags
import io.circe.syntax._
import org.scalacheck.Arbitrary._
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers, OptionValues}


class InlineKeyboardButtonTest extends FlatSpec
                                       with OptionValues
                                       with Matchers
                                       with GeneratorDrivenPropertyChecks
                                       with tags.Syntax
                                       with CirceEncoders
                                       with CirceDecoders {
  behavior of "InlineKeyboardButton"

  import InlineKeyboardButtonTest._

  it should "convert to a json and back to a model" in {
    forAll(inlineKeyboardButtonGen) { inlineKeyboardButton ⇒
      val json = inlineKeyboardButton.asJson.noSpaces
      io.circe.parser.decode[InlineKeyboardButton](json).toOption.value shouldEqual inlineKeyboardButton
    }
  }
}

object InlineKeyboardButtonTest extends tags.Syntax {
  val inlineKeyboardButtonGen = for {
    text ← arbitrary[String]
    url ← arbitrary[Option[String]]
    callbackData ← arbitrary[Option[String]]
    switchInlineQuery ← arbitrary[Option[String]]
  } yield InlineKeyboardButton(text, url, callbackData, switchInlineQuery)
}
