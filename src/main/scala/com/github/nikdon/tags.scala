package com.github.nikdon

import shapeless.tag
import shapeless.tag.@@


trait IsResourceId[A]


object IsResourceId {
  implicit val intIsResourceId = new IsResourceId[Int] {}
}


object tags {

  trait UserId
  trait ChatId

  trait Syntax {

    implicit class ResiurseIdOps[A: IsResourceId](a: A) {
      def userId: A @@ UserId = tag[UserId](a)
      def chatId: A @@ ChatId = tag[ChatId](a)
    }

  }

  object syntax extends Syntax

}
