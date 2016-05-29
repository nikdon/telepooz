package com.github.nikdon


@scala.annotation.implicitNotFound("No member of type class ToModel in scope for ${DTO} and ${Model}")
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

}
