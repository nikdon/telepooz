package com.github.nikdon.telepooz.model

import com.github.nikdon.telepooz.{ToDTO, dto, model, tags}
import shapeless.tag.@@


/**
  * This object represents a Telegram user or bot.
  *
  * @param id        Unique identifier for this user or bot
  * @param firstName User‘s or bot’s first name
  * @param lastName  User‘s or bot’s last name
  * @param userName  User‘s or bot’s username
  */
case class User(id: Int @@ tags.UserId,
                firstName: String,
                lastName: Option[String],
                userName: Option[String])

object User {
  implicit val userToDTO: ToDTO[model.User, dto.User] =
    ToDTO(u ⇒ dto.User(u.id, u.firstName, u.lastName, u.userName))
}
