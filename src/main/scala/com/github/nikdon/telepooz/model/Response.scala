package com.github.nikdon.telepooz.model


/**
  * The response contains a JSON object, which always has a Boolean field ‘ok’ and may have an optional String
  * field ‘description’ with a human-readable description of the result. If ‘ok’ equals true, the request was
  * successful and the result of the query can be found in the ‘result’ field. In case of an unsuccessful request,
  * ‘ok’ equals false and the error is explained in the ‘description’. An Integer ‘error_code’ field is also
  * returned, but its contents are subject to change in the future.
  *
  * @param ok           Status of the request
  * @param result       The result of the query
  * @param description  Explanation of a possible error
  * @param errorCode    The error code
  * @tparam T           A type of the expected model
  */
case class Response[T](ok: Boolean,
                       result: Option[T],
                       description: Option[String] = None,
                       errorCode: Option[Int] = None)
