package com.github.nikdon.model

import com.github.nikdon.ToDTO.syntax._
import com.github.nikdon.{ToDTO, dto, model}


/**
  * This object represent a user's profile pictures.
  *
  * @param totalCount Total number of profile pictures the target user has
  * @param photos     Requested profile pictures (in up to 4 sizes each)
  */
case class UserProfilePhotos(totalCount: Int,
                             photos: Vector[Vector[model.PhotoSize]])

object UserProfilePhotos {
  implicit val toDTO: ToDTO[UserProfilePhotos, dto.UserProfilePhotos] =
    ToDTO(u ⇒ dto.UserProfilePhotos(u.totalCount, u.photos.map(_.map(_.toDTO))))
}
