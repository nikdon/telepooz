package com.github.nikdon.telepooz.model.inline


/**
  * Represents the content of a contact message to be sent as the result of an inline query.
  *
  * @param phone_number Contact's phone number
  * @param first_name   Contact's first name
  * @param last_name    Contact's last name
  */
case class InputContactMessageContent(phone_number: String,
                                      first_name: String,
                                      last_name: Option[String] = None)
