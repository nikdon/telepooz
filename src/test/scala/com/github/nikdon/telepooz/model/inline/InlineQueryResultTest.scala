package com.github.nikdon.telepooz.model.inline

import com.github.nikdon.telepooz.helpers.Arbitraries._
import com.github.nikdon.telepooz.json.CirceEncoders
import io.circe.Json
import io.circe.syntax._
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}

class InlineQueryResultTest extends FlatSpec with Matchers with GeneratorDrivenPropertyChecks with CirceEncoders {

  "InlineQueryResultArticle" should "convert to json" in {
    forAll { inlineQueryResultArticle: InlineQueryResultArticle ⇒
      val js = inlineQueryResultArticle.asJson
      js should not be Json.Null
      (js \\ "type").head.as[String] shouldBe Right("article")
    }
  }

  "InlineQueryResultPhoto" should "convert to json" in {
    forAll { inlineQueryResultPhoto: InlineQueryResultPhoto ⇒
      val js = inlineQueryResultPhoto.asJson
      js should not be Json.Null
      (js \\ "type").head.as[String] shouldBe Right("photo")
    }
  }

  "InlineQueryResultGif" should "convert to json" in {
    forAll { inlineQueryResultGif: InlineQueryResultGif ⇒
      val js = inlineQueryResultGif.asJson
      js should not be Json.Null
      (js \\ "type").head.as[String] shouldBe Right("gif")
    }
  }

  "InlineQueryResultMpeg4Gif" should "convert to json" in {
    forAll { inlineQueryResultMpeg4Gif: InlineQueryResultMpeg4Gif ⇒
      val js = inlineQueryResultMpeg4Gif.asJson
      js should not be Json.Null
      (js \\ "type").head.as[String] shouldBe Right("mpeg4_gif")
    }
  }

  "InlineQueryResultVideo" should "convert to json" in {
    forAll { inlineQueryResultVideo: InlineQueryResultVideo ⇒
      val js = inlineQueryResultVideo.asJson
      js should not be Json.Null
      (js \\ "type").head.as[String] shouldBe Right("video")
    }
  }

  "InlineQueryResultAudio" should "convert to json" in {
    forAll { inlineQueryResultAudio: InlineQueryResultAudio ⇒
      val js = inlineQueryResultAudio.asJson
      js should not be Json.Null
      (js \\ "type").head.as[String] shouldBe Right("audio")
    }
  }

  "InlineQueryResultVoice" should "convert to json" in {
    forAll { inlineQueryResultVoice: InlineQueryResultVoice ⇒
      val js = inlineQueryResultVoice.asJson
      js should not be Json.Null
      (js \\ "type").head.as[String] shouldBe Right("voice")
    }
  }

  "InlineQueryResultDocument" should "convert to json" in {
    forAll { inlineQueryResultDocument: InlineQueryResultDocument ⇒
      val js = inlineQueryResultDocument.asJson
      js should not be Json.Null
      (js \\ "type").head.as[String] shouldBe Right("document")
    }
  }

  "InlineQueryResultLocation" should "convert to json" in {
    forAll { inlineQueryResultLocation: InlineQueryResultLocation ⇒
      val js = inlineQueryResultLocation.asJson
      js should not be Json.Null
      (js \\ "type").head.as[String] shouldBe Right("location")
    }
  }

  "InlineQueryResultVenue" should "convert to json" in {
    forAll { inlineQueryResultVenue: InlineQueryResultVenue ⇒
      val js = inlineQueryResultVenue.asJson
      js should not be Json.Null
      (js \\ "type").head.as[String] shouldBe Right("venue")
    }
  }

  "InlineQueryResultContact" should "convert to json" in {
    forAll { inlineQueryResultContact: InlineQueryResultContact ⇒
      val js = inlineQueryResultContact.asJson
      js should not be Json.Null
      (js \\ "type").head.as[String] shouldBe Right("contact")
    }
  }

  "InlineQueryResultCachedPhoto" should "convert to json" in {
    forAll { inlineQueryResultCachedPhoto: InlineQueryResultCachedPhoto ⇒
      val js = inlineQueryResultCachedPhoto.asJson
      js should not be Json.Null
      (js \\ "type").head.as[String] shouldBe Right("photo")
    }
  }

  "InlineQueryResultCachedGif" should "convert to json" in {
    forAll { inlineQueryResultCachedGif: InlineQueryResultCachedGif ⇒
      val js = inlineQueryResultCachedGif.asJson
      js should not be Json.Null
      (js \\ "type").head.as[String] shouldBe Right("gif")
    }
  }

  "InlineQueryResultCachedMpeg4Gif" should "convert to json" in {
    forAll { inlineQueryResultCachedMpeg4Gif: InlineQueryResultCachedMpeg4Gif ⇒
      val js = inlineQueryResultCachedMpeg4Gif.asJson
      js should not be Json.Null
      (js \\ "type").head.as[String] shouldBe Right("mpeg4_gif")
    }
  }

  "InlineQueryResultCachedSticker" should "convert to json" in {
    forAll { inlineQueryResultCachedSticker: InlineQueryResultCachedSticker ⇒
      val js = inlineQueryResultCachedSticker.asJson
      js should not be Json.Null
      (js \\ "type").head.as[String] shouldBe Right("sticker")
    }
  }

  "InlineQueryResultCachedDocument" should "convert to json" in {
    forAll { inlineQueryResultCachedDocument: InlineQueryResultCachedDocument ⇒
      val js = inlineQueryResultCachedDocument.asJson
      js should not be Json.Null
      (js \\ "type").head.as[String] shouldBe Right("document")
    }
  }

  "InlineQueryResultCachedVideo" should "convert to json" in {
    forAll { inlineQueryResultCachedVideo: InlineQueryResultCachedVideo ⇒
      val js = inlineQueryResultCachedVideo.asJson
      js should not be Json.Null
      (js \\ "type").head.as[String] shouldBe Right("video")
    }
  }

  "InlineQueryResultCachedVoice" should "convert to json" in {
    forAll { inlineQueryResultCachedVoice: InlineQueryResultCachedVoice ⇒
      val js = inlineQueryResultCachedVoice.asJson
      js should not be Json.Null
      (js \\ "type").head.as[String] shouldBe Right("voice")
    }
  }

  "InlineQueryResultCachedAudio" should "convert to json" in {
    forAll { inlineQueryResultCachedAudio: InlineQueryResultCachedAudio ⇒
      val js = inlineQueryResultCachedAudio.asJson
      js should not be Json.Null
      (js \\ "type").head.as[String] shouldBe Right("audio")
    }
  }

  "InlineQueryResultGame" should "convert to json" in {
    forAll { inlineQueryResultGame: InlineQueryResultGame ⇒
      val js = inlineQueryResultGame.asJson
      js should not be Json.Null
      (js \\ "type").head.as[String] shouldBe Right("game")
    }
  }
}
