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
    id ← arbitrary[Int].map(_.messageId)
    date ← arbitrary[Date]
    chat ← ChatTest.chatGen
  } yield Message(id, date, chat)

  val fullMessageGen: Gen[Message] = for {
    messageId ← arbitrary[Int].map(_.messageId)
    date ← arbitrary[Date]
    chat ← ChatTest.chatGen
    // from: Option[User] = None TODO
    // forwardFrom: Option[User] = None TODO
    forwardFromChat ← arbitrary[Option[Int]].map(_ ⇒ ChatTest.chatGen.sample)
    forwardDate ← arbitrary[Option[Date]]
    replyToMessage ← arbitrary[Option[Int]].map(_ ⇒ simpleMessageGen.sample)
    text ← arbitrary[Option[String]]
    entities ← arbitrary[Option[Vector[Int]]].map(_.map(_.flatMap(_ ⇒ MessageEntityTest.messageEntityGen.sample)))
    audio ← arbitrary[Option[Int]].map(_ ⇒ AudioTest.auidoGen.sample)
    // document: Option[Document] = None TODO
    // photo: Option[Vector[PhotoSize]] = None TODO
    // sticker: Option[Sticker] = None, TODO
    // video: Option[Video] = None, TODO
    // voice: Option[Voice] = None, TODO
    caption ← arbitrary[Option[String]]
    contact ← arbitrary[Option[Int]].map(_ ⇒ ContactTest.contactGen.sample)
    // location: Option[Location] = None, TODO
    // venue: Option[Venue] = None, TODO
    // newChatMember: Option[User] = None, TODO
    // leftChatMember: Option[User] = None, TODO
    newChatTitle ← arbitrary[Option[String]]
    // newChatPhoto: Option[Vector[PhotoSize]] = None, TODO
    deleteChatPhoto ← arbitrary[Option[Boolean]]
    groupChatCreated ← arbitrary[Option[Boolean]]
    superGroupChatCreated ← arbitrary[Option[Boolean]]
    channelChatCreated ← arbitrary[Option[Boolean]]
    migrateToChatId ← arbitrary[Option[Int]].map(_.map(_.chatId))
    migrateFromChatId ← arbitrary[Option[Int]].map(_.map(_.chatId))
    pinnedMessage ← arbitrary[Option[Int]].map(_ ⇒ simpleMessageGen.sample)
  } yield Message(messageId,
                  date,
                  chat,
                  None,
                  None,
                  forwardFromChat,
                  forwardDate,
                  replyToMessage,
                  text,
                  entities,
                  audio,
                  None,
                  None,
                  None,
                  None,
                  None,
                  caption,
                  contact,
                  None,
                  None,
                  None,
                  None,
                  newChatTitle,
                  None,
                  deleteChatPhoto,
                  groupChatCreated,
                  superGroupChatCreated,
                  channelChatCreated,
                  migrateToChatId,
                  migrateFromChatId,
                  pinnedMessage)
}
