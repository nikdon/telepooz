package com.github.nikdon.telepooz


@scala.annotation.implicitNotFound("No member of type class ToRaw in scope for ${DTO} and ${Raw}")
trait ToRaw[DTO, Raw] extends Produce[DTO, Raw]

object ToRaw {
  def apply[DTO, Raw](f: DTO ⇒ Raw): ToRaw[DTO, Raw] = new ToRaw[DTO, Raw] {
    override def produce(a: DTO): Raw = f(a)
  }

  object syntax {
    implicit class ToRawCommandSyntaxOps[Model, DTO](a: Model)(implicit F: ToRaw[Model, DTO]) {
      def toRawRequest: DTO = F.produce(a)
    }
  }

}
