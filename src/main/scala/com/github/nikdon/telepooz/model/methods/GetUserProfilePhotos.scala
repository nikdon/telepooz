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

package com.github.nikdon.telepooz.model.methods

import com.github.nikdon.telepooz.model.{Response, UserProfilePhotos}

/**
  * Use this method to get a list of profile pictures for a user. Returns a UserProfilePhotos object.
  *
  * @param user_id  Unique identifier of the target user
  * @param offset   Sequential number of the first photo to be returned. By default, all photos are returned.
  * @param limit    Limits the number of photos to be retrieved. Values between 1—100 are accepted. Defaults to 100.
  */
case class GetUserProfilePhotos(
    user_id: Int,
    offset: Option[Int] = None,
    limit: Option[Int] = None
) extends Method[Response[UserProfilePhotos]]
