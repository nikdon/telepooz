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
    forAll { inlineQueryResultArticle: InlineQueryResultArticle ⇒
      val js = inlineQueryResultArticle.asJson
      js should not be Json.Null
      js.cursor.downField("type").flatMap(_.focus.asString) shouldBe Some("article")
    }
  }

  "InlineQueryResultPhoto" should "convert to json" in {
    forAll { inlineQueryResultPhoto: InlineQueryResultPhoto ⇒
      val js = inlineQueryResultPhoto.asJson
      js should not be Json.Null
      js.cursor.downField("type").flatMap(_.focus.asString) shouldBe Some("photo")
    }
  }

  "InlineQueryResultGif" should "convert to json" in {
    forAll { inlineQueryResultGif: InlineQueryResultGif ⇒
      val js = inlineQueryResultGif.asJson
      js should not be Json.Null
      js.cursor.downField("type").flatMap(_.focus.asString) shouldBe Some("gif")
    }
  }

  "InlineQueryResultMpeg4Gif" should "convert to json" in {
    forAll { inlineQueryResultMpeg4Gif: InlineQueryResultMpeg4Gif ⇒
      val js = inlineQueryResultMpeg4Gif.asJson
      js should not be Json.Null
      js.cursor.downField("type").flatMap(_.focus.asString) shouldBe Some("mpeg4_gif")
    }
  }

  "InlineQueryResultVideo" should "convert to json" in {
    forAll { inlineQueryResultVideo: InlineQueryResultVideo ⇒
      val js = inlineQueryResultVideo.asJson
      js should not be Json.Null
      js.cursor.downField("type").flatMap(_.focus.asString) shouldBe Some("video")
    }
  }

  "InlineQueryResultAudio" should "convert to json" in {
    forAll { inlineQueryResultAudio: InlineQueryResultAudio ⇒
      val js = inlineQueryResultAudio.asJson
      js should not be Json.Null
      js.cursor.downField("type").flatMap(_.focus.asString) shouldBe Some("audio")
    }
  }

  "InlineQueryResultVoice" should "convert to json" in {
    forAll { inlineQueryResultVoice: InlineQueryResultVoice ⇒
      val js = inlineQueryResultVoice.asJson
      js should not be Json.Null
      js.cursor.downField("type").flatMap(_.focus.asString) shouldBe Some("voice")
    }
  }

  "InlineQueryResultDocument" should "convert to json" in {
    forAll { inlineQueryResultDocument: InlineQueryResultDocument ⇒
      val js = inlineQueryResultDocument.asJson
      js should not be Json.Null
      js.cursor.downField("type").flatMap(_.focus.asString) shouldBe Some("document")
    }
  }

  "InlineQueryResultLocation" should "convert to json" in {
    forAll { inlineQueryResultLocation: InlineQueryResultLocation ⇒
      val js = inlineQueryResultLocation.asJson
      js should not be Json.Null
      js.cursor.downField("type").flatMap(_.focus.asString) shouldBe Some("location")
    }
  }

  "InlineQueryResultVenue" should "convert to json" in {
    forAll { inlineQueryResultVenue: InlineQueryResultVenue ⇒
      val js = inlineQueryResultVenue.asJson
      js should not be Json.Null
      js.cursor.downField("type").flatMap(_.focus.asString) shouldBe Some("venue")
    }
  }

  "InlineQueryResultContact" should "convert to json" in {
    forAll { inlineQueryResultContact: InlineQueryResultContact ⇒
      val js = inlineQueryResultContact.asJson
      js should not be Json.Null
      js.cursor.downField("type").flatMap(_.focus.asString) shouldBe Some("contact")
    }
  }

  "InlineQueryResultCachedPhoto" should "convert to json" in {
    forAll { inlineQueryResultCachedPhoto: InlineQueryResultCachedPhoto ⇒
      val js = inlineQueryResultCachedPhoto.asJson
      js should not be Json.Null
      js.cursor.downField("type").flatMap(_.focus.asString) shouldBe Some("photo")
    }
  }

  "InlineQueryResultCachedGif" should "convert to json" in {
    forAll { inlineQueryResultCachedGif: InlineQueryResultCachedGif ⇒
      val js = inlineQueryResultCachedGif.asJson
      js should not be Json.Null
      js.cursor.downField("type").flatMap(_.focus.asString) shouldBe Some("gif")
    }
  }

  "InlineQueryResultCachedMpeg4Gif" should "convert to json" in {
    forAll { inlineQueryResultCachedMpeg4Gif: InlineQueryResultCachedMpeg4Gif ⇒
      val js = inlineQueryResultCachedMpeg4Gif.asJson
      js should not be Json.Null
      js.cursor.downField("type").flatMap(_.focus.asString) shouldBe Some("mpeg4_gif")
    }
  }

  "InlineQueryResultCachedSticker" should "convert to json" in {
    forAll { inlineQueryResultCachedSticker: InlineQueryResultCachedSticker ⇒
      val js = inlineQueryResultCachedSticker.asJson
      js should not be Json.Null
      js.cursor.downField("type").flatMap(_.focus.asString) shouldBe Some("sticker")
    }
  }

  "InlineQueryResultCachedDocument" should "convert to json" in {
    forAll { inlineQueryResultCachedDocument: InlineQueryResultCachedDocument ⇒
      val js = inlineQueryResultCachedDocument.asJson
      js should not be Json.Null
      js.cursor.downField("type").flatMap(_.focus.asString) shouldBe Some("document")
    }
  }

  "InlineQueryResultCachedVideo" should "convert to json" in {
    forAll { inlineQueryResultCachedVideo: InlineQueryResultCachedVideo ⇒
      val js = inlineQueryResultCachedVideo.asJson
      js should not be Json.Null
      js.cursor.downField("type").flatMap(_.focus.asString) shouldBe Some("video")
    }
  }

  "InlineQueryResultCachedVoice" should "convert to json" in {
    forAll { inlineQueryResultCachedVoice: InlineQueryResultCachedVoice ⇒
      val js = inlineQueryResultCachedVoice.asJson
      js should not be Json.Null
      js.cursor.downField("type").flatMap(_.focus.asString) shouldBe Some("voice")
    }
  }

  "InlineQueryResultCachedAudio" should "convert to json" in {
    forAll { inlineQueryResultCachedAudio: InlineQueryResultCachedAudio ⇒
      val js = inlineQueryResultCachedAudio.asJson
      js should not be Json.Null
      js.cursor.downField("type").flatMap(_.focus.asString) shouldBe Some("audio")
    }
  }
}
