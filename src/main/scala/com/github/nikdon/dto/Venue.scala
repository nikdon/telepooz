package com.github.nikdon.dto

import com.github.nikdon.ToModel.syntax._
import com.github.nikdon.tags.syntax._
import com.github.nikdon.{ToModel, dto, model}


/**
  * This object represents a venue.
  *
  * @param location     Venue location
  * @param title        Name of the venue
  * @param address      Address of the venue
  * @param foursquareId Foursquare identifier of the venue
  */
case class Venue(location: dto.Location,
                 title: String,
                 address: String,
                 foursquareId: Option[String])

object Venue {
  implicit val toModel: ToModel[Venue, model.Venue] =
    ToModel(v ⇒ model.Venue(v.location.toModel, v.title, v.address, v.foursquareId.map(_.foursquareId)))
}
