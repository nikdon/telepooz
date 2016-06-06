package com.github.nikdon.telepooz.model

import com.github.nikdon.telepooz.tags
import shapeless.tag.@@


/**
  * This object represents a Telegram user or bot.
  *
  * @param id         Unique identifier for this user or bot
  * @param first_name User‘s or bot’s first name
  * @param last_name  User‘s or bot’s last name
  * @param username   User‘s or bot’s username
  */
case class User(id: Int @@ tags.UserId,
                first_name: String,
                last_name: Option[String] = None,
                username: Option[String] = None)
