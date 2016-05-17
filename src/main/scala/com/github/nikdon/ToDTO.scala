package com.github.nikdon


trait ToDTO[Model, DTO] extends Produce[Model, DTO]


object ToDTO {

  def apply[Model, DTO](f: Model ⇒ DTO): ToDTO[Model, DTO] = new ToDTO[Model, DTO] {
    override def produce(a: Model): DTO = f(a)
  }

  object syntax {

    implicit class ToDTOSyntaxOps[Model, DTO](a: Model)(implicit F: ToDTO[Model, DTO]) {
      def toDTO: DTO = F.produce(a)
    }

  }

  implicit val chatToDTO: ToDTO[model.Chat, dto.Chat] =
    ToDTO(c ⇒ dto.Chat(c.id, c.`type`.name, c.title, c.userName, c.firstName, c.lastName))

  implicit val messageEntityToDTO: ToDTO[model.MessageEntity, dto.MessageEntity] =
    ToDTO(m ⇒ dto.MessageEntity(m.`type`.name, m.offset, m.length, m.url))

  implicit val photoSizeToDTO: ToDTO[model.PhotoSize, dto.PhotoSize] =
    ToDTO(p ⇒ dto.PhotoSize(p.fileId, p.width, p.height, p.fileSize))

  implicit val userToDTO: ToDTO[model.User, dto.User] =
    ToDTO(u ⇒ dto.User(u.id, u.firstName, u.lastName, u.userName))
}
