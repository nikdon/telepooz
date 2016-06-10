package com.github.nikdon.telepooz.model


import com.github.nikdon.telepooz.raw.{CirceDecoders, CirceEncoders}
import com.github.nikdon.telepooz.tags
import io.circe.syntax._
import org.scalacheck.Gen
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}

class ChatMemberTest extends FlatSpec
                       with Matchers
                       with GeneratorDrivenPropertyChecks
                       with tags.Syntax
                       with CirceEncoders
                       with CirceDecoders {

  behavior of "ChatMember"
  import ChatMemberTest._

  it should "convert to a json and back to a model" in {
    forAll(chatMemberGen) { chatMember ⇒
      val json = chatMember.asJson.noSpaces
      io.circe.parser.decode[Chat](json) foreach (res ⇒ res shouldEqual chatMember)
    }
  }
}

object ChatMemberTest extends tags.Syntax {
  import com.github.nikdon.telepooz.model.MemberStatus.{Administrator, Creator, Kicked, Left, Member}

  val chatMemberGen = for {
    user ← UserTest.userGen
    status ← Gen.oneOf(Creator, Administrator, Member, Left, Kicked)
  } yield ChatMember(user, status)
}