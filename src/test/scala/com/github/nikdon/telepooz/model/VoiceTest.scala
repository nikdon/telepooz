package com.github.nikdon.telepooz.model

import java.time.Duration

import com.github.nikdon.telepooz.raw.{CirceDecoders, CirceEncoders}
import com.github.nikdon.telepooz.tags
import io.circe.syntax._
import org.scalacheck.Arbitrary._
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}


class VoiceTest extends FlatSpec
                        with Matchers
                        with GeneratorDrivenPropertyChecks
                        with tags.Syntax
                        with CirceEncoders
                        with CirceDecoders {
  behavior of "Voice"

  import VoiceTest._

  it should "convert to a json and back to a model" in {
    forAll(voiceGen) { voice ⇒
      val json = voice.asJson.noSpaces
      io.circe.parser.decode[Voice](json) foreach (res ⇒ res shouldEqual voice)
    }
  }
}

object VoiceTest extends tags.Syntax {
  val voiceGen = for {
    fileId ← arbitrary[String].map(_.fileId)
    duration ← arbitrary[Int].map(s ⇒ Duration.ofSeconds(s.toLong))
    mimeType ← arbitrary[String]
    fileSize ← arbitrary[Int]
  } yield Voice(fileId, duration, mimeType, fileSize)
}
