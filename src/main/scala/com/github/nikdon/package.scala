package com.github


package object nikdon {
  def unexpected[T](s: String): T = sys.error(s"Unexpected call: $s")
}
