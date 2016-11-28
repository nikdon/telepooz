/*
 * Copyright 2016 Nikolay Donets
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.nikdon.telepooz.model

import com.github.nikdon.telepooz.model
import com.github.nikdon.telepooz.tags.FoursquareId
import shapeless.tag.@@


/**
  * This object represents a venue.
  *
  * @param location       Venue location
  * @param title          Name of the venue
  * @param address        Address of the venue
  * @param foursquare_id  Foursquare identifier of the venue
  */
case class Venue(location: model.Location,
                 title: String,
                 address: String,
                 foursquare_id: Option[String @@ FoursquareId])
