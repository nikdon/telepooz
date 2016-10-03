package com.github.nikdon.telepooz.model.methods

import com.github.nikdon.telepooz.IsResourceId
import com.github.nikdon.telepooz.tags.{ChatId, MessageId, UserId}
import shapeless.tag.@@


/**
  * Use this method to get data for high score tables. Will return the score of the specified user and several of his
  * neighbors in a game. On success, returns an Array of GameHighScore objects.
  *
  * @param user_id            Target user id
  * @param chat_id            Required if inline_message_id is not specified. Unique identifier for the target chat
  *                           (or username of the target channel in the format @channelusername)
  * @param message_id         Required if inline_message_id is not specified. Unique identifier of the sent message
  * @param inline_message_id  	Required if chat_id and message_id are not specified. Identifier of the inline message
  */
case class GetGameHighScores[A: IsResourceId](
  user_id: Long @@ UserId,
  chat_id: Option[A @@ ChatId] = None,
  message_id: Option[Long @@ MessageId] = None,
  inline_message_id: Option[String @@ MessageId] = None
)
