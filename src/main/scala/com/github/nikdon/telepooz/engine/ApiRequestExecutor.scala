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

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.client.RequestBuilding
import akka.http.scaladsl.model.Uri
import akka.stream._
import cats.implicits._
import cats.~>
import com.github.nikdon.telepooz.model._
import com.github.nikdon.telepooz.model.methods._
import com.github.nikdon.telepooz.json._
import com.typesafe.config.{Config, ConfigFactory}
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport
import io.circe.syntax._
import io.circe.{Decoder, Json, JsonObject}

import scala.concurrent.{ExecutionContextExecutor, Future}

abstract class ApiRequestExecutor(implicit system: ActorSystem,
                                  materializer: Materializer,
                                  ec: ExecutionContextExecutor)
    extends (Method ~> Future)
    with FailFastCirceSupport
    with CirceEncoders
    with CirceDecoders {

  protected lazy val config: Config       = ConfigFactory.load()
  protected lazy val telegramHost: String = config.getString("telegram.host")
  protected lazy val token: String        = config.getString("telegram.token")

  private[this] lazy val http = Http()

  private[this] def dropNulls(j: Json): Json = {
    j match {
      case x if x.isArray => x.asArray.get.map(dropNulls).asJson
      case x if x.isObject =>
        val filtered = x.asObject.get.toList.flatMap {
          case (_, v) if v.isNull            => None
          case (k, v)                        => Some(k -> dropNulls(v))
        }
        JsonObject.fromIterable(filtered).asJson
      case x => x
    }
  }

  private[this] def go[B: Decoder](methodName: String, data: Json): Future[Response[B]] = {
    val uri  = "https://" |+| telegramHost |+| "/bot" + token |+| "/" + methodName
    val body = RequestBuilding.Post(Uri(uri), content = data)
    for {
      response <- http.singleRequest(body)
      decoded  <- unmarshaller(responseDecoder).apply(response.entity)
    } yield decoded
  }

  override def apply[A](fa: Method[A]): Future[A] = fa match {
    case m: GetMe.type             => go(m.name, dropNulls(m.asJson))
    case m: GetWebhookInfo.type    => go(m.name, dropNulls(m.asJson))
    case m: SendMessage            => go(m.name, dropNulls(m.asJson))
    case m: SendGame               => go(m.name, dropNulls(m.asJson))
    case m: SetGameScore           => go(m.name, dropNulls(m.asJson))
    case m: GetGameHighScores      => go(m.name, dropNulls(m.asJson))
    case m: ForwardMessage         => go(m.name, dropNulls(m.asJson))
    case m: GetUpdates             => go(m.name, dropNulls(m.asJson))
    case m: SendPhoto              => go(m.name, dropNulls(m.asJson))
    case m: SendAudio              => go(m.name, dropNulls(m.asJson))
    case m: SendDocument           => go(m.name, dropNulls(m.asJson))
    case m: SendSticker            => go(m.name, dropNulls(m.asJson))
    case m: SendVideo              => go(m.name, dropNulls(m.asJson))
    case m: SendVoice              => go(m.name, dropNulls(m.asJson))
    case m: SendLocation           => go(m.name, dropNulls(m.asJson))
    case m: SendVenue              => go(m.name, dropNulls(m.asJson))
    case m: SendContact            => go(m.name, dropNulls(m.asJson))
    case m: SendChatAction         => go(m.name, dropNulls(m.asJson))
    case m: GetUserProfilePhotos   => go(m.name, dropNulls(m.asJson))
    case m: GetFile                => go(m.name, dropNulls(m.asJson))
    case m: KickChatMember         => go(m.name, dropNulls(m.asJson))
    case m: LeaveChat              => go(m.name, dropNulls(m.asJson))
    case m: UnbanChatMember        => go(m.name, dropNulls(m.asJson))
    case m: GetChat                => go(m.name, dropNulls(m.asJson))
    case m: GetChatAdministrators  => go(m.name, dropNulls(m.asJson))
    case m: GetChatMembersCount    => go(m.name, dropNulls(m.asJson))
    case m: GetChatMember          => go(m.name, dropNulls(m.asJson))
    case m: AnswerCallbackQuery    => go(m.name, dropNulls(m.asJson))
    case m: EditMessageCaption     => go(m.name, dropNulls(m.asJson))
    case m: EditMessageReplyMarkup => go(m.name, dropNulls(m.asJson))
    case m: EditMessageText        => go(m.name, dropNulls(m.asJson))
    case m: SetWebhook             => go(m.name, dropNulls(m.asJson))
  }
}
