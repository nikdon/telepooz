package com.github.nikdon.telepooz.model


import com.github.nikdon.telepooz.raw.{CirceDecoders, CirceEncoders}
import com.github.nikdon.telepooz.tags
import io.circe.syntax._
import org.scalacheck.Arbitrary._
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers, OptionValues}


class UserTest extends FlatSpec
                       with OptionValues
                       with Matchers
                       with GeneratorDrivenPropertyChecks
                       with tags.Syntax
                       with CirceEncoders
                       with CirceDecoders {
  behavior of "User"

  import UserTest._

  it should "convert to a json and back to a model" in {
    forAll(userGen) { user ⇒
      val json = user.asJson.noSpaces
      io.circe.parser.decode[User](json).toOption.value shouldEqual user
    }
  }
}

object UserTest extends tags.Syntax {
  val userGen = for {
    id ← arbitrary[Int].map(_.userId)
    firstName ← arbitrary[String]
    lastName ← arbitrary[Option[String]]
    username ← arbitrary[Option[String]]
  } yield User(id, firstName, lastName, username)
}
