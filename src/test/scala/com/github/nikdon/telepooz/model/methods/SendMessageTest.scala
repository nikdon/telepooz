package com.github.nikdon.telepooz.model.methods

import com.github.nikdon.telepooz.helpers.Arbitraries._
import com.github.nikdon.telepooz.raw.CirceEncoders
import io.circe.Json
import io.circe.syntax._
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}

class SendMessageTest extends FlatSpec
                              with Matchers
                              with GeneratorDrivenPropertyChecks
                              with CirceEncoders {

  "SendMessage" should "convert to json with chat id of type String" in {
    forAll { sendMessage: SendMessage[Long] ⇒
      sendMessage.asJson should not be Json.Null
    }
  }
}
