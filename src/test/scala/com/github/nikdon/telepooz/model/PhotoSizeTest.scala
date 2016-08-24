package com.github.nikdon.telepooz.model

import com.github.nikdon.telepooz.helpers.Arbitraries._
import com.github.nikdon.telepooz.raw.{CirceDecoders, CirceEncoders}
import io.circe.syntax._
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers, OptionValues}


class PhotoSizeTest extends FlatSpec
                            with OptionValues
                            with Matchers
                            with GeneratorDrivenPropertyChecks
                            with CirceEncoders
                            with CirceDecoders {

  "PhotoSize" should "convert to a json and back to a model" in {
    forAll { photoSize: PhotoSize ⇒
      val json = photoSize.asJson.noSpaces
      io.circe.parser.decode[PhotoSize](json).toOption.value shouldEqual photoSize
    }
  }
}
