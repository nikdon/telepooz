package com.github.nikdon.telepooz.dto

import com.github.nikdon.telepooz.{ToModel, dto, model}
import com.github.nikdon.telepooz.ToModel.syntax._


/**
  * This object represent a user's profile pictures.
  *
  * @param total_count Total number of profile pictures the target user has
  * @param photos      Requested profile pictures (in up to 4 sizes each)
  */
case class UserProfilePhotos(total_count: Int,
                             photos: Vector[Vector[dto.PhotoSize]])

object UserProfilePhotos {
  implicit val toModel: ToModel[UserProfilePhotos, model.UserProfilePhotos] =
    ToModel(u ⇒ model.UserProfilePhotos(u.total_count, u.photos.map(_.map(_.toModel))))
}
