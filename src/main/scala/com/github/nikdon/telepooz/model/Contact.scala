package com.github.nikdon.telepooz.model

import com.github.nikdon.telepooz.tags.UserId
import com.github.nikdon.telepooz.{ToDTO, dto}
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

object Contact {
  implicit val toDTO: ToDTO[Contact, dto.Contact] =
    ToDTO(v ⇒ dto.Contact(v.phoneNumber, v.firstName, v.lastName, v.userId))
}
