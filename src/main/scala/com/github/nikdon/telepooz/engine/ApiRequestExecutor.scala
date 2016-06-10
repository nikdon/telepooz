package com.github.nikdon.telepooz.engine

import akka.actor.ActorSystem
import akka.event.Logging
import akka.http.scaladsl.Http
import akka.http.scaladsl.Http.OutgoingConnection
import akka.http.scaladsl.client.RequestBuilding
import akka.http.scaladsl.model.{HttpRequest, HttpResponse, Uri}
import akka.stream._
import akka.stream.scaladsl.{Flow, Keep, RunnableGraph, Sink, Source}
import cats.std.string._
import cats.syntax.semigroup._
import cats.{Monad, ~>}
import com.github.nikdon.telepooz.model._
import com.github.nikdon.telepooz.raw.RawRequest._
import com.github.nikdon.telepooz.raw._
import com.typesafe.config.{Config, ConfigFactory}
import de.heikoseeberger.akkahttpcirce.CirceSupport
import io.circe.syntax._
import io.circe.{Decoder, Json, JsonObject}

import scala.concurrent.{ExecutionContext, ExecutionContextExecutor, Future}


trait ApiRequestExecutor extends (RawRequest ~> Future)
                                 with CirceSupport
                                 with CirceEncoders
                                 with CirceDecoders {

  protected implicit def system: ActorSystem = ActorSystem("AkkaRequestExecutorSystem")
  protected implicit def executor: ExecutionContextExecutor = system.dispatcher
  protected implicit def materializer: Materializer = ActorMaterializer()

  protected implicit def MonadFuture(implicit ec: ExecutionContext): Monad[Future] = new Monad[Future] {
    override def flatMap[A, B](fa: Future[A])(f: (A) ⇒ Future[B]): Future[B] = fa.flatMap(f)
    override def pure[A](x: A): Future[A] = Future.successful(x)
  }

  protected def config: Config = ConfigFactory.load()
  protected def telegramHost: String = config.getString("telegram.host")
  protected def token: String = config.getString("telegram.token")

  protected def logger = Logging(system, getClass)

  private[this] def telegramConnectionFlow: Flow[HttpRequest, HttpResponse, Future[OutgoingConnection]] =
    Http(system).outgoingConnectionHttps(telegramHost)

  private[this] def telegramRequest(request: HttpRequest): RunnableGraph[Future[HttpResponse]] =
    Source.single(request).via(telegramConnectionFlow).toMat(Sink.head)(Keep.right)

  override def apply[A](fa: RawRequest[A]): Future[A] = {
    def dropNulls(j: Json): Json = j.withObject { c ⇒
      val fields = c.toList.filterNot { case (f, v) ⇒ v.isNull }
      JsonObject.fromIterable(fields).asJson
    }

    def go[B: Decoder](methodName: String, payload: Json): Future[Response[B]] = {
      val uri = "https://" |+| telegramHost |+| "/bot" + token |+| "/" + methodName
      for {
        response ← telegramRequest(RequestBuilding.Post(Uri(uri), content = dropNulls(payload))).run()
        decoded ← circeUnmarshaller(responseDecoder).apply(response.entity)
      } yield decoded
    }

    fa match {
      case m@GetMe                          ⇒ go(m.name, m.payload)
      case m@SendMessage(payload)           ⇒ go(m.name, m.payload)
      case m@ForwardMessage(payload)        ⇒ go(m.name, m.payload)
      case m@GetUpdates(payload)            ⇒ go(m.name, m.payload)
      case m@SendPhoto(payload)             ⇒ go(m.name, m.payload)
      case m@SendAudio(payload)             ⇒ go(m.name, m.payload)
      case m@SendDocument(payload)          ⇒ go(m.name, m.payload)
      case m@SendSticker(payload)           ⇒ go(m.name, m.payload)
      case m@SendVideo(payload)             ⇒ go(m.name, m.payload)
      case m@SendVoice(payload)             ⇒ go(m.name, m.payload)
      case m@SendLocation(payload)          ⇒ go(m.name, m.payload)
      case m@SendVenue(payload)             ⇒ go(m.name, m.payload)
      case m@SendContact(payload)           ⇒ go(m.name, m.payload)
      case m@SendChatAction(payload)        ⇒ go(m.name, m.payload)
      case m@GetUserProfilePhotos(payload)  ⇒ go(m.name, m.payload)
      case m@GetFile(payload)               ⇒ go(m.name, m.payload)
      case m@KickChatMember(payload)        ⇒ go(m.name, m.payload)
      case m@LeaveChat(payload)             ⇒ go(m.name, m.payload)
      case m@UnbanChatMember(payload)       ⇒ go(m.name, m.payload)
      case m@GetChat(payload)               ⇒ go(m.name, m.payload)
      case m@GetChatAdministrators(payload) ⇒ go(m.name, m.payload)
      case m@GetChatMembersCount(payload)   ⇒ go(m.name, m.payload)
      case m@GetChatMember(payload)         ⇒ go(m.name, m.payload)
      case m@AnswerCallbackQuery(payload)   ⇒ go(m.name, m.payload)
    }
  }
}
