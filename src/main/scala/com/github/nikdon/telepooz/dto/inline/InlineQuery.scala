package com.github.nikdon.telepooz.dto.inline

import com.github.nikdon.telepooz.ToModel
import com.github.nikdon.telepooz.ToModel.syntax._
import com.github.nikdon.telepooz.model
import com.github.nikdon.telepooz.tags.syntax._
import com.github.nikdon.telepooz.dto.{Location, User}


/**
  * This object represents an incoming inline query. When the user sends an empty query,
  * your bot could return some default or trending results.
  *
  * @param id       Unique identifier for this query
  * @param from     Sender
  * @param location Sender location, only for bots that request user location
  * @param query    Text of the query (up to 512 characters)
  * @param offset   Offset of the results to be returned, can be controlled by the bot
  */
case class InlineQuery(id: String,
                       from: User,
                       location: Option[Location],
                       query: String,
                       offset: String)

object InlineQuery {
  implicit val toModel: ToModel[InlineQuery, model.inline.InlineQuery] =
    ToModel(i ⇒ model.inline.InlineQuery(i.id.queryId, i.from.toModel, i.location.map(_.toModel), i.query, i.offset))
}
