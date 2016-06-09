package com.github.nikdon.telepooz.model

import com.github.nikdon.telepooz._


/**
  * This object contains information about one member of the chat.
  *
  * @param user   Information about the user
  * @param status The member's status in the chat. Can be “creator”, “administrator”, “member”, “left” or “kicked”
  */
case class ChatMember(user: User,
                      status: MemberStatus)

sealed trait MemberStatus extends Product with Serializable {def name: String = productPrefix}
object MemberStatus {
  case object Creator extends MemberStatus {override val name = super.name}
  case object Administrator extends MemberStatus {override val name = super.name}
  case object Member extends MemberStatus {override val name = super.name}
  case object Left extends MemberStatus {override val name = super.name}
  case object Kicked extends MemberStatus {override val name = super.name}

  def unsafe(str: String): MemberStatus = str match {
    case Creator.name       ⇒ Creator
    case Administrator.name ⇒ Administrator
    case Member.name        ⇒ Member
    case Left.name          ⇒ Left
    case Kicked.name        ⇒ Kicked
    case _                  ⇒ unexpected(str)
  }
}
