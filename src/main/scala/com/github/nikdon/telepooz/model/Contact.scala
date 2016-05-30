package com.github.nikdon.telepooz.model

import com.github.nikdon.telepooz.tags.UserId
import shapeless.tag.@@


/**
  * This object represents a phone contact.
  *
  * @param phoneNumber  Contact's phone number
  * @param firstName    Contact's first name
  * @param lastName     Contact's last name
  * @param userId       Contact's user identifier in Telegram
  */
case class Contact(phoneNumber: String,
                   firstName: String,
                   lastName: Option[String],
                   userId: Option[Int @@ UserId])
