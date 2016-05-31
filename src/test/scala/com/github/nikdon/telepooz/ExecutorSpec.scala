package com.github.nikdon.telepooz

import akka.actor.ActorSystem
import akka.stream.{ActorMaterializer, Materializer}
import cats.Monad
import com.github.nikdon.telepooz.ToRawRequest.syntax._
import com.github.nikdon.telepooz.interpreters.AkkaInterpreter
import com.github.nikdon.telepooz.model.methods.{ForwardMessage, GetUpdates}
import com.github.nikdon.telepooz.raw.CirceEncoders
import com.github.nikdon.telepooz.tags.syntax._
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext, ExecutionContextExecutor, Future}


class ExecutorSpec extends FlatSpec
                           with Matchers
                           with GeneratorDrivenPropertyChecks
                           with CirceEncoders {

  val i = new AkkaInterpreter {
    override implicit def system: ActorSystem = ActorSystem("TestAkka")
    override implicit def executor: ExecutionContextExecutor = system.dispatcher
    override implicit def materializer: Materializer = ActorMaterializer()
  }

  implicit val ec = i.system.dispatcher

  implicit def M(implicit ec: ExecutionContext): Monad[Future] = new Monad[Future] {
    override def flatMap[A, B](fa: Future[A])(f: (A) ⇒ Future[B]): Future[B] = fa.flatMap(f)
    override def pure[A](x: A): Future[A] = Future.successful(x)
  }

  behavior of "Executor"

  it should "return a result of exe" in {

    implicit def M: Monad[Future] = new Monad[Future] {
      override def flatMap[A, B](fa: Future[A])(f: (A) ⇒ Future[B]): Future[B] = fa.flatMap(f)
      override def pure[A](x: A): Future[A] = Future.successful(x)
    }

    val res0 = (for {
      a ← api.execute(model.methods.GetMe.toRawRequest)
      b ← api.execute(model.methods.SendMessage(123.chatId, a.result.fold("empty")(_.firstName)).toRawRequest)
    } yield b).foldMap(i)

    println("res from Future = " + Await.result(res0, Duration.Inf))

    val res1 = (for {
      a ← api.execute(model.methods.SendMessage(123.chatId, "TEASDF").toRawRequest)
      b ← api.execute(model.methods.GetMe.toRawRequest)
    } yield b).foldMap(i)

    println("res from Future = " + Await.result(res1, Duration.Inf))

    val res2 = (for {
      c ← api.execute(ForwardMessage(123.chatId, "asdf".chatId, 321.messageId).toRawRequest)
      a ← api.execute(model.methods.SendMessage(123.chatId, "TEASDF").toRawRequest)
      b ← api.execute(model.methods.GetMe.toRawRequest)
    } yield b).foldMap(i)

    println("res from Future = " + Await.result(res2, Duration.Inf))

    val res3 = (for {
      d ← api.execute(GetUpdates().toRawRequest)
    } yield d).foldMap(i)

    println("res from Future = " + Await.result(res2, Duration.Inf))
  }
}
