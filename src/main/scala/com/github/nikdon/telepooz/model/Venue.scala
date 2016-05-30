package com.github.nikdon.telepooz.model

import com.github.nikdon.telepooz.ToDTO.syntax._
import com.github.nikdon.telepooz.tags.FoursquareId
import com.github.nikdon.telepooz.{ToDTO, dto, model}
import shapeless.tag.@@


/**
  * This object represents a venue.
  *
  * @param location     Venue location
  * @param title        Name of the venue
  * @param address      Address of the venue
  * @param foursquareId Foursquare identifier of the venue
  */
case class Venue(location: model.Location,
                 title: String,
                 address: String,
                 foursquareId: Option[String @@ FoursquareId])

object Venue {
  implicit val toDTO: ToDTO[Venue, dto.Venue] =
    ToDTO(l ⇒ dto.Venue(l.location.toDTO, l.title, l.address, l.foursquareId))
}
