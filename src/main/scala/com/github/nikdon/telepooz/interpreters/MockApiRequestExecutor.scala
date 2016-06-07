package com.github.nikdon.telepooz.interpreters

import java.util.Date

import com.github.nikdon.telepooz.model._
import com.github.nikdon.telepooz.raw.RawRequest
import com.github.nikdon.telepooz.raw.RawRequest.{ForwardMessage, GetMe, GetUpdates, SendMessage}
import com.github.nikdon.telepooz.tags

import scala.concurrent.Future
import scala.util.Random


trait MockApiRequestExecutor extends ApiRequestExecutor with tags.Syntax {

  def r = Random

  override def apply[A](fa: RawRequest[A]): Future[A] = fa match {
    case m@GetMe                   => Future.successful(Response(ok = true, Some(User(r.nextInt.userId, r.nextString(5)))))
    case m@SendMessage(payload)    => Future.successful(Response(ok = true, io.circe.parser.decode[Message](payload.noSpaces).toOption))
    case m@ForwardMessage(payload) => Future.successful(Response(ok = true, io.circe.parser.decode[Message](payload.noSpaces).toOption))
    case m@GetUpdates(payload)     => Future.successful(Response(ok = true, Some(Vector(Update(r.nextInt().updateId,
                                                                                               Some(Message(r.nextInt.messageId,
                                                                                                            new Date(r.nextLong),
                                                                                                            Chat(r.nextInt.chatId, ChatType.Private))))))))
  }

}