package com.github.nikdon.telepooz.model.inline

import com.github.nikdon.telepooz.tags.FoursquareId
import shapeless.tag.@@


/**
  * Represents the content of a venue message to be sent as the result of an inline query.
  *
  * @param latitude       Latitude of the venue in degrees
  * @param longitude      Longitude of the venue in degrees
  * @param title          Name of the venue
  * @param address        Address of the venue
  * @param foursquare_id  Foursquare identifier of the venue, if known
  */
case class InputVenueMessageContent(latitude: Double,
                                    longitude: Double,
                                    title: String,
                                    address: String,
                                    foursquare_id: Option[String @@ FoursquareId])
