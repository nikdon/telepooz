package com.github.nikdon.telepooz.model.methods

import com.github.nikdon.telepooz.raw.CirceEncoders
import com.github.nikdon.telepooz.tags
import io.circe.Json
import io.circe.syntax._
import org.scalacheck.Arbitrary.arbitrary
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}


class AnswerCallbackQueryTest extends FlatSpec
                                 with Matchers
                                 with GeneratorDrivenPropertyChecks
                                 with tags.Syntax
                                 with CirceEncoders {
  behavior of "SendChatAction"

  it should "convert to json" in {
    forAll(AnswerCallbackQueryTest.answerCallbackQueryGen) { answerCallbackQuery ⇒
      answerCallbackQuery.asJson should not be Json.Null
    }
  }
}

object AnswerCallbackQueryTest extends tags.Syntax {
  val answerCallbackQueryGen = for {
    callbackQueryId ← arbitrary[String].map(_.queryId)
    text ← arbitrary[Option[String]]
    showAlert ← arbitrary[Option[Boolean]]
  } yield AnswerCallbackQuery(callbackQueryId, text, showAlert)
}
