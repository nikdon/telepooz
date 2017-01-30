package com.github.nikdon.telepooz.model.methods

import com.github.nikdon.telepooz.model.Response

/**
  * Use this method to remove webhook integration if you decide to switch back to getUpdates.
  * Returns True on success. Requires no parameters.
  */
case object DeleteWebhook extends Method[Response[Boolean]]
