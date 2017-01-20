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

import akka.NotUsed
import akka.event.LoggingAdapter
import akka.stream._
import akka.stream.scaladsl.{Broadcast, Flow, GraphDSL, Merge, Source, ZipWith}
import cats.implicits._
import com.github.nikdon.telepooz.api
import com.github.nikdon.telepooz.model.{Update, methods}
import com.typesafe.config.{Config, ConfigFactory}

import scala.collection.immutable
import scala.concurrent.ExecutionContextExecutor
import scala.concurrent.duration._


class Polling(implicit apiRequestExecutor: ApiRequestExecutor, ec: ExecutionContextExecutor, logger: LoggingAdapter) {

  private[this] val config: Config           = ConfigFactory.load()
  private[this] val interval: FiniteDuration = config.getInt("telegram.polling.interval").millis
  private[this] val limit: Int               = config.getInt("telegram.polling.limit")
  private[this] val parallelism: Int         = config.getInt("telegram.polling.parallelism")

  val pollGraph: Graph[SourceShape[Update], NotUsed] = GraphDSL.create() { implicit builder ⇒
    import GraphDSL.Implicits._

    case object Trigger

    /** Emits the first [[methods.GetUpdates]] method */
    val A: Outlet[methods.GetUpdates] = builder.add(Source.single(methods.GetUpdates())).out

    /** Emits a [[Trigger]] every n milliseconds */
    val B: Outlet[Trigger.type] = builder.add(Source.tick(interval, interval, Trigger)).out

    /**
      * Finds the last [[Update]] in the incoming collection and produce a new [[methods.GetUpdates]]
      * with obtained update_id. If collection of [[Update]] is empty, produce a default [[methods.GetUpdates]]
      * entity
      */
    val C: FlowShape[immutable.Seq[Update], methods.GetUpdates] = builder.add(Flow[immutable.Seq[Update]].map {
      case Seq() ⇒
        logger.debug("Received empty array of Updates")
        methods.GetUpdates()
      case updates ⇒
        logger.debug(s"Received ${updates.length} updates: ${updates.map(_.update_id).mkString(", ")}")
        val lastUpdate  = updates.maxBy(_.update_id.toInt)
        val ack = lastUpdate.update_id + 1
        methods.GetUpdates(Some(ack), Some(limit))
    })

    /** Execute a [[methods.GetUpdates]] via the provided api */
    val D: FlowShape[methods.GetUpdates, immutable.Seq[Update]] = builder.add(Flow[methods.GetUpdates].mapAsync(parallelism) {
      gu: methods.GetUpdates ⇒
        logger.debug(s"Executing a GetUpdates request: $gu")
        val res = api.execute(gu).foldMap(apiRequestExecutor).map(_.result.fold(Vector.empty[Update])(identity))
        res
    })

    /** Merge two input streams of [[methods.GetUpdates]]. Created because of the initial producer [[A]] */
    val F: UniformFanInShape[methods.GetUpdates, methods.GetUpdates] = builder.add(Merge[methods.GetUpdates](2))

    /** Broadcasts received collection of [[Update]] */
    val E: UniformFanOutShape[immutable.Seq[Update], immutable.Seq[Update]] =
      builder.add(Broadcast[immutable.Seq[Update]](2))

    /** Emits a [[methods.GetUpdates]] when [[Trigger]] comes to input */
    val Z = builder.add(ZipWith((tick: methods.GetUpdates, trigger: Trigger.type) ⇒ {
      logger.debug(s"Trigger pulled: $tick")
      tick
    }))

    /** Flatten an incoming collection of [[Update]] */
    val Y: FlowShape[immutable.Seq[Update], Update] = builder.add(Flow[immutable.Seq[Update]].mapConcat(identity))

             C ~> Z.in0
        B ~>      Z.in1
                  Z.out ~> F
    A          ~>          F ~> D ~> E ~> Y
             C           <~          E

    SourceShape(Y.out)
  }
}
