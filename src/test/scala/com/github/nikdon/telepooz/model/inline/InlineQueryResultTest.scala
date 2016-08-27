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

  "InlineQueryResultLocation" should "convert to json" in {
    forAll { inlineQueryResultLocation: InlineQueryResultLocation ⇒
      inlineQueryResultLocation.asJson should not be Json.Null
    }
  }

  "InlineQueryResultVenue" should "convert to json" in {
    forAll { inlineQueryResultVenue: InlineQueryResultVenue ⇒
      inlineQueryResultVenue.asJson should not be Json.Null
    }
  }

  "InlineQueryResultContact" should "convert to json" in {
    forAll { inlineQueryResultContact: InlineQueryResultContact ⇒
      inlineQueryResultContact.asJson should not be Json.Null
    }
  }

  "InlineQueryResultCachedPhoto" should "convert to json" in {
    forAll { inlineQueryResultCachedPhoto: InlineQueryResultCachedPhoto ⇒
      inlineQueryResultCachedPhoto.asJson should not be Json.Null
    }
  }

  "InlineQueryResultCachedGif" should "convert to json" in {
    forAll { inlineQueryResultCachedGif: InlineQueryResultCachedGif ⇒
      inlineQueryResultCachedGif.asJson should not be Json.Null
    }
  }

  "InlineQueryResultCachedMpeg4Gif" should "convert to json" in {
    forAll { inlineQueryResultCachedMpeg4Gif: InlineQueryResultCachedMpeg4Gif ⇒
      inlineQueryResultCachedMpeg4Gif.asJson should not be Json.Null
    }
  }

  "InlineQueryResultCachedSticker" should "convert to json" in {
    forAll { inlineQueryResultCachedSticker: InlineQueryResultCachedSticker ⇒
      inlineQueryResultCachedSticker.asJson should not be Json.Null
    }
  }

  "InlineQueryResultCachedDocument" should "convert to json" in {
    forAll { inlineQueryResultCachedDocument: InlineQueryResultCachedDocument ⇒
      inlineQueryResultCachedDocument.asJson should not be Json.Null
    }
  }
}
