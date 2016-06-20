package com.github.nikdon.telepooz.model.methods

import com.github.nikdon.telepooz.raw.CirceEncoders
import com.github.nikdon.telepooz.tags
import io.circe.Json
import io.circe.syntax._
import org.scalacheck.Arbitrary.arbitrary
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}


class GetChatMembersCountTest extends FlatSpec
                                      with Matchers
                                      with GeneratorDrivenPropertyChecks
                                      with tags.Syntax
                                      with CirceEncoders {
  behavior of "GetChatMembersCount"

  it should "convert to json" in {
    forAll(GetChatMembersCountTest.getChatMembersCountGen) { getChatMembersCount ⇒
      getChatMembersCount.asJson should not be Json.Null
    }
  }
}

object GetChatMembersCountTest extends tags.Syntax {
  val getChatMembersCountGen = for {
    chatId ← arbitrary[String].map(_.chatId)
  } yield GetChatMembersCount(chatId)
}
