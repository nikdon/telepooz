package com.github.nikdon.telepooz.model

import java.util.Date

import com.github.nikdon.telepooz.raw.{CirceDecoders, CirceEncoders}
import com.github.nikdon.telepooz.tags
import io.circe.syntax._
import org.scalacheck.Arbitrary.arbitrary
import org.scalacheck.Gen
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}


class MessageTest extends FlatSpec
                          with Matchers
                          with GeneratorDrivenPropertyChecks
                          with tags.Syntax
                          with CirceEncoders
                          with CirceDecoders {

  behavior of "Message"

  import MessageTest._

  it should "convert to a json and back to a model" in {
    forAll(fullMessageGen) { message ⇒
      val json = message.asJson.noSpaces
      io.circe.parser.decode[Message](json) foreach (res ⇒ res shouldEqual message)
    }
  }

}

object MessageTest extends tags.Syntax {
  val simpleMessageGen: Gen[Message] = for {
    id ← arbitrary[Long].map(_.messageId)
    date ← arbitrary[Date]
    chat ← ChatTest.chatGen
  } yield Message(id, date, chat)

  val fullMessageGen: Gen[Message] = for {
    messageId ← arbitrary[Long].map(_.messageId)
    date ← arbitrary[Date]
    chat ← ChatTest.chatGen
    from ← arbitrary[Option[Boolean]].map(_ ⇒ UserTest.userGen.sample)
    forwardFrom ← arbitrary[Option[Boolean]].map(_ ⇒ UserTest.userGen.sample)
    forwardFromChat ← arbitrary[Option[Int]].map(_ ⇒ ChatTest.chatGen.sample)
    forwardDate ← arbitrary[Option[Date]]
    replyToMessage ← arbitrary[Option[Int]].map(_ ⇒ simpleMessageGen.sample)
    text ← arbitrary[Option[String]]
    entities ← arbitrary[Option[Vector[Int]]].map(_.map(_.flatMap(_ ⇒ MessageEntityTest.messageEntityGen.sample)))
    audio ← arbitrary[Option[Int]].map(_ ⇒ AudioTest.auidoGen.sample)
    document ← arbitrary[Option[Boolean]].map(_ ⇒ DocumentTest.documentGen.sample)
    photo ← arbitrary[Option[Boolean]].flatMap(_ ⇒ Gen.nonEmptyListOf(PhotoSizeTest.photoSizeGen).sample.map(_.toVector))
    sticker ← arbitrary[Option[Boolean]].map(_ ⇒ StickerTest.stickerGen.sample)
    video ← arbitrary[Option[Boolean]].map(_ ⇒ VideoTest.videoGen.sample)
    voice ← arbitrary[Option[Boolean]].map(_ ⇒ VoiceTest.voiceGen.sample)
    caption ← arbitrary[Option[String]]
    contact ← arbitrary[Option[Int]].map(_ ⇒ ContactTest.contactGen.sample)
    location ← arbitrary[Option[Boolean]].map(_ ⇒ LocationTest.locationGen.sample)
    venue ← arbitrary[Option[Boolean]].map(_ ⇒ VenueTest.venueGen.sample)
    newChatMember ← arbitrary[Option[Boolean]].map(_ ⇒ UserTest.userGen.sample)
    leftChatMember ← arbitrary[Option[Boolean]].map(_ ⇒ UserTest.userGen.sample)
    newChatTitle ← arbitrary[Option[String]]
    newChatPhoto ← arbitrary[Option[Boolean]].flatMap(_ ⇒ Gen.nonEmptyListOf(PhotoSizeTest.photoSizeGen).sample.map(_.toVector))
    deleteChatPhoto ← arbitrary[Option[Boolean]]
    groupChatCreated ← arbitrary[Option[Boolean]]
    superGroupChatCreated ← arbitrary[Option[Boolean]]
    channelChatCreated ← arbitrary[Option[Boolean]]
    migrateToChatId ← arbitrary[Option[Long]].map(_.map(_.chatId))
    migrateFromChatId ← arbitrary[Option[Long]].map(_.map(_.chatId))
    pinnedMessage ← arbitrary[Option[Int]].map(_ ⇒ simpleMessageGen.sample)
  } yield Message(messageId,
                  date,
                  chat,
                  from,
                  forwardFrom,
                  forwardFromChat,
                  forwardDate,
                  replyToMessage,
                  text,
                  entities,
                  audio,
                  document,
                  photo,
                  sticker,
                  video,
                  voice,
                  caption,
                  contact,
                  location,
                  venue,
                  newChatMember,
                  leftChatMember,
                  newChatTitle,
                  newChatPhoto,
                  deleteChatPhoto,
                  groupChatCreated,
                  superGroupChatCreated,
                  channelChatCreated,
                  migrateToChatId,
                  migrateFromChatId,
                  pinnedMessage)
}
