package com.github.nikdon.telepooz.model.methods

import com.github.nikdon.telepooz.model.ParseMode
import com.github.nikdon.telepooz.raw.CirceEncoders
import com.github.nikdon.telepooz.tags
import io.circe.syntax._
import org.scalacheck.Arbitrary.arbitrary
import org.scalacheck.Gen
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}

class SendMessageTest extends FlatSpec
                              with Matchers
                              with GeneratorDrivenPropertyChecks
                              with tags.Syntax
                              with CirceEncoders {

  behavior of "SendMessage"

  import SendMessageTest._

  it should "convert to json with chat id of type String" in {
    forAll(sendMessageStringChatIdGen) { sendMessage ⇒
      val json = sendMessage.asJson
      // println("json = " + json)
    }
  }

  it should "convert to json with chat id of type Int" in {
    forAll(sendMessageIntChatIdGen) { sendMessage ⇒
      val json = sendMessage.asJson
      // println("json = " + json)
    }
  }

}

object SendMessageTest extends tags.Syntax {
  val sendMessageStringChatIdGen = for {
    id ← arbitrary[String].map(_.chatId)
    text ← arbitrary[String]
    parseMode ← arbitrary[Option[Boolean]].map(_ ⇒ Gen.oneOf(ParseMode.HTML, ParseMode.Markdown).sample)
    disableWebPagePreview ← arbitrary[Option[Boolean]]
    disableNotification ← arbitrary[Option[Boolean]]
    replyToMessageId ← arbitrary[Option[Int]].map(_.map(_.messageId))
    // replyMarkup TODO
  } yield SendMessage(id, text, parseMode, disableWebPagePreview, disableNotification, replyToMessageId, None)

  val sendMessageIntChatIdGen = for {
    id ← arbitrary[Long].map(_.chatId)
    text ← arbitrary[String]
    parseMode ← arbitrary[Option[Boolean]].map(_ ⇒ Gen.oneOf(ParseMode.HTML, ParseMode.Markdown).sample)
    disableWebPagePreview ← arbitrary[Option[Boolean]]
    disableNotification ← arbitrary[Option[Boolean]]
    replyToMessageId ← arbitrary[Option[Int]].map(_.map(_.messageId))
    // replyMarkup TODO
  } yield SendMessage(id, text, parseMode, disableWebPagePreview, disableNotification, replyToMessageId, None)
}
