package com.github.nikdon.dto

import com.github.nikdon.{ToModel, dto, model}
import com.github.nikdon.ToModel.syntax._


/**
  * This object represent a user's profile pictures.
  *
  * @param totalCount Total number of profile pictures the target user has
  * @param photos     Requested profile pictures (in up to 4 sizes each)
  */
case class UserProfilePhotos(totalCount: Int,
                             photos: Vector[Vector[dto.PhotoSize]])

object UserProfilePhotos {
  implicit val toModel: ToModel[UserProfilePhotos, model.UserProfilePhotos] =
    ToModel(u ⇒ model.UserProfilePhotos(u.totalCount, u.photos.map(_.map(_.toModel))))
}
