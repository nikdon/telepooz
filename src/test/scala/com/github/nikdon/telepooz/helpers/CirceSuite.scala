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

package com.github.nikdon.telepooz.helpers

import cats.Eq
import io.circe.testing.CodecTests
import io.circe.{Decoder, Encoder, Json}
import io.circe.syntax._
import org.scalacheck.Arbitrary
import org.scalatest.FlatSpec
import org.scalatest.prop.{Checkers, GeneratorDrivenPropertyChecks}
import org.typelevel.discipline.Laws

trait CirceSuite extends FlatSpec with GeneratorDrivenPropertyChecks {
  def checkLaws(name: String, ruleSet: Laws#RuleSet): Unit = ruleSet.all.properties.zipWithIndex.foreach {
    case ((id, prop), 0) => name should s"obey $id" in Checkers.check(prop)
    case ((id, prop), _) => it should s"obey $id" in Checkers.check(prop)
  }

  def checkCodec[T: Arbitrary: Eq: Decoder: Encoder](name: String): Unit =
    CodecTests[T].codec.all.properties.zipWithIndex.foreach {
      case ((id, prop), 0) => name should s"obey $id" in Checkers.check(prop)
      case ((id, prop), _) => it should s"obey $id" in Checkers.check(prop)
    }

  def checkEncodeIsNotNull[T: Arbitrary: Eq: Encoder](name: String): Unit =
    name should s"not be a null json" in forAll { sample: T =>
      assert(sample.asJson !== Json.Null)
    }

  def checkEncodeIsNull[T: Arbitrary: Eq: Encoder](name: String): Unit =
    name should s"not be a null json" in forAll { sample: T =>
      assert(sample.asJson === Json.Null)
    }
}
