package com.github.nikdon.telepooz

import com.github.nikdon.telepooz.engine.{MockApiRequestExecutor, Telepooz}
import com.github.nikdon.telepooz.raw.CirceEncoders
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}

import scala.concurrent.Await
import scala.concurrent.duration._


class ExecutorSpec extends FlatSpec
                           with Matchers
                           with GeneratorDrivenPropertyChecks
                           with CirceEncoders {

//  val i = new MockApiRequestExecutor {}
//
//  implicit val ec = i.system.dispatcher
//
//  implicit def MonadFuture(implicit ec: ExecutionContext): Monad[Future] = new Monad[Future] {
//    override def flatMap[A, B](fa: Future[A])(f: (A) ⇒ Future[B]): Future[B] = fa.flatMap(f)
//    override def pure[A](x: A): Future[A] = Future.successful(x)
//  }

  behavior of "Executor"

//  it should "return a result of exe" in {
//
//    val res0 = (for {
//      a ← api.execute(model.methods.GetMe.toRawRequest)
//      b ← api.execute(model.methods.SendMessage(123.chatId, a.result.fold("empty")(_.first_name)).toRawRequest)
//    } yield b).foldMap(i)
//
//    println("res0 from Future = " + Await.result(res0, Duration.Inf))
//
//    val res1 = (for {
//      a ← api.execute(model.methods.SendMessage(123.chatId, "TEASDF").toRawRequest)
//      b ← api.execute(model.methods.GetMe.toRawRequest)
//    } yield b).foldMap(i)
//
//    println("res1 from Future = " + Await.result(res1, Duration.Inf))
//
//    val res2 = (for {
//      c ← api.execute(ForwardMessage(123.chatId, "asdf".chatId, 321.messageId).toRawRequest)
//      a ← api.execute(model.methods.SendMessage(123.chatId, "TEASDF").toRawRequest)
//      b ← api.execute(model.methods.GetMe.toRawRequest)
//    } yield b).foldMap(i)
//
//    println("res2 from Future = " + Await.result(res2, Duration.Inf))
//
//    val res3 = (for {
//      d ← api.execute(GetUpdates().toRawRequest)
//    } yield d).foldMap(i)
//
//    println("res3 from Future = " + Await.result(res3, Duration.Inf))
//  }

  it should "poll" in {
    val telebot = new Telepooz with MockApiRequestExecutor {
      override def reactions: Map[String, Reaction] = Map(
        "/test" → (implicit message ⇒ args ⇒ {
          reply("Hi there!")
        })
      )
    }

    val asdf = telebot.run()
    Await.ready(asdf, 20.seconds)
  }
}
