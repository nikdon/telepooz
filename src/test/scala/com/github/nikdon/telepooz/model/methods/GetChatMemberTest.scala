package com.github.nikdon.telepooz.model.methods

import com.github.nikdon.telepooz.raw.CirceEncoders
import com.github.nikdon.telepooz.tags
import io.circe.Json
import io.circe.syntax._
import org.scalacheck.Arbitrary.arbitrary
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}


class GetChatMemberTest extends FlatSpec
                                with Matchers
                                with GeneratorDrivenPropertyChecks
                                with tags.Syntax
                                with CirceEncoders {
  behavior of "GetChatMember"

  it should "convert to json" in {
    forAll(GetChatMemberTest.getChatMemberGen) { getChatMember ⇒
      getChatMember.asJson should not be Json.Null
    }
  }
}

object GetChatMemberTest extends tags.Syntax {
  val getChatMemberGen = for {
    chatId ← arbitrary[String].map(_.chatId)
    userId ← arbitrary[Int].map(_.userId)
  } yield GetChatMember(chatId, userId)
}
