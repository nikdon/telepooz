package com.github.nikdon.telepooz.model.methods

trait Method[Result] extends Product with Serializable {
  lazy val name: String = this.productPrefix
}
