package com.github.nikdon.telepooz.interpreters

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.Http.OutgoingConnection
import akka.http.scaladsl.client.RequestBuilding
import akka.http.scaladsl.model.{HttpRequest, HttpResponse, Uri}
import akka.stream.Materializer
import akka.stream.scaladsl.{Flow, Keep, RunnableGraph, Sink, Source}
import cats.~>
import com.github.nikdon.telepooz.raw.RawRequest
import com.typesafe.config.{Config, ConfigFactory}
import de.heikoseeberger.akkahttpcirce.CirceSupport

import scala.concurrent.duration._
import scala.concurrent.{ExecutionContextExecutor, Future}


abstract class AkkaInterpreter extends (RawRequest ~> Future) with CirceSupport {

  implicit def system: ActorSystem
  implicit def executor: ExecutionContextExecutor
  implicit def materializer: Materializer

  def config: Config = ConfigFactory.load()
  def telegramHost: String = config.getString("telegram.host")
  def token: String = config.getString("telegram.token")
  def timeout = 10.seconds

  def telegramConnectionFlow: Flow[HttpRequest, HttpResponse, Future[OutgoingConnection]] =
    Http(system).outgoingConnectionHttps(telegramHost)

  def telegramRequest(request: HttpRequest): RunnableGraph[Future[HttpResponse]] =
    Source.single(request).via(telegramConnectionFlow).toMat(Sink.head)(Keep.right)

  def toHttpRequest[A](c: RawRequest[A]): HttpRequest = {
    val uri = "https://" + telegramHost + "/bot" + token +"/" + c.name
    val payload = c.json
    val res = RequestBuilding.Post(Uri(uri), content = payload)
    println("res = " + res)
    res
  }

  override def apply[A](fa: RawRequest[A]): Future[A] = ???
}
