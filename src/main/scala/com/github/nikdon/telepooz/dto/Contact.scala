package com.github.nikdon.telepooz.dto

import com.github.nikdon.telepooz.tags.syntax._
import com.github.nikdon.telepooz.{ToModel, model}


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
                   last_name: Option[String] = None,
                   user_id: Option[Int] = None)

object Contact {
  implicit val toModel: ToModel[Contact, model.Contact] =
    ToModel(v ⇒ model.Contact(v.phone_number, v.first_name, v.last_name, v.user_id.map(_.userId)))
}
