package com.github.nikdon.telepooz.model


import com.github.nikdon.telepooz.raw.{CirceDecoders, CirceEncoders}
import com.github.nikdon.telepooz.tags
import io.circe.syntax._
import org.scalacheck.Arbitrary._
import org.scalacheck.Gen
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}


class UserProfilePhotosTest extends FlatSpec
                                    with Matchers
                                    with GeneratorDrivenPropertyChecks
                                    with tags.Syntax
                                    with CirceEncoders
                                    with CirceDecoders {

  behavior of "UserProfilePhotos"

  import UserProfilePhotosTest._

  it should "convert to a json and back to a model" in {
    forAll(stickerGen) { sticker ⇒
      val json = sticker.asJson.noSpaces
      io.circe.parser.decode[UserProfilePhotos](json) foreach (res ⇒ res shouldEqual sticker)
    }
  }
}

object UserProfilePhotosTest extends tags.Syntax {
  val stickerGen = for {
    totalCount ← arbitrary[Int]
    photos ← Gen.nonEmptyListOf(Gen.nonEmptyListOf(PhotoSizeTest.photoSizeGen).map(_.toVector)).map(_.toVector)
  } yield UserProfilePhotos(totalCount, photos)
}
