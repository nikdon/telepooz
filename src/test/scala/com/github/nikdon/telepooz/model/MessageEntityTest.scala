package com.github.nikdon.telepooz.model

import com.github.nikdon.telepooz.model.MessageEntityType._
import com.github.nikdon.telepooz.raw.{CirceDecoders, CirceEncoders}
import com.github.nikdon.telepooz.tags
import io.circe.syntax._
import org.scalacheck.Arbitrary._
import org.scalacheck.Gen
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}


class MessageEntityTest extends FlatSpec
                                with Matchers
                                with GeneratorDrivenPropertyChecks
                                with tags.Syntax
                                with CirceEncoders
                                with CirceDecoders {

  behavior of "MessageEntity"

  import MessageEntityTest._

  it should "convert to a json and back to a model" in {
    forAll(messageEntityGen) { messageEntity ⇒
      val json = messageEntity.asJson.noSpaces
      io.circe.parser.decode[MessageEntity](json) foreach (res ⇒ res shouldEqual messageEntity)
    }
  }

}

object MessageEntityTest {
  val messageEntityGen = for {
    t ← Gen.oneOf(Mention, Hashtag, BotCommand, Url, Email, Bold, Italic, Code, Pre, TextLink)
    offset ← arbitrary[Int]
    length ← arbitrary[Int]
    url ← arbitrary[Option[String]]
  } yield MessageEntity(t, offset, length, url)
}
