package com.github.nikdon

import com.github.nikdon.model.ChatType
import com.github.nikdon.tags.syntax._


trait ToModel[DTO, Model] extends Produce[DTO, Model]


object ToModel {

  def apply[DTO, Model](f: DTO ⇒ Model): ToModel[DTO, Model] = new ToModel[DTO, Model] {
    override def produce(a: DTO): Model = f(a)
  }

  object syntax {

    implicit class ToModelSyntaxOps[DTO, Model](a: DTO)(implicit F: ToModel[DTO, Model]) {
      def toModel: Model = F.produce(a)
    }

  }

  implicit val chatToModel: ToModel[dto.Chat, model.Chat] =
    ToModel(c ⇒ model.Chat(c.id.chatId, ChatType.unsafe(c.`type`), c.title, c.userName, c.firstName, c.lastName))

  implicit val photoSizeToModel: ToModel[dto.PhotoSize, model.PhotoSize] =
    ToModel(p ⇒ model.PhotoSize(p.fileId.fileId, p.width, p.height, p.fileSize))

  implicit val userToModel: ToModel[dto.User, model.User] =
    ToModel(u ⇒ model.User(u.id.userId, u.firstName, u.lastName, u.userName))
}
