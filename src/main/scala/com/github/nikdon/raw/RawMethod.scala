package com.github.nikdon.raw

import com.github.nikdon.dto
import io.circe.Json


sealed trait RawMethod[Result] extends Product with Serializable {
  def name: String = this.productPrefix
  def json: Json
}

object RawMethod {
  case object GetMe                     extends RawMethod[dto.User]    { override val json = Json.Null }
  case class SendMessage(json: Json)    extends RawMethod[dto.Message]
  case class ForwardMessage(json: Json) extends RawMethod[dto.Message]
}
