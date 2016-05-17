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

  implicit val userToDTO: ToDTO[model.User, dto.User] =
    ToDTO(u ⇒ dto.User(u.id, u.firstName, u.lastName, u.userName))


  implicit val chatToDTO: ToDTO[model.Chat, dto.Chat] =
    ToDTO(c ⇒ dto.Chat(c.id, c.`type`.name, c.title, c.userName, c.firstName, c.lastName))
}
