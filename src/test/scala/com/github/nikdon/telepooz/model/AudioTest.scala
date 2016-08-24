package com.github.nikdon.telepooz.model


import com.github.nikdon.telepooz.helpers.Arbitraries._
import com.github.nikdon.telepooz.raw.{CirceDecoders, CirceEncoders}
import io.circe.syntax._
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers, OptionValues}


class AudioTest extends FlatSpec
                        with OptionValues
                        with Matchers
                        with GeneratorDrivenPropertyChecks
                        with CirceEncoders
                        with CirceDecoders {

  "Audio" should "convert to a json and back to a model" in {
    forAll { audio: Audio ⇒
      val json = audio.asJson.noSpaces
      io.circe.parser.decode[Audio](json).toOption.value shouldEqual audio
    }
  }

}
