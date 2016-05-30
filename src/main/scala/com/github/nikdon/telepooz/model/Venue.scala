package com.github.nikdon.telepooz.model

import com.github.nikdon.telepooz.model
import com.github.nikdon.telepooz.tags.FoursquareId
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
