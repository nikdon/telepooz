package com.github.nikdon.telepooz.model.methods

import com.github.nikdon.telepooz.raw.CirceEncoders
import com.github.nikdon.telepooz.tags
import io.circe.Json
import io.circe.syntax._
import org.scalacheck.Arbitrary.arbitrary
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}


class GetChatTest extends FlatSpec
                          with Matchers
                          with GeneratorDrivenPropertyChecks
                          with tags.Syntax
                          with CirceEncoders {
  behavior of "GetChat"

  it should "convert to json" in {
    forAll(GetChatTest.getChatGen) { getChat ⇒
      getChat.asJson should not be Json.Null
    }
  }
}

object GetChatTest extends tags.Syntax {
  val getChatGen = for {
    chatId ← arbitrary[String].map(_.chatId)
  } yield GetChat(chatId)
}
