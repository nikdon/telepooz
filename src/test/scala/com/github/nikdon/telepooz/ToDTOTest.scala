package com.github.nikdon.telepooz

import com.github.nikdon.telepooz.ToDTO.syntax._
import com.github.nikdon.telepooz.model.ChatType.{Channel, Group, Private, SuperGroup}
import com.github.nikdon.telepooz.model.MessageEntityType._
import com.github.nikdon.telepooz.tags.syntax._
import org.scalacheck.Arbitrary.arbitrary
import org.scalacheck.Gen
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}


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

  behavior of "MessageEntity ToDTO"

  val messageEntityGen = for {
    `type` ← Gen.oneOf(Mention, Hashtag, BotCommand, Url, Email, Bold, Italic, Code, Pre, TextLink)
    offset ← arbitrary[Int]
    length ← arbitrary[Int]
    url ← `type` match {
      case Url ⇒ arbitrary[Option[String]]
      case _ ⇒ Gen.oneOf(None: Option[String], None: Option[String])
    }
  } yield model.MessageEntity(`type`, offset, length, url)

  it should "produce a DTO" in {
    forAll(messageEntityGen) { m ⇒
      m.toDTO shouldBe dto.MessageEntity(m.`type`.name, m.offset, m.length, m.url)
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

  behavior of "Contact"

  val contactGen = for {
    phoneNumber ← arbitrary[String]
    firstName ← arbitrary[String]
    lastName ← arbitrary[Option[String]]
    userId ← arbitrary[Option[Int]].map(_.map(_.userId))
  } yield model.Contact(phoneNumber, firstName, lastName, userId)

  it should "produce a DTO" in {
    forAll(contactGen) { c ⇒
      c.toDTO shouldBe dto.Contact(c.phoneNumber, c.firstName, c.lastName, c.userId)
    }
  }
}