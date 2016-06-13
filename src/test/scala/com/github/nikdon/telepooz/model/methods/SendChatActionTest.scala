package com.github.nikdon.telepooz.model.methods

import com.github.nikdon.telepooz.model.methods.ChatAction._
import com.github.nikdon.telepooz.raw.CirceEncoders
import com.github.nikdon.telepooz.tags
import io.circe.Json
import io.circe.syntax._
import org.scalacheck.Arbitrary.arbitrary
import org.scalacheck.Gen
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}


class SendChatActionTest extends FlatSpec
                                 with Matchers
                                 with GeneratorDrivenPropertyChecks
                                 with tags.Syntax
                                 with CirceEncoders {
  behavior of "SendChatAction"

  it should "convert to json" in {
    forAll(SendChatActionTest.sendChatActionGen) { sendChatAction ⇒
      sendChatAction.asJson should not be Json.Null
    }
  }
}

object SendChatActionTest extends tags.Syntax {
  val sendChatActionGen = for {
    chatId ← arbitrary[String].map(_.chatId)
    action ← Gen.oneOf(Typing, UploadPhoto, RecordVideo, UploadVideo, RecordAudio, UploadAudio, UploadDocument, FindLocation)
  } yield SendChatAction(chatId, action)
}
