package com.github.nikdon.telepooz.model.methods

import com.github.nikdon.telepooz.raw.{CirceDecoders, CirceEncoders}
import com.github.nikdon.telepooz.tags
import io.circe.Json
import io.circe.syntax._
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}


class GetMeTest extends FlatSpec
                         with Matchers
                         with GeneratorDrivenPropertyChecks
                         with tags.Syntax
                         with CirceEncoders
                         with CirceDecoders {

  behavior of "GetMe"

  it should "convert to a Null json" in {
    GetMe.asJson shouldEqual Json.Null
  }

}
