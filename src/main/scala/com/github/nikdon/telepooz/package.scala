package com.github.nikdon

package object telepooz {
  def unexpected[T](s: String): T = sys.error(s"Unexpected call: $s")
}
