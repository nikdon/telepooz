package com.github.nikdon.telepooz.model

import com.github.nikdon.telepooz.raw.{CirceDecoders, CirceEncoders}
import com.github.nikdon.telepooz.tags
import io.circe.syntax._
import org.scalacheck.Arbitrary._
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}


class LocationTest extends FlatSpec
                           with Matchers
                           with GeneratorDrivenPropertyChecks
                           with tags.Syntax
                           with CirceEncoders
                           with CirceDecoders {
  behavior of "Location"

  import LocationTest._

  it should "convert to a json and back to a model" in {
    forAll(locationGen) { location ⇒
      val json = location.asJson.noSpaces
      io.circe.parser.decode[Location](json) foreach (res ⇒ res shouldEqual location)
    }
  }
}

object LocationTest extends tags.Syntax {
  val locationGen = for {
    longitude ← arbitrary[Double]
    latitude ← arbitrary[Double]
  } yield Location(longitude, latitude)
}
