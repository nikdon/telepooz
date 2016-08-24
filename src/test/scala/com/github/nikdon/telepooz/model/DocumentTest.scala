package com.github.nikdon.telepooz.model

import com.github.nikdon.telepooz.helpers.Arbitraries._
import com.github.nikdon.telepooz.raw.{CirceDecoders, CirceEncoders}
import io.circe.syntax._
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers, OptionValues}


class DocumentTest extends FlatSpec
                           with OptionValues
                           with Matchers
                           with GeneratorDrivenPropertyChecks
                           with CirceEncoders
                           with CirceDecoders {

  "Document" should "convert to a json and back to a model" in {
    forAll { document: Document ⇒
      val json = document.asJson.noSpaces
      io.circe.parser.decode[Document](json).toOption.value shouldEqual document
    }
  }
}
