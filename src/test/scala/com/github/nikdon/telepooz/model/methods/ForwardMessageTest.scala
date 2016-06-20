package com.github.nikdon.telepooz.model.methods

import com.github.nikdon.telepooz.raw.CirceEncoders
import com.github.nikdon.telepooz.tags
import io.circe.Json
import io.circe.syntax._
import org.scalacheck.Arbitrary.arbitrary
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}


class ForwardMessageTest extends FlatSpec
                                 with Matchers
                                 with GeneratorDrivenPropertyChecks
                                 with tags.Syntax
                                 with CirceEncoders {
  behavior of "ForwardMessage"

  it should "convert to json" in {
    forAll(ForwardMessageTest.forwardMessageGen) { forwardMessage ⇒
      forwardMessage.asJson should not be Json.Null
    }
  }
}

object ForwardMessageTest extends tags.Syntax {
  val forwardMessageGen = for {
    chatId ← arbitrary[String].map(_.chatId)
    fromChatId ← arbitrary[String].map(_.chatId)
    messageId ← arbitrary[Long].map(_.messageId)
    disableNotification ← arbitrary[Option[Boolean]]
  } yield ForwardMessage(chatId, fromChatId, messageId, disableNotification)
}
