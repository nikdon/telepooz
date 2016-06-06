package com.github.nikdon.telepooz.model

import com.github.nikdon.telepooz.tags.UserId
import shapeless.tag.@@


/**
  * This object represents a phone contact.
  *
  * @param phone_number  Contact's phone number
  * @param first_name    Contact's first name
  * @param last_name     Contact's last name
  * @param user_id       Contact's user identifier in Telegram
  */
case class Contact(phone_number: String,
                   first_name: String,
                   last_name: Option[String],
                   user_id: Option[Int @@ UserId])
