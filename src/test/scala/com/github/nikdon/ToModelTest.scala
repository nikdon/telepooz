package com.github.nikdon

import org.scalacheck.Arbitrary.arbitrary
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}
import tags.syntax._
import ToModel.syntax._
import com.github.nikdon.model.ChatType
import org.scalacheck.Gen
//import org.scalacheck.Shapeless._


class ToModelTest extends FlatSpec
                          with Matchers
                          with GeneratorDrivenPropertyChecks {

  behavior of "User ToModel"

  val userDTOGen = for {
    id ← arbitrary[Int]
    firstName ← arbitrary[String]
    lastName ← arbitrary[Option[String]]
    userName ←arbitrary[Option[String]]
  } yield dto.User(id, firstName, lastName, userName)

  it should "produce a model" in {
    forAll(userDTOGen) { u ⇒
      u.toModel shouldBe model.User(u.id.userId, u.firstName, u.lastName, u.userName)
    }
  }

  behavior of "Chat ToModel"

  val chatDTOGen = for {
    id ← arbitrary[Int]
    `type` ← Gen.oneOf("private", "group", "supergroup", "channel")
    title ← arbitrary[Option[String]]
    userName ← arbitrary[Option[String]]
    firstName ← arbitrary[Option[String]]
    lastName ← arbitrary[Option[String]]
  } yield dto.Chat(id, `type`, title, userName, firstName, lastName)

  it should "produce a model" in {
    forAll(chatDTOGen) { c ⇒
      c.toModel shouldBe model.Chat(c.id.chatId, ChatType.unsafe(c.`type`), c.title, c.userName, c.firstName, c.lastName)
    }
  }

  behavior of "PhotoSize"

  val photoSizeDTOGen = for {
    fileId ← arbitrary[String]
    width ← arbitrary[Int]
    height ← arbitrary[Int]
    fileSize ← arbitrary[Option[Int]]
  } yield dto.PhotoSize(fileId, width, height, fileSize)

  it should "produce a model" in {
    forAll(photoSizeDTOGen) { p: dto.PhotoSize ⇒
      p.toModel shouldBe model.PhotoSize(p.fileId.fileId, p.width, p.height, p.fileSize)
    }
  }
}
