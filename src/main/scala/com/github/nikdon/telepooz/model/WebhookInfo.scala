package com.github.nikdon.telepooz.model


/**
  * Contains information about the current status of a webhook.
  *
  * @param url                    Webhook URL, may be empty if webhook is not set up
  * @param has_custom_certificate True, if a custom certificate was provided for webhook certificate checks
  * @param pending_update_count   Number of updates awaiting delivery
  * @param last_error_date        Unix time for the most recent error that happened when trying to deliver an update via
  *                               webhook
  * @param last_error_message     Error message in human-readable format for the most recent error that happened when
  *                               trying to deliver an update via webhook
  */
case class WebhookInfo(
  url: String,
  has_custom_certificate: Boolean,
  pending_update_count: Long,
  last_error_date: Long,
  last_error_message: String
)
