package com.github.nikdon.telepooz.model


import com.github.nikdon.telepooz.raw.{CirceDecoders, CirceEncoders}
import com.github.nikdon.telepooz.tags
import io.circe.syntax._
import org.scalacheck.Arbitrary._
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}


class StickerTest extends FlatSpec
                            with Matchers
                            with GeneratorDrivenPropertyChecks
                            with tags.Syntax
                            with CirceEncoders
                            with CirceDecoders {

  behavior of "Sticker"

  import StickerTest._

  it should "convert to a json and back to a model" in {
    forAll(stickerGen) { sticker ⇒
      val json = sticker.asJson.noSpaces
      io.circe.parser.decode[Sticker](json) foreach (res ⇒ res shouldEqual sticker)
    }
  }
}

object StickerTest extends tags.Syntax {
  val stickerGen = for {
    fileId ← arbitrary[String].map(_.fileId)
    width ← arbitrary[Int]
    height ← arbitrary[Int]
    thumb ← arbitrary[Option[Boolean]].map(_ ⇒ PhotoSizeTest.photoSizeGen.sample)
    emoji ← arbitrary[Option[String]]
    fileSize ← arbitrary[Option[Int]]
  } yield Sticker(fileId, width, height, thumb, emoji, fileSize)
}
