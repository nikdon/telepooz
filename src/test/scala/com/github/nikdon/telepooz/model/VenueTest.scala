package com.github.nikdon.telepooz.model


import com.github.nikdon.telepooz.raw.{CirceDecoders, CirceEncoders}
import com.github.nikdon.telepooz.tags
import io.circe.syntax._
import org.scalacheck.Arbitrary._
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers, OptionValues}


class VenueTest extends FlatSpec
                        with OptionValues
                        with Matchers
                        with GeneratorDrivenPropertyChecks
                        with tags.Syntax
                        with CirceEncoders
                        with CirceDecoders {
  behavior of "Venue"

  import VenueTest._

  it should "convert to a json and back to a model" in {
    forAll(venueGen) { venue ⇒
      val json = venue.asJson.noSpaces
      io.circe.parser.decode[Venue](json).toOption.value shouldEqual venue
    }
  }
}

object VenueTest extends tags.Syntax {
  val venueGen = for {
    location ← LocationTest.locationGen
    title ← arbitrary[String]
    address ← arbitrary[String]
    foursquareId ← arbitrary[Option[String]].map(_.map(_.foursquareId))
  } yield Venue(location, title, address, foursquareId)
}
