package com.github.nikdon.telepooz.dto

import com.github.nikdon.telepooz.{ToModel, model}


/**
  * This object represents a point on the map.
  *
  * @param longitude  Longitude as defined by sender
  * @param latitude   Latitude as defined by sender
  */
case class Location(longitude: Double,
                    latitude: Double)

object Location {
  implicit val toModel: ToModel[Location, model.Location] =
    ToModel(v ⇒ model.Location(v.longitude, v.latitude))
}
