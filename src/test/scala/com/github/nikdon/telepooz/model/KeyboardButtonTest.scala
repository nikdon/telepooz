package com.github.nikdon.telepooz.model

import com.github.nikdon.telepooz.raw.{CirceDecoders, CirceEncoders}
import com.github.nikdon.telepooz.tags
import io.circe.syntax._
import org.scalacheck.Arbitrary._
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers, OptionValues}



class KeyboardButtonTest extends FlatSpec
                                 with OptionValues
                                 with Matchers
                                 with GeneratorDrivenPropertyChecks
                                 with tags.Syntax
                                 with CirceEncoders
                                 with CirceDecoders {

  behavior of "KeyboardButton"

  import KeyboardButtonTest._

  it should "convert to a json and back to a model" in {
    forAll(keyboardButtonGen) { keyboardButton ⇒
      val json = keyboardButton.asJson.noSpaces
      io.circe.parser.decode[KeyboardButton](json).toOption.value shouldEqual keyboardButton
    }
  }
}

object KeyboardButtonTest {
  val keyboardButtonGen = for {
    text ← arbitrary[String]
    requestContact ← arbitrary[Option[Boolean]]
    requestLocation ← arbitrary[Option[Boolean]]
  } yield KeyboardButton(text, requestContact, requestLocation)
}
