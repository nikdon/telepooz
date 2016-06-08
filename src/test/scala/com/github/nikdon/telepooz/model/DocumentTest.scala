package com.github.nikdon.telepooz.model


import com.github.nikdon.telepooz.raw.{CirceDecoders, CirceEncoders}
import com.github.nikdon.telepooz.tags
import io.circe.syntax._
import org.scalacheck.Arbitrary._
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}


class DocumentTest extends FlatSpec
                           with Matchers
                           with GeneratorDrivenPropertyChecks
                           with tags.Syntax
                           with CirceEncoders
                           with CirceDecoders {
  behavior of "Document"

  import DocumentTest._

  it should "convert to a json and back to a model" in {
    forAll(documentGen) { document ⇒
      val json = document.asJson.noSpaces
      io.circe.parser.decode[Document](json) foreach (res ⇒ res shouldEqual document)
    }
  }
}

object DocumentTest extends tags.Syntax {
  val documentGen = for {
    fileId ← arbitrary[String].map(_.fileId)
    thumb ← arbitrary[Option[Boolean]].map(_ ⇒ PhotoSizeTest.photoSizeGen.sample)
    fileName ← arbitrary[Option[String]]
    mimeType ← arbitrary[Option[String]]
    fileSize ← arbitrary[Option[Int]]
  } yield Document(fileId, thumb, fileName, mimeType, fileSize)
}
