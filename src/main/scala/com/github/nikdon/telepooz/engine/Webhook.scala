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

package com.github.nikdon.telepooz.engine

import java.util.concurrent.ArrayBlockingQueue

import akka.NotUsed
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.Http.ServerBinding
import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.model.Uri
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server._
import akka.stream._
import akka.stream.scaladsl.Source
import akka.stream.stage.{GraphStage, GraphStageLogic, OutHandler}
import cats.implicits._
import com.github.nikdon.telepooz.api._
import com.github.nikdon.telepooz.json.CirceDecoders
import com.github.nikdon.telepooz.model.methods.SetWebhook
import com.github.nikdon.telepooz.model.{Response, Update}
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport._

import scala.concurrent.{Future, Promise}
import scala.util.Failure

/**
  *
  * @param endpoint         HTTPS url's endpoint to send updates to. Use an empty string to remove webhook integration.
  *                         Ex.: "example-endpoint"
  * @param scheme           HTTPS
  * @param interface        Host
  * @param port             Ports currently supported for Webhooks: 443, 80, 88, 8443.
  * @param max_connections  Maximum allowed number of simultaneous HTTPS connections to the webhook for update delivery,
  *                         1-100. Defaults to 40. Use lower values to limit the load on your bot‘s server, and higher
  *                         values to increase your bot’s throughput.
  * @param allowed_updates  List the types of updates you want your bot to receive.
  *                         For example, specify [“message”, “edited_channel_post”, “callback_query”] to only receive
  *                         updates of these types. See Update for a complete list of available update types.
  *                         Specify an empty list to receive all updates regardless of type (default).
  *                         If not specified, the previous setting will be used.
  * @param bufferSize       Size of buffer in element count
  */
class Webhook(
    endpoint: String,
    scheme: String = "https",
    interface: String = "::0",
    port: Int = 443,
    max_connections: Option[Int] = None,
    allowed_updates: Option[List[String]] = None,
    bufferSize: Int = 10000)(implicit are: ApiRequestExecutor, system: ActorSystem, materializer: ActorMaterializer) {
  val source: Source[Update, NotUsed] =
    Source.fromGraph(
      new WebHookSource(endpoint, scheme, interface, port, max_connections, allowed_updates, bufferSize))
}

class WebHookSource(
    endpoint: String,
    scheme: String,
    interface: String,
    port: Int,
    max_connections: Option[Int],
    allowed_updates: Option[List[String]],
    bufferSize: Int)(implicit are: ApiRequestExecutor, ast: ActorSystem, materializer: ActorMaterializer)
    extends GraphStage[SourceShape[Update]]
    with CirceDecoders {

  require(bufferSize > 0, "Param `bufferSize` should be > 1.")

  import ast.dispatcher

  val out: Outlet[Update]                 = Outlet("Webhook.Updates")
  override def shape: SourceShape[Update] = SourceShape(out)

  val serverBindingP = Promise[ServerBinding]

  serverBindingP.future.onComplete {
    case Failure(ex) ⇒
      println(s"[ERROR] ${ex.getMessage}")
      sys.exit(1)
    case _ ⇒ // ignore
  }

  def route(fn: Update ⇒ Boolean): Route = path(endpoint) {
    entity(as[Update]) {
      case update if fn(update) ⇒ complete(OK)
      case _                    ⇒ reject()
    }
  }

  def http(fn: Update ⇒ Boolean): Future[ServerBinding] = {
    SetWebhook(Uri.from(scheme, host = interface, port = port, path = s"/$endpoint").toString(),
               max_connections,
               allowed_updates)
      .foldMap(are)
      .flatMap {
        case Response(true, Some(true), _, _) ⇒
          val f = Http().bindAndHandle(route(fn), "::0", port)
          serverBindingP.completeWith(f)
          f
        case response ⇒
          val f = Future.failed(new IllegalStateException(s"Can't set webhook: $response"))
          serverBindingP.completeWith(f)
          f
      }
  }

  override def createLogic(inheritedAttributes: Attributes): GraphStageLogic = new GraphStageLogic(shape) {

    val blockingQueue  = new ArrayBlockingQueue[Update](bufferSize)
    val serverBindingF = http(blockingQueue.offer)

    setHandler(
      out,
      new OutHandler {
        override def onPull(): Unit = {
          Option(blockingQueue.take()) match {
            case Some(element) ⇒ push(out, element)
            case None          ⇒ // do nothing as we waiting for the element
          }
        }

        override def onDownstreamFinish(): Unit = {
          super.onDownstreamFinish()
          serverBindingF.map(_.unbind())(ast.dispatcher)
        }
      }
    )

    override def postStop(): Unit = {
      super.postStop()
      serverBindingF.map(_.unbind())(ast.dispatcher)
    }
  }
}
