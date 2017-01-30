/*
 * Copyright 2016 Nikolay Donets
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.nikdon.telepooz

import cats.free.Free

import scala.language.implicitConversions

/** Import to use */
object api {

  /** Implicitly lift `F[_]` to Free[F, A] and allow to use Natural Transformation as interpreter */
  implicit def implicitLift[F[_], A](fa: F[A]): Free[F, A] = Free liftF fa

}
