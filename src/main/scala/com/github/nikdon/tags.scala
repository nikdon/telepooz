package com.github.nikdon

import shapeless.tag
import shapeless.tag.@@


trait IsResourceId[A]


object IsResourceId {
  implicit val intIsResourceId = new IsResourceId[Int] {}
  implicit val stringIsResourceId = new IsResourceId[String] {}
}


object tags {

  trait FileId
  trait UserId
  trait ChatId
  trait FoursquareId

  trait Syntax {

    implicit class ResiurseIdOps[A: IsResourceId](a: A) {
      def fileId: A @@ FileId = tag[FileId](a)
      def userId: A @@ UserId = tag[UserId](a)
      def chatId: A @@ ChatId = tag[ChatId](a)
      def foursquareId: A @@ FoursquareId = tag[FoursquareId](a)
    }

  }

  object syntax extends Syntax

}
