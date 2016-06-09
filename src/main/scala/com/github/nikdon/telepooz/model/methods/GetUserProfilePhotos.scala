package com.github.nikdon.telepooz.model.methods

import com.github.nikdon.telepooz.tags.UserId
import shapeless.tag.@@


/**
  * Use this method to get a list of profile pictures for a user. Returns a UserProfilePhotos object.
  *
  * @param user_id  Unique identifier of the target user
  * @param offset   Sequential number of the first photo to be returned. By default, all photos are returned.
  * @param limit    Limits the number of photos to be retrieved. Values between 1—100 are accepted. Defaults to 100.
  */
case class GetUserProfilePhotos(user_id: Int @@ UserId,
                                offset: Option[Int] = None,
                                limit: Option[Int] = None)
