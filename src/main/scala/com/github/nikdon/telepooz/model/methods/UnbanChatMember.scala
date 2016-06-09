package com.github.nikdon.telepooz.model.methods

import com.github.nikdon.telepooz.IsResourceId
import com.github.nikdon.telepooz.tags.{ChatId, UserId}
import shapeless.tag.@@


/**
  * Use this method to unban a previously kicked user in a supergroup. The user will not return to the group
  * automatically, but will be able to join via link, etc. The bot must be an administrator in the group for this to
  * work. Returns True on success.
  *
  * @param chat_id  Unique identifier for the target group or username of the target supergroup
  *                 (in the format @supergroupusername)
  * @param user_id  Unique identifier of the target user
  */
case class UnbanChatMember[A: IsResourceId](chat_id: A @@ ChatId,
                                            user_id: Int @@ UserId)
