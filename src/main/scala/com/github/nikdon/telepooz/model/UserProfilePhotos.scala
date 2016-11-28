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


/**
  * This object represent a user's profile pictures.
  *
  * @param total_count  Total number of profile pictures the target user has
  * @param photos       Requested profile pictures (in up to 4 sizes each)
  */
case class UserProfilePhotos(total_count: Int,
                             photos: Vector[Vector[model.PhotoSize]])
