package com.github.nikdon.telepooz.model

import com.github.nikdon.telepooz.{ToDTO, dto}


/**
  * This object represents a point on the map.
  *
  * @param longitude  Longitude as defined by sender
  * @param latitude   Latitude as defined by sender
  */
case class Location(longitude: Double,
                    latitude: Double)

object Location {
  implicit val toDTO: ToDTO[Location, dto.Location] =
    ToDTO(l ⇒ dto.Location(l.longitude, l.latitude))
}
