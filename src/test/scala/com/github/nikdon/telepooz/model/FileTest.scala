package com.github.nikdon.telepooz.model

import com.github.nikdon.telepooz.raw.{CirceDecoders, CirceEncoders}
import com.github.nikdon.telepooz.tags
import io.circe.syntax._
import org.scalacheck.Arbitrary._
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}


class FileTest extends FlatSpec
                        with Matchers
                        with GeneratorDrivenPropertyChecks
                        with tags.Syntax
                        with CirceEncoders
                        with CirceDecoders {

  behavior of "File"

  import FileTest._

  it should "convert to a json and back to a model" in {

    forAll(fileGen) { file ⇒
      val json = file.asJson.noSpaces
      io.circe.parser.decode[File](json) foreach (res ⇒ res shouldEqual file)
    }
  }

}

object FileTest extends tags.Syntax {
  val fileGen = for {
    fileId ← arbitrary[String].map(_.fileId)
    fileSize ← arbitrary[Option[Int]]
    filePath ← arbitrary[Option[String]]
  } yield File(fileId, fileSize, filePath)
}
