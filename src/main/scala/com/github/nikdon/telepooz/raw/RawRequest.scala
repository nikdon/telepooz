package com.github.nikdon.telepooz.raw

import com.github.nikdon.telepooz.dto
import io.circe.Json


sealed trait RawRequest[Result] extends Product with Serializable {
  def name: String = this.productPrefix
  def json: Json
}

object RawRequest {
  case object GetMe                     extends RawRequest[dto.User]    { override val json = Json.Null }
  case class SendMessage(json: Json)    extends RawRequest[dto.Message]
  case class ForwardMessage(json: Json) extends RawRequest[dto.Message]
}
