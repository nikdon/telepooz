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
