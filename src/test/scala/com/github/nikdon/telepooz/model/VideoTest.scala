package com.github.nikdon.telepooz.model


import java.time.Duration

import com.github.nikdon.telepooz.raw.{CirceDecoders, CirceEncoders}
import com.github.nikdon.telepooz.tags
import io.circe.syntax._
import org.scalacheck.Arbitrary._
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}


class VideoTest extends FlatSpec
                        with Matchers
                        with GeneratorDrivenPropertyChecks
                        with tags.Syntax
                        with CirceEncoders
                        with CirceDecoders {

  behavior of "Video"

  import VideoTest._

  it should "convert to a json and back to a model" in {
    forAll(videoGen) { video ⇒
      val json = video.asJson.noSpaces
      io.circe.parser.decode[Video](json) foreach (res ⇒ res shouldEqual video)
    }
  }
}

object VideoTest extends tags.Syntax {
  val videoGen = for {
    fileId ← arbitrary[String].map(_.fileId)
    width ← arbitrary[Int]
    height ← arbitrary[Int]
    duration ← arbitrary[Int].map(s ⇒ Duration.ofSeconds(s.toLong))
    thumb ← arbitrary[Option[Boolean]].map(_ ⇒ PhotoSizeTest.photoSizeGen.sample)
    mimeType ← arbitrary[Option[String]]
    fileSize ← arbitrary[Option[Int]]
  } yield Video(fileId, width, height, duration, thumb, mimeType, fileSize)
}
