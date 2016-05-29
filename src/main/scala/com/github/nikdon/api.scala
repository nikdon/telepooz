package com.github.nikdon

import cats.free.Free

import scala.language.implicitConversions


object api {

  // TODO replace by implicit lift from scalaz-interpreter when circe 0.5.0 will use cats 0.6.0
  implicit def ImplicitInjectLift[F[_], A](fa: F[A]): Free[F, A] = Free liftF fa

  def execute[F[_], R](m: F[R]): Free[F, R] = m.step
}