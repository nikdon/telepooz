package com.github.nikdon.telepooz.model.methods

import com.github.nikdon.telepooz.IsResourceId
import com.github.nikdon.telepooz.tags.ChatId
import shapeless.tag.@@


/**
  * Use this method to get the number of members in a chat. Returns Int on success.
  *
  * @param chat_id  Unique identifier for the target chat or username of the target supergroup or channel
  *                 (in the format @channelusername)
  */
case class GetChatMembersCount[A: IsResourceId](chat_id: A @@ ChatId)
