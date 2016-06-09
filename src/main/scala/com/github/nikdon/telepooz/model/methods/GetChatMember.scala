package com.github.nikdon.telepooz.model.methods

import com.github.nikdon.telepooz.IsResourceId
import com.github.nikdon.telepooz.tags.{ChatId, UserId}
import shapeless.tag.@@


/**
  * Use this method to get information about a member of a chat. Returns a ChatMember object on success.
  *
  * @param chat_id  Unique identifier for the target group or username of the target supergroup
  *                 (in the format @supergroupusername)
  * @param user_id  Unique identifier of the target user
  */
case class GetChatMember[A: IsResourceId](chat_id: A @@ ChatId,
                                          user_id: Int @@ UserId)
