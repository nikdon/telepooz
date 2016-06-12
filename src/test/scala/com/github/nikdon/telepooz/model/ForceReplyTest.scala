package com.github.nikdon.telepooz.model


import com.github.nikdon.telepooz.raw.{CirceDecoders, CirceEncoders}
import com.github.nikdon.telepooz.tags
import io.circe.syntax._
import org.scalacheck.Arbitrary._
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers, OptionValues}


class ForceReplyTest extends FlatSpec
                             with OptionValues
                             with Matchers
                             with GeneratorDrivenPropertyChecks
                             with tags.Syntax
                             with CirceEncoders
                             with CirceDecoders {

  behavior of "ForceReply"

  import ForceReplyTest._

  it should "convert to a json and back to a model" in {
    forAll(forceReplyGen) { forceReply ⇒
      val json = forceReply.asJson.noSpaces
      io.circe.parser.decode[ForceReply](json).toOption.value shouldEqual forceReply
    }
  }
}

object ForceReplyTest extends tags.Syntax {
  val forceReplyGen = for {
    forceReply ← arbitrary[Boolean]
    selective ← arbitrary[Option[Boolean]]
  } yield ForceReply(forceReply, selective)
}
