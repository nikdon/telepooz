package com.github.nikdon.dto

import com.github.nikdon.tags.syntax._
import com.github.nikdon.{ToModel, dto, model}


/**
  * This object represents a Telegram user or bot.
  *
  * @param id        Unique identifier for this user or bot
  * @param firstName User‘s or bot’s first name
  * @param lastName  User‘s or bot’s last name
  * @param userName  User‘s or bot’s username
  */
case class User(id: Int,
                firstName: String,
                lastName: Option[String],
                userName: Option[String])

object User {
  implicit val userToModel: ToModel[dto.User, model.User] =
    ToModel(u ⇒ model.User(u.id.userId, u.firstName, u.lastName, u.userName))
}
