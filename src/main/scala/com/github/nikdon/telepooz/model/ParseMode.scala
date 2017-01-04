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

package com.github.nikdon.telepooz.model

import com.github.nikdon.telepooz._


sealed trait ParseMode extends Product with Serializable {
  def name = this.productPrefix
}

object ParseMode {

  case object HTML extends ParseMode {override val name = super.name}
  case object Markdown extends ParseMode {override val name = super.name}

  def unsafe(str: String): ParseMode = str match {
    case HTML.name     ⇒ HTML
    case Markdown.name ⇒ Markdown
    case _             ⇒ unexpected(str)
  }
}
