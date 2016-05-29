package com.github.nikdon.raw

import com.github.nikdon.dto
import io.circe.Json


sealed trait RawCommand[Result] extends Product with Serializable {
  def name: String = this.productPrefix
  def json: Json
}

object RawCommand {
  case object GetMe                  extends RawCommand[dto.User]    { override val json = Json.Null }
  case class SendMessage(json: Json) extends RawCommand[dto.Message]
}


sealed trait Method[Result] extends Product with Serializable {
  def name: String = this.productPrefix
}
