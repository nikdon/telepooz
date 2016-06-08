package com.github.nikdon.telepooz.model

import com.github.nikdon.telepooz.raw.{CirceDecoders, CirceEncoders}
import com.github.nikdon.telepooz.tags
import io.circe.syntax._
import org.scalacheck.Arbitrary.arbitrary
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}


class ContactTest extends FlatSpec
                          with Matchers
                          with GeneratorDrivenPropertyChecks
                          with tags.Syntax
                          with CirceEncoders
                          with CirceDecoders {

  behavior of "Contact"

  import ContactTest._

  it should "convert to a json and back to a model" in {
    forAll(contactGen) { contact ⇒
      val json = contact.asJson.noSpaces
      io.circe.parser.decode[Contact](json) foreach (res ⇒ res shouldEqual contact)
    }
  }

}

object ContactTest extends tags.Syntax {
  val contactGen = for {
    phoneNumber ← arbitrary[String]
    firstName ← arbitrary[String]
    lastName ← arbitrary[Option[String]]
    userId ← arbitrary[Option[Int]]
  } yield Contact(phoneNumber, firstName, lastName, userId.map(_.userId))
}
