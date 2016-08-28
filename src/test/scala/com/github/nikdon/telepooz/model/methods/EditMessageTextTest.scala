package com.github.nikdon.telepooz.model.methods

import com.github.nikdon.telepooz.helpers.Arbitraries._
import com.github.nikdon.telepooz.raw.CirceEncoders
import io.circe.Json
import io.circe.syntax._
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}


class EditMessageTextTest extends FlatSpec
                                  with Matchers
                                  with GeneratorDrivenPropertyChecks
                                  with CirceEncoders {
  "EditMessageTextTest" should "convert to json" in {
    forAll { editMessageTextTest: EditMessageText[Long] ⇒
      editMessageTextTest.asJson should not be Json.Null
    }
  }
}
