package com.github.nikdon.telepooz

import shapeless.tag
import shapeless.tag.@@


@scala.annotation.implicitNotFound("No member of type class IsResourceId in scope for ${A}")
trait IsResourceId[A]


object IsResourceId {
  implicit val intIsResourceId = new IsResourceId[Int] {}
  implicit val longIsResourceId = new IsResourceId[Long] {}
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

    implicit class ResiurseIdOps[A: IsResourceId](a: A) {
      def chatId: A @@ ChatId = tag[ChatId](a)
      def fileId: A @@ FileId = tag[FileId](a)
      def foursquareId: A @@ FoursquareId = tag[FoursquareId](a)
      def messageId: A @@ MessageId = tag[MessageId](a)
      def resultId: A @@ ResultId = tag[ResultId](a)
      def updateId: A @@ UpdateId = tag[UpdateId](a)
      def userId: A @@ UserId = tag[UserId](a)
      def queryId: A @@ QueryId = tag[QueryId](a)
    }

  }

  object syntax extends Syntax

}
