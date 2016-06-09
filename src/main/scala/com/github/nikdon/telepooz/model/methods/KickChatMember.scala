package com.github.nikdon.telepooz.model.methods

import com.github.nikdon.telepooz.IsResourceId
import com.github.nikdon.telepooz.tags.{ChatId, UserId}
import shapeless.tag.@@


/**
  * Use this method to kick a user from a group or a supergroup. In the case of supergroups, the user will not be able
  * to return to the group on their own using invite links, etc., unless unbanned first. The bot must be an
  * administrator in the group for this to work. Returns True on success.
  *
  * @param chat_id  Unique identifier for the target group or username of the target supergroup
  *                 (in the format @supergroupusername)
  * @param user_id  Unique identifier of the target user
  */
case class KickChatMember[A: IsResourceId](chat_id: A @@ ChatId,
                                           user_id: Int @@ UserId)
