package com.github.nikdon.telepooz.model

import java.time.Duration

import com.github.nikdon.telepooz.raw.{CirceDecoders, CirceEncoders}
import com.github.nikdon.telepooz.tags
import io.circe.syntax._
import org.scalacheck.Arbitrary.arbitrary
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}


class AudioTest extends FlatSpec
                        with Matchers
                        with GeneratorDrivenPropertyChecks
                        with tags.Syntax
                        with CirceEncoders
                        with CirceDecoders {

  behavior of "Audio"

  import AudioTest._

  it should "convert to a json and back to a model" in {
    forAll(auidoGen) { audio ⇒
      val json = audio.asJson.noSpaces
      io.circe.parser.decode[Audio](json) foreach (res ⇒ res shouldEqual audio)
    }
  }

}

object AudioTest extends tags.Syntax {
  val auidoGen = for {
    fileId ← arbitrary[String].map(_.fileId)
    duration ← arbitrary[Int].map(s ⇒ Duration.ofSeconds(s.toLong))
    performer ← arbitrary[Option[String]]
    title ← arbitrary[Option[String]]
    mimeType ← arbitrary[Option[String]]
    fileSize ← arbitrary[Option[Int]]
  } yield Audio(fileId, duration, performer, title, mimeType, fileSize)
}
