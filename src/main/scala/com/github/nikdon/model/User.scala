package com.github.nikdon.model

import com.github.nikdon.tags
import shapeless.tag.@@


/**
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
