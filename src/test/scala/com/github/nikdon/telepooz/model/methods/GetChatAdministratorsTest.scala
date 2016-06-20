package com.github.nikdon.telepooz.model.methods

import com.github.nikdon.telepooz.raw.CirceEncoders
import com.github.nikdon.telepooz.tags
import io.circe.Json
import io.circe.syntax._
import org.scalacheck.Arbitrary.arbitrary
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}


class GetChatAdministratorsTest extends FlatSpec
                                        with Matchers
                                        with GeneratorDrivenPropertyChecks
                                        with tags.Syntax
                                        with CirceEncoders {
  behavior of "GetChatAdministrators"

  it should "convert to json" in {
    forAll(GetChatAdministratorsTest.getChatAdministratorsGen) { getChatAdministrators ⇒
      getChatAdministrators.asJson should not be Json.Null
    }
  }
}

object GetChatAdministratorsTest extends tags.Syntax {
  val getChatAdministratorsGen = for {
    chatId ← arbitrary[String].map(_.chatId)
  } yield GetChatAdministrators(chatId)
}
