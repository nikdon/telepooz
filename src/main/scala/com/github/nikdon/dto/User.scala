package com.github.nikdon.dto

import com.github.nikdon.tags.syntax._
import com.github.nikdon.{ToModel, dto, model}


/**
  * This object represents a Telegram user or bot.
  *
  * @param id         Unique identifier for this user or bot
  * @param first_name User‘s or bot’s first name
  * @param last_name  User‘s or bot’s last name
  * @param user_name  User‘s or bot’s username
  */
case class User(id: Int,
                first_name: String,
                last_name: Option[String],
                user_name: Option[String])

object User {
  implicit val userToModel: ToModel[dto.User, model.User] =
    ToModel(u ⇒ model.User(u.id.userId, u.first_name, u.last_name, u.user_name))
}
