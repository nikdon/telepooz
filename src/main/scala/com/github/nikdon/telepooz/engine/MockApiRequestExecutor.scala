package com.github.nikdon.telepooz.engine

import java.util.Date

import akka.actor.ActorSystem
import akka.stream.Materializer
import com.github.nikdon.telepooz.model._
import com.github.nikdon.telepooz.raw.RawRequest
import com.github.nikdon.telepooz.raw.RawRequest._
import com.github.nikdon.telepooz.tags

import scala.concurrent.{ExecutionContextExecutor, Future}
import scala.util.Random


class MockApiRequestExecutor(nUpdates: Int = 1)(implicit system: ActorSystem, materializer: Materializer, ec: ExecutionContextExecutor)
    extends ApiRequestExecutor()(system, materializer, ec)
    with tags.Syntax {

  def r = Random

  val fakeChat              = Chat(r.nextLong.chatId, ChatType.Private)
  val fakeFile              = File(r.nextString(5).fileId, None, None)
  val fakeMessage           = Message(r.nextLong.messageId, new Date(r.nextLong), Chat(r.nextLong.chatId, ChatType.Private))
  val fakeUserProfilePhotos = UserProfilePhotos(r.nextInt, Vector.empty)
  val fakeUser              = User(r.nextInt.userId, r.nextString(3))

  lazy val updates =
    Vector.tabulate(nUpdates)(_ ⇒ Update(r.nextLong.updateId, Some(Message(r.nextLong.messageId, new Date(r.nextLong), fakeChat))))

  override def apply[A](fa: RawRequest[A]): Future[A] = fa match {
    case m @ GetMe =>
      Future.successful(Response(ok = true, Some(User(r.nextInt.userId, r.nextString(5)))))
    case m @ SendMessage(payload) =>
      Future.successful(Response(ok = true, Some(fakeMessage)))
    case m @ ForwardMessage(payload) =>
      Future.successful(Response(ok = true, Some(fakeMessage)))

    case m @ SendPhoto(payload) ⇒
      Future.successful(Response(ok = true, Some(fakeMessage)))
    case m @ SendAudio(payload) ⇒
      Future.successful(Response(ok = true, Some(fakeMessage)))
    case m @ SendDocument(payload) ⇒
      Future.successful(Response(ok = true, Some(fakeMessage)))
    case m @ SendSticker(payload) ⇒
      Future.successful(Response(ok = true, Some(fakeMessage)))
    case m @ SendVideo(payload) ⇒
      Future.successful(Response(ok = true, Some(fakeMessage)))
    case m @ SendVoice(payload) ⇒
      Future.successful(Response(ok = true, Some(fakeMessage)))
    case m @ SendLocation(payload) ⇒
      Future.successful(Response(ok = true, Some(fakeMessage)))
    case m @ SendVenue(payload) ⇒
      Future.successful(Response(ok = true, Some(fakeMessage)))
    case m @ SendContact(payload) ⇒
      Future.successful(Response(ok = true, Some(fakeMessage)))
    case m @ SendChatAction(payload) ⇒
      Future.successful(Response(ok = true, Some(true)))
    case m @ GetUserProfilePhotos(payload) ⇒
      Future.successful(Response(ok = true, Some(fakeUserProfilePhotos)))
    case m @ GetFile(payload) ⇒
      Future.successful(Response(ok = true, Some(fakeFile)))
    case m @ KickChatMember(payload) ⇒
      Future.successful(Response(ok = true, Some(true)))
    case m @ LeaveChat(payload) ⇒
      Future.successful(Response(ok = true, Some(true)))
    case m @ UnbanChatMember(payload) ⇒
      Future.successful(Response(ok = true, Some(true)))
    case m @ GetChat(payload) ⇒
      Future.successful(Response(ok = true, Some(fakeChat)))
    case m @ GetChatAdministrators(payload) ⇒
      Future.successful(Response(ok = true, Some(Vector(ChatMember(fakeUser, MemberStatus.Member)))))
    case m @ GetChatMembersCount(payload) ⇒
      Future.successful(Response(ok = true, Some(r.nextInt)))
    case m @ GetChatMember(payload) ⇒
      Future.successful(Response(ok = true, Some(ChatMember(fakeUser, MemberStatus.Member))))
    case m @ AnswerCallbackQuery(payload) ⇒
      Future.successful(Response(ok = true, Some(true)))

    case m @ GetUpdates(payload) =>
      Future.successful(Response(ok = true, Some(updates)))

    case m @ EditMessageCaption(payload) ⇒
      Future.successful(Response(ok = true, Some(Right(fakeMessage))))
    case m @ EditMessageReplyMarkup(payload) ⇒
      Future.successful(Response(ok = true, Some(Right(fakeMessage))))
    case m @ EditMessageText(payload) ⇒
      Future.successful(Response(ok = true, Some(Right(fakeMessage))))

    case m @ SetWebhook(payload) ⇒
      Future.successful(Response(ok = true, Some(true)))
  }

}
