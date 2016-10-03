package com.github.nikdon.telepooz.model

import com.github.nikdon.telepooz.helpers.Arbitraries._
import com.github.nikdon.telepooz.raw.{CirceDecoders, CirceEncoders}
import io.circe.syntax._
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers, OptionValues}


class GameTest extends FlatSpec
                       with OptionValues
                       with Matchers
                       with GeneratorDrivenPropertyChecks
                       with CirceEncoders
                       with CirceDecoders {

  "Game" should "convert to a json and back to a model" in {
    forAll { game: Game ⇒
      val json = game.asJson.noSpaces
      io.circe.parser.decode[Game](json).toOption.value shouldEqual game
    }
  }
}