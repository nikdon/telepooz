package com.github.nikdon.telepooz.model


import com.github.nikdon.telepooz.raw.{CirceDecoders, CirceEncoders}
import com.github.nikdon.telepooz.tags
import io.circe.syntax._
import org.scalacheck.Gen
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}


class InlineKeyboardMarkupTest extends FlatSpec
                                       with Matchers
                                       with GeneratorDrivenPropertyChecks
                                       with tags.Syntax
                                       with CirceEncoders
                                       with CirceDecoders {
  behavior of "InlineKeyboardMarkup"

  import InlineKeyboardMarkupTest._

  it should "convert to a json and back to a model" in {
    forAll(inlineKeyboardMarkupGen) { inlineKeyboardMarkup ⇒
      val json = inlineKeyboardMarkup.asJson.noSpaces
      io.circe.parser.decode[ForceReply](json) foreach (res ⇒ res shouldEqual inlineKeyboardMarkup)
    }
  }
}

object InlineKeyboardMarkupTest extends tags.Syntax {
  val inlineKeyboardMarkupGen = for {
    keyboard ← Gen.nonEmptyListOf(Gen.nonEmptyListOf(InlineKeyboardButtonTest.inlineKeyboardButtonGen).map(_.toVector)).map(_.toVector)
  } yield InlineKeyboardMarkup(keyboard)
}
