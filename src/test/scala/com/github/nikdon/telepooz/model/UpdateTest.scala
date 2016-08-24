package com.github.nikdon.telepooz.model


import com.github.nikdon.telepooz.helpers.Arbitraries._
import com.github.nikdon.telepooz.raw.{CirceDecoders, CirceEncoders}
import io.circe.syntax._
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers, OptionValues}


class UpdateTest extends FlatSpec
                         with OptionValues
                         with Matchers
                         with GeneratorDrivenPropertyChecks
                         with CirceEncoders
                         with CirceDecoders {

  "Update" should "convert to a json and back to a model" in {
    forAll { update: Update ⇒
      val json = update.asJson.noSpaces
      io.circe.parser.decode[Update](json).toOption.value shouldEqual update
    }
  }
}
