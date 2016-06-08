package com.github.nikdon.telepooz.model.inline


/**
  * Represents the content of a location message to be sent as the result of an inline query.
  *
  * @param latitude   Latitude of the location in degrees
  * @param longitude  Longitude of the location in degrees
  */
case class InputLocationMessageContent(latitude: Double,
                                       longitude: Double)
