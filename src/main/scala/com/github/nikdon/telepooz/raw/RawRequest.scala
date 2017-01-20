///*
// * Copyright 2016 Nikolay Donets
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *     http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//
//package com.github.nikdon.telepooz.raw
//
//import com.github.nikdon.telepooz.model._
//import io.circe.Json
//
//
//sealed trait Method[Result] extends Product with Serializable {
//  def name: String = this.productPrefix
//  def payload: Json
//}
//
//object Method {
//
//  def apply[A](implicit ev: Method[A]): Method[A] = ev
//
//  case object GetMe extends Method[Response[User]] {override val payload = Json.Null}
//  case object GetWebhookInfo extends Method[Response[WebhookInfo]] {override val payload = Json.Null}
//  final case class SendMessage(payload: Json) extends Method[Response[Message]]
//  final case class ForwardMessage(payload: Json) extends Method[Response[Message]]
//  final case class GetUpdates(payload: Json) extends Method[Response[Vector[Update]]]
//  final case class SendGame(payload: Json) extends Method[Response[Message]]
//  final case class SetGameScore(payload: Json) extends Method[Response[Message]]
//  final case class GetGameHighScores(payload: Json) extends Method[Response[Vector[GameHighScore]]]
//
//  // TODO Add an ability to upload files as multipart form
//  final case class SendPhoto(payload: Json) extends Method[Response[Message]]
//  final case class SendAudio(payload: Json) extends Method[Response[Message]]
//  final case class SendDocument(payload: Json) extends Method[Response[Message]]
//  final case class SendSticker(payload: Json) extends Method[Response[Message]]
//  final case class SendVideo(payload: Json) extends Method[Response[Message]]
//  final case class SendVoice(payload: Json) extends Method[Response[Message]]
//
//  final case class SendLocation(payload: Json) extends Method[Response[Message]]
//  final case class SendVenue(payload: Json) extends Method[Response[Message]]
//  final case class SendContact(payload: Json) extends Method[Response[Message]]
//  final case class SendChatAction(payload: Json) extends Method[Response[Boolean]]
//  final case class GetUserProfilePhotos(payload: Json) extends Method[Response[UserProfilePhotos]]
//  final case class GetFile(payload: Json) extends Method[Response[File]]
//  final case class KickChatMember(payload: Json) extends Method[Response[Boolean]]
//  final case class LeaveChat(payload: Json) extends Method[Response[Boolean]]
//  final case class UnbanChatMember(payload: Json) extends Method[Response[Boolean]]
//  final case class GetChat(payload: Json) extends Method[Response[Chat]]
//  final case class GetChatAdministrators(payload: Json) extends Method[Response[Vector[ChatMember]]]
//  final case class GetChatMembersCount(payload: Json) extends Method[Response[Int]]
//  final case class GetChatMember(payload: Json) extends Method[Response[ChatMember]]
//  final case class AnswerCallbackQuery(payload: Json) extends Method[Response[Boolean]]
//
//  final case class EditMessageText(payload: Json) extends Method[Response[Either[Boolean, Message]]]
//  final case class EditMessageCaption(payload: Json) extends Method[Response[Either[Boolean, Message]]]
//  final case class EditMessageReplyMarkup(payload: Json) extends Method[Response[Either[Boolean, Message]]]
//
//  final case class SetWebhook(payload: Json) extends Method[Response[Boolean]]
//}
