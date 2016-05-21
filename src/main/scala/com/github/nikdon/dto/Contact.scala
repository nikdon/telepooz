package com.github.nikdon.dto

import com.github.nikdon.tags.syntax._
import com.github.nikdon.{ToModel, model}


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
                   userId: Option[Int])

object Contact {
  implicit val toModel: ToModel[Contact, model.Contact] =
    ToModel(v ⇒ model.Contact(v.phoneNumber, v.firstName, v.lastName, v.userId.map(_.userId)))
}
