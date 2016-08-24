package com.github.nikdon.telepooz.model.inline

import com.github.nikdon.telepooz.raw.CirceEncoders
import com.github.nikdon.telepooz.tags
import io.circe.Json
import io.circe.syntax._
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}
import com.github.nikdon.telepooz.helpers.Arbitraries._


class AnswerInlineQueryTest extends FlatSpec
                                    with Matchers
                                    with GeneratorDrivenPropertyChecks
                                    with tags.Syntax
                                    with CirceEncoders {

  "AnswerInlineQuery" should "convert to json" in {
    forAll { answerInlineQuery: AnswerInlineQuery ⇒
      answerInlineQuery.asJson should not be Json.Null
    }
  }
}
