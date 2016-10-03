package com.github.nikdon.telepooz.model.methods


/**
  * Use this method to get current webhook status. Requires no parameters. On success, returns a WebhookInfo object.
  * If the bot is using getUpdates, will return an object with the url field empty.
  */
case object GetWebhookInfo
