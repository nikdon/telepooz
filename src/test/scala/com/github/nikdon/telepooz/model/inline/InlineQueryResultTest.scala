package com.github.nikdon.telepooz.model.inline

import com.github.nikdon.telepooz.helpers.Arbitraries._
import com.github.nikdon.telepooz.raw.CirceEncoders
import io.circe.Json
import io.circe.syntax._
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}


class InlineQueryResultTest extends FlatSpec
                                    with Matchers
                                    with GeneratorDrivenPropertyChecks
                                    with CirceEncoders {

  "InlineQueryResultArticle" should "convert to json" in {
    forAll { inlineQueryResultArticle: InputVenueMessageContent ⇒
      inlineQueryResultArticle.asJson should not be Json.Null
    }
  }

  "InlineQueryResultPhoto" should "convert to json" in {
    forAll { inlineQueryResultPhoto: InlineQueryResultPhoto ⇒
      inlineQueryResultPhoto.asJson should not be Json.Null
    }
  }

  "InlineQueryResultGif" should "convert to json" in {
    forAll { inlineQueryResultGif: InlineQueryResultGif ⇒
      inlineQueryResultGif.asJson should not be Json.Null
    }
  }

  "InlineQueryResultMpeg4Gif" should "convert to json" in {
    forAll { inlineQueryResultMpeg4Gif: InlineQueryResultMpeg4Gif ⇒
      inlineQueryResultMpeg4Gif.asJson should not be Json.Null
    }
  }

  "InlineQueryResultVideo" should "convert to json" in {
    forAll { inlineQueryResultVideo: InlineQueryResultVideo ⇒
      inlineQueryResultVideo.asJson should not be Json.Null
    }
  }

  "InlineQueryResultAudio" should "convert to json" in {
    forAll { inlineQueryResultAudio: InlineQueryResultAudio ⇒
      inlineQueryResultAudio.asJson should not be Json.Null
    }
  }

  "InlineQueryResultVoice" should "convert to json" in {
    forAll { inlineQueryResultVoice: InlineQueryResultVoice ⇒
      inlineQueryResultVoice.asJson should not be Json.Null
    }
  }

  "InlineQueryResultDocument" should "convert to json" in {
    forAll { inlineQueryResultDocument: InlineQueryResultDocument ⇒
      inlineQueryResultDocument.asJson should not be Json.Null
    }
  }
}
