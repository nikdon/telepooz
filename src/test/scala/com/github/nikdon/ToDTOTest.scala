package com.github.nikdon

import org.scalacheck.Arbitrary.arbitrary
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}
import tags.syntax._
import ToDTO.syntax._
import com.github.nikdon.model.{Channel, Group, Private, SuperGroup}
import org.scalacheck.Gen


class ToDTOTest extends FlatSpec
                        with Matchers
                        with GeneratorDrivenPropertyChecks {

  behavior of "User ToDTO"

  val userModelGen = for {
    id ← arbitrary[Int].map(_.userId)
    firstName ← arbitrary[String]
    lastName ← arbitrary[Option[String]]
    userName ← arbitrary[Option[String]]
  } yield model.User(id, firstName, lastName, userName)

  it should "produce a DTO" in {
    forAll(userModelGen) { u ⇒
      u.toDTO shouldBe dto.User(u.id, u.firstName, u.lastName, u.userName)
    }
  }

  behavior of "Chat ToDTO"

  val chatModelGen = for {
    id ← arbitrary[Int].map(_.chatId)
    `type` ← Gen.oneOf(Private, Group, SuperGroup, Channel)
    title ← arbitrary[Option[String]]
    userName ← arbitrary[Option[String]]
    firstName ← arbitrary[Option[String]]
    lastName ← arbitrary[Option[String]]
  } yield model.Chat(id, `type`, title, userName, firstName, lastName)

  it should "produce a DTO" in {
    forAll(chatModelGen) { c ⇒
      c.toDTO shouldBe dto.Chat(c.id, c.`type`.name, c.title, c.userName, c.firstName, c.lastName)
    }
  }

  behavior of "PhotoSize ToDTO"

  val photoModelGen = for {
    fileId ← arbitrary[String].map(_.fileId)
    width ← arbitrary[Int]
    height ← arbitrary[Int]
    fileSize ← arbitrary[Option[Int]]
  } yield model.PhotoSize(fileId, width, height, fileSize)

  it should "produce a DTO" in {
    forAll(photoModelGen) { p: model.PhotoSize ⇒
      p.toDTO shouldBe dto.PhotoSize(p.fileId, p.width, p.height, p.fileSize)
    }
  }
}