package com.github.nikdon

import com.github.nikdon.tags.UserId
import org.scalacheck.Arbitrary.arbitrary
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}
import shapeless.tag
import shapeless.test.illTyped


class TagsSyntaxSpec extends FlatSpec
                             with Matchers
                             with GeneratorDrivenPropertyChecks
                             with tags.Syntax {

  behavior of "tags conversions"

  it should "do work for resource ids" in {
    forAll(arbitrary[Int]) { i ⇒
      i.userId shouldBe tag[UserId](i)
    }
  }

  it should "do not work for non resource ids" in {
    illTyped(""" "a".userId """)
  }

}
