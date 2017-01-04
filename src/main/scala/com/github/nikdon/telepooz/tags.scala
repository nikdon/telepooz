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

package com.github.nikdon.telepooz

import shapeless.tag
import shapeless.tag.@@

@scala.annotation.implicitNotFound("No member of type class IsResourceId in scope for ${A}")
trait IsResourceId[A]

object IsResourceId {
  implicit val intIsResourceId    = new IsResourceId[Int]    {}
  implicit val longIsResourceId   = new IsResourceId[Long]   {}
  implicit val stringIsResourceId = new IsResourceId[String] {}
}

object tags {

  trait ChatId
  trait FileId
  trait FoursquareId
  trait MessageId
  trait ResultId
  trait UpdateId
  trait UserId
  trait QueryId

  trait Syntax {

    implicit class ResourceIdOps[A: IsResourceId](a: A) {
      def chatId: A @@ ChatId             = tag[ChatId](a)
      def fileId: A @@ FileId             = tag[FileId](a)
      def foursquareId: A @@ FoursquareId = tag[FoursquareId](a)
      def messageId: A @@ MessageId       = tag[MessageId](a)
      def resultId: A @@ ResultId         = tag[ResultId](a)
      def updateId: A @@ UpdateId         = tag[UpdateId](a)
      def userId: A @@ UserId             = tag[UserId](a)
      def queryId: A @@ QueryId           = tag[QueryId](a)
    }

  }

  object syntax extends Syntax

}
