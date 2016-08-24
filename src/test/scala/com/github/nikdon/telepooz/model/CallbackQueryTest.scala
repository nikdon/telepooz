package com.github.nikdon.telepooz.model


import com.github.nikdon.telepooz.helpers.Arbitraries._
import com.github.nikdon.telepooz.raw.{CirceDecoders, CirceEncoders}
import io.circe.syntax._
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers, OptionValues}


class CallbackQueryTest extends FlatSpec
                                with OptionValues
                                with Matchers
                                with GeneratorDrivenPropertyChecks
                                with CirceEncoders
                                with CirceDecoders {

  "CallbackQuery" should "convert to a json and back to a model" in {
    forAll { callbackQuery: CallbackQuery ⇒
      val json = callbackQuery.asJson.noSpaces
      io.circe.parser.decode[CallbackQuery](json).toOption.value shouldEqual callbackQuery
    }
  }
}
