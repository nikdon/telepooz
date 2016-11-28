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

object utils {

  /** These functions are from json4s https://github.com/json4s/json4s */
  def camelize(word: String): String = {
    if (word.nonEmpty) {
      val w = pascalize(word)
      w.substring(0, 1).toLowerCase + w.substring(1)
    } else {
      word
    }
  }
  def pascalize(word: String): String = {
    val lst = word.split("_").toList
    (lst.headOption.map(s ⇒ s.substring(0, 1).toUpperCase + s.substring(1)).get ::
      lst.tail.map(s ⇒ s.substring(0, 1).toUpperCase + s.substring(1))).mkString("")
  }
  def snakenize(word: String): String = {
    val spacesPattern = "[-\\s]".r
    val firstPattern = "([A-Z]+)([A-Z][a-z])".r
    val secondPattern = "([a-z\\d])([A-Z])".r
    val replacementPattern = "$1_$2"
    spacesPattern.replaceAllIn(
      secondPattern.replaceAllIn(
        firstPattern.replaceAllIn(
          word, replacementPattern), replacementPattern), "_").toLowerCase
  }

}
