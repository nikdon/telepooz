package com.github.nikdon.telepooz.engine

import java.util.Date

import com.github.nikdon.telepooz.model._
import com.github.nikdon.telepooz.raw.RawRequest
import com.github.nikdon.telepooz.raw.RawRequest._
import com.github.nikdon.telepooz.tags

import scala.concurrent.Future
import scala.util.Random


trait MockApiRequestExecutor extends ApiRequestExecutor with tags.Syntax {

  def r = Random

  val fakeChat = Chat(r.nextLong.chatId, ChatType.Private)
  val fakeFile = File(r.nextString(5).fileId, None, None)
  val fakeMessage = Message(r.nextLong.messageId, new Date(r.nextLong), Chat(r.nextLong.chatId, ChatType.Private))
  val fakeUserProfilePhotos = UserProfilePhotos(r.nextInt, Vector.empty)
  val fakeUser = User(r.nextInt.userId, r.nextString(3))

  override def apply[A](fa: RawRequest[A]): Future[A] = fa match {
    case m@GetMe                   =>
      Future.successful(Response(ok = true, Some(User(r.nextInt.userId, r.nextString(5)))))
    case m@SendMessage(payload)    =>
      println("payload = " + payload.spaces2)
      Future.successful(Response(ok = true, io.circe.parser.decode[Message](payload.noSpaces).toOption))
    case m@ForwardMessage(payload) =>
      Future.successful(Response(ok = true, io.circe.parser.decode[Message](payload.noSpaces).toOption))

    case m@SendPhoto(payload) ⇒
      Future.successful(Response(ok = true, Some(fakeMessage)))
    case m@SendAudio(payload) ⇒
      Future.successful(Response(ok = true, Some(fakeMessage)))
    case m@SendDocument(payload) ⇒
      Future.successful(Response(ok = true, Some(fakeMessage)))
    case m@SendSticker(payload) ⇒
      Future.successful(Response(ok = true, Some(fakeMessage)))
    case m@SendVideo(payload) ⇒
      Future.successful(Response(ok = true, Some(fakeMessage)))
    case m@SendVoice(payload) ⇒
      Future.successful(Response(ok = true, Some(fakeMessage)))
    case m@SendLocation(payload) ⇒
      Future.successful(Response(ok = true, Some(fakeMessage)))
    case m@SendVenue(payload) ⇒
      Future.successful(Response(ok = true, Some(fakeMessage)))
    case m@SendContact(payload) ⇒
      Future.successful(Response(ok = true, Some(fakeMessage)))
    case m@SendChatAction(payload) ⇒
      Future.successful(Response(ok = true, Some(true)))
    case m@GetUserProfilePhotos(payload) ⇒
      Future.successful(Response(ok = true, Some(fakeUserProfilePhotos)))
    case m@GetFile(payload) ⇒
      Future.successful(Response(ok = true, Some(fakeFile)))
    case m@KickChatMember(payload) ⇒
      Future.successful(Response(ok = true, Some(true)))
    case m@LeaveChat(payload) ⇒
      Future.successful(Response(ok = true, Some(true)))
    case m@UnbanChatMember(payload) ⇒
      Future.successful(Response(ok = true, Some(true)))
    case m@GetChat(payload) ⇒
      Future.successful(Response(ok = true, Some(fakeChat)))
    case m@GetChatAdministrators(payload) ⇒
      Future.successful(Response(ok = true, Some(Vector(ChatMember(fakeUser, MemberStatus.Member)))))
    case m@GetChatMembersCount(payload) ⇒
      Future.successful(Response(ok = true, Some(r.nextInt)))
    case m@GetChatMember(payload) ⇒
      Future.successful(Response(ok = true, Some(ChatMember(fakeUser, MemberStatus.Member))))
    case m@AnswerCallbackQuery(payload) ⇒
      Future.successful(Response(ok = true, Some(true)))

    case m@GetUpdates(payload)     =>
      Future.successful(Response(ok = true, Some(Vector(Update(r.nextLong.updateId,
                                                               Some(Message(r.nextLong.messageId,
                                                                            new Date(r.nextLong),
                                                                            fakeChat))),
                                                        Update(r.nextLong.updateId,
                                                               Some(Message(r.nextLong.messageId,
                                                                            new Date(r.nextLong),
                                                                            fakeChat,
                                                                            text = Some("/test"))))))))
  }

}