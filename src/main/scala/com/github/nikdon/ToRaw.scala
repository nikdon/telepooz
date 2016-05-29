package com.github.nikdon

import com.github.nikdon.dto.methods.{GetMe, SendMessage}
import com.github.nikdon.raw.{CirceEncoders, RawCommand}
import io.circe.Encoder
import io.circe.syntax._


@scala.annotation.implicitNotFound("No member of type class ToRaw in scope for ${DTO} and ${Raw}")
trait ToRaw[DTO, Raw] extends Produce[DTO, Raw]

object ToRaw extends CirceEncoders {
  def apply[DTO, Raw](f: DTO ⇒ Raw): ToRaw[DTO, Raw] = new ToRaw[DTO, Raw] {
    override def produce(a: DTO): Raw = f(a)
  }

  object syntax {
    implicit class ToRawCommandSyntaxOps[Model, DTO](a: Model)(implicit F: ToRaw[Model, DTO]) {
      def toRawCommand: DTO = F.produce(a)
    }
  }

  implicit val getMeMethodToRawCommand: ToRaw[GetMe.type, RawCommand.GetMe.type] =
    ToRaw(m => RawCommand.GetMe)

  implicit def sendMessageMethodToRawCommand[A : IsResourceId : Encoder]: ToRaw[SendMessage[A], RawCommand.SendMessage] =
    ToRaw(m ⇒ RawCommand.SendMessage(m.asJson))
}
