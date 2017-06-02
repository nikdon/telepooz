/*
 * Copyright 2016 Nikolay Donets
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.nikdon.telepooz.engine

import java.util.Date

import akka.actor.ActorSystem
import akka.stream.Materializer
import com.github.nikdon.telepooz.model._
import com.github.nikdon.telepooz.model.methods._
import com.github.nikdon.telepooz.model.methods.payments.{AnswerPreCheckoutQuery, AnswerShippingQuery, SendInvoice}

import scala.concurrent.{ExecutionContextExecutor, Future}
import scala.util.Random

class MockApiRequestExecutor(
    nUpdates: Int = 1)(implicit system: ActorSystem, materializer: Materializer, ec: ExecutionContextExecutor)
    extends ApiRequestExecutor()(system, materializer, ec) {

  def r = Random

  val fakeChat              = Chat(r.nextLong(), ChatType.Private)
  val fakeFile              = File(r.nextString(5), None, None)
  val fakeMessage           = Message(r.nextLong, new Date(r.nextLong), Chat(r.nextLong(), ChatType.Private))
  val fakeUserProfilePhotos = UserProfilePhotos(r.nextInt, Vector.empty)
  val fakeUser              = User(r.nextInt, r.nextString(3))

  lazy val updates =
    Vector.tabulate(nUpdates)(_ ⇒ Update(r.nextLong, Some(Message(r.nextLong, new Date(r.nextLong), fakeChat))))

  override def apply[A](fa: Method[A]): Future[A] = fa match {
    case m @ GetMe =>
      Future.successful(Response(ok = true, Some(User(r.nextInt, r.nextString(5)))))

    case m @ GetWebhookInfo =>
      Future.successful(
        Response(ok = true,
                 Some(WebhookInfo(r.nextString(5), r.nextBoolean, r.nextLong, r.nextLong, r.nextString(5)))))

    case m: SendMessage =>
      Future.successful(Response(ok = true, Some(fakeMessage)))
    case m: ForwardMessage =>
      Future.successful(Response(ok = true, Some(fakeMessage)))

    case m: SendGame ⇒
      Future.successful(Response(ok = true, Some(fakeMessage)))
    case m: SetGameScore ⇒
      Future.successful(Response(ok = true, Some(fakeMessage)))
    case m: GetGameHighScores ⇒
      Future.successful(Response(ok = true, Some(Vector(GameHighScore(r.nextInt, fakeUser, r.nextInt)))))

    case m: AnswerPreCheckoutQuery ⇒ Future.successful(Response(ok = true, Some(true)))
    case m: AnswerShippingQuery    ⇒ Future.successful(Response(ok = true, Some(true)))
    case m: SendInvoice            ⇒ Future.successful(Response(ok = true, Some(fakeMessage)))

    case m: SendPhoto ⇒
      Future.successful(Response(ok = true, Some(fakeMessage)))
    case m: SendAudio ⇒
      Future.successful(Response(ok = true, Some(fakeMessage)))
    case m: SendDocument ⇒
      Future.successful(Response(ok = true, Some(fakeMessage)))
    case m: SendSticker ⇒
      Future.successful(Response(ok = true, Some(fakeMessage)))
    case m: SendVideo ⇒
      Future.successful(Response(ok = true, Some(fakeMessage)))
    case m: SendVideoNote ⇒
      Future.successful(Response(ok = true, Some(fakeMessage)))
    case m: SendVoice ⇒
      Future.successful(Response(ok = true, Some(fakeMessage)))
    case m: SendLocation ⇒
      Future.successful(Response(ok = true, Some(fakeMessage)))
    case m: SendVenue ⇒
      Future.successful(Response(ok = true, Some(fakeMessage)))
    case m: SendContact ⇒
      Future.successful(Response(ok = true, Some(fakeMessage)))
    case m: SendChatAction ⇒
      Future.successful(Response(ok = true, Some(true)))
    case m: GetUserProfilePhotos ⇒
      Future.successful(Response(ok = true, Some(fakeUserProfilePhotos)))
    case m: GetFile ⇒
      Future.successful(Response(ok = true, Some(fakeFile)))
    case m: KickChatMember ⇒
      Future.successful(Response(ok = true, Some(true)))
    case m: LeaveChat ⇒
      Future.successful(Response(ok = true, Some(true)))
    case m: UnbanChatMember ⇒
      Future.successful(Response(ok = true, Some(true)))
    case m: DeleteMessage ⇒
      Future.successful(Response(ok = true, Some(true)))
    case m: GetChat ⇒
      Future.successful(Response(ok = true, Some(fakeChat)))
    case m: GetChatAdministrators ⇒
      Future.successful(Response(ok = true, Some(Vector(ChatMember(fakeUser, MemberStatus.Member)))))
    case m: GetChatMembersCount ⇒
      Future.successful(Response(ok = true, Some(r.nextInt)))
    case m: GetChatMember ⇒
      Future.successful(Response(ok = true, Some(ChatMember(fakeUser, MemberStatus.Member))))
    case m: AnswerCallbackQuery ⇒
      Future.successful(Response(ok = true, Some(true)))

    case m: GetUpdates =>
      Future.successful(Response(ok = true, Some(updates)))

    case m: EditMessageCaption ⇒
      Future.successful(Response(ok = true, Some(Right(fakeMessage))))
    case m: EditMessageReplyMarkup ⇒
      Future.successful(Response(ok = true, Some(Right(fakeMessage))))
    case m: EditMessageText ⇒
      Future.successful(Response(ok = true, Some(Right(fakeMessage))))

    case m: SetWebhook ⇒
      Future.successful(Response(ok = true, Some(true)))
  }

}
