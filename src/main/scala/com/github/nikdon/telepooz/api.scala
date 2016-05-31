package com.github.nikdon.telepooz

import cats.free.Free

import scala.language.implicitConversions


object api {

  implicit def implicitLift[F[_], A](fa: F[A]): Free[F, A] = Free liftF fa

  def execute[F[_], R](m: F[R]): Free[F, R] = m.step
}