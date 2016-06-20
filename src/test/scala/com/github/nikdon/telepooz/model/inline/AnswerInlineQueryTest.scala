package com.github.nikdon.telepooz.model.inline

import com.github.nikdon.telepooz.raw.CirceEncoders
import com.github.nikdon.telepooz.tags
import io.circe.Json
import io.circe.syntax._
import org.scalacheck.Arbitrary._
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}


class AnswerInlineQueryTest extends FlatSpec
                                    with Matchers
                                    with GeneratorDrivenPropertyChecks
                                    with tags.Syntax
                                    with CirceEncoders {
  behavior of "AnswerInlineQuery"

  it should "convert to json" in {
    forAll(AnswerInlineQueryTest.answerInlineQueryGen) { answerInlineQuery ⇒
      answerInlineQuery.asJson should not be Json.Null
    }
  }
}

object AnswerInlineQueryTest extends tags.Syntax {
  val answerInlineQueryGen = for {
    inline_query_id ← arbitrary[String].map(_.queryId)
    // results ← TODO
    cache_time ← arbitrary[Option[Int]]
    is_personal ← arbitrary[Option[Boolean]]
    next_offset ← arbitrary[Option[String]]
    switch_pm_text ← arbitrary[Option[String]]
    switch_pm_parameter ← arbitrary[Option[String]]
  } yield AnswerInlineQuery(inline_query_id,
                            results = Vector.empty,
                            cache_time,
                            is_personal,
                            next_offset,
                            switch_pm_text,
                            switch_pm_parameter)
}
