package com.github.nikdon.telepooz.model.methods

import com.github.nikdon.telepooz.IsResourceId
import com.github.nikdon.telepooz.tags.ChatId
import shapeless.tag._


/**
  * Use this method to get up to date information about the chat (current name of the user for one-on-one conversations,
  * current username of a user, group or channel, etc.). Returns a Chat object on success.
  *
  * @param chat_id  Unique identifier for the target chat or username of the target supergroup or channel
  *                 (in the format @channelusername)
  */
case class GetChat[A: IsResourceId](chat_id: A @@ ChatId)