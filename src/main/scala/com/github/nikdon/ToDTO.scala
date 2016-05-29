package com.github.nikdon


@scala.annotation.implicitNotFound("No member of type class ToDTO in scope for ${Model} and ${DTO}")
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

}
