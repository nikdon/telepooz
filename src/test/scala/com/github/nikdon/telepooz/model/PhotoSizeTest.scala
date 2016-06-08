package com.github.nikdon.telepooz.model

import com.github.nikdon.telepooz.raw.{CirceDecoders, CirceEncoders}
import com.github.nikdon.telepooz.tags
import io.circe.syntax._
import org.scalacheck.Arbitrary._
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}


class PhotoSizeTest extends FlatSpec
                            with Matchers
                            with GeneratorDrivenPropertyChecks
                            with tags.Syntax
                            with CirceEncoders
                            with CirceDecoders {

  behavior of "PhotoSize"

  import PhotoSizeTest._

  it should "convert to a json and back to a model" in {
    forAll(photoSizeGen) { photoSize ⇒
      val json = photoSize.asJson.noSpaces
      io.circe.parser.decode[PhotoSize](json) foreach (res ⇒ res shouldEqual photoSize)
    }
  }
}

object PhotoSizeTest extends tags.Syntax {
  val photoSizeGen = for {
    fileId ← arbitrary[String].map(_.fileId)
    width ← arbitrary[Int]
    height ← arbitrary[Int]
    fileSize ← arbitrary[Option[Int]]
  } yield PhotoSize(fileId, width, height, fileSize)
}
