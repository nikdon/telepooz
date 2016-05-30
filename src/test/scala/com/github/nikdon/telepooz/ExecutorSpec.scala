package com.github.nikdon.telepooz

import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}


class ExecutorSpec extends FlatSpec
                       with Matchers
                       with GeneratorDrivenPropertyChecks {

  behavior of "Executor"

  it should "return a result of exe" in {

//    import scala.concurrent.ExecutionContext.Implicits.global
//
//    implicit def M: Monad[Future] = new Monad[Future] {
//      override def flatMap[A, B](fa: Future[A])(f: (A) ⇒ Future[B]): Future[B] = fa.flatMap(f)
//      override def pure[A](x: A): Future[A] = Future.successful(x)
//    }
//
//    val res4 = (for {
//      a ← com.github.nikdon.telepooz.api.execute(model.methods.GetMe.toDTO.toRawRequest)
//      b ← com.github.nikdon.telepooz.api.execute(model.methods.SendMessage(33.chatId, a.user_name.getOrElse("TEST"), replyMarkup = Some(model.ForceReply(forceReply = true, None))).toDTO.toRawRequest)
//    } yield b).foldMap(new AkkaInterpreter {
//      override implicit def system: ActorSystem = ActorSystem("TestAkka")
//      override implicit def executor: ExecutionContextExecutor = system.dispatcher
//      override implicit def materializer: Materializer = ActorMaterializer()
//    })
//
//    println("res from Future = " + Await.result(res4, Duration.Inf))

//    val res5 = com.github.nikdon.telepooz.api.execute(model.methods.GetMe.toDTO.toRawRequest).foldMap(new AkkaInterpreter[Id] {
//      override implicit def system: ActorSystem = ActorSystem("TestAkka")
//      override implicit def executor: ExecutionContextExecutor = system.dispatcher
//      override implicit def materializer: Materializer = ActorMaterializer()
//    })
//
//    println("res from Id = " + res5)
//
//    val res6 = com.github.nikdon.telepooz.api.execute(model.methods.SendMessage(123.chatId, "hello", replyToMessageId = Some(123.messageId)).toDTO.toRawRequest).foldMap(new AkkaInterpreter[Id] {
//      override implicit def system: ActorSystem = ActorSystem("TestAkka")
//      override implicit def executor: ExecutionContextExecutor = system.dispatcher
//      override implicit def materializer: Materializer = ActorMaterializer()
//    })
//
//    println("res from Id = " + res6)
  }
}
