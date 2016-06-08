package com.github.nikdon.telepooz.model

import com.github.nikdon.telepooz.raw.{CirceDecoders, CirceEncoders}
import com.github.nikdon.telepooz.tags
import io.circe.syntax._
import org.scalacheck.Arbitrary.arbitrary
import org.scalacheck.Gen
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}

class ChatTest extends FlatSpec
                       with Matchers
                       with GeneratorDrivenPropertyChecks
                       with tags.Syntax
                       with CirceEncoders
                       with CirceDecoders {

  behavior of "Chat"
  import ChatTest._

  it should "convert to a json and back to a model" in {
    forAll(chatGen) { chat ⇒
      val json = chat.asJson.noSpaces
      io.circe.parser.decode[Chat](json) foreach (res ⇒ res shouldEqual chat)
    }
  }
}

object ChatTest extends tags.Syntax {
  val chatGen = for {
    id ← arbitrary[Long].map(_.chatId)
    t ← Gen.oneOf(ChatType.Group, ChatType.Channel, ChatType.Private, ChatType.SuperGroup)
    title ← arbitrary[Option[String]]
    userName ← arbitrary[Option[String]]
    firstName ← arbitrary[Option[String]]
    lastName ← arbitrary[Option[String]]
  } yield Chat(id, t, title, userName, firstName, lastName)
}
