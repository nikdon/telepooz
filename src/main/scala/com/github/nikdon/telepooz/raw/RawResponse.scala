package com.github.nikdon.telepooz.raw


case class RawResponse[T](ok: Boolean,
                          result: Option[T],
                          description: Option[String] = None,
                          errorCode: Option[Int] = None)
