package com.github.nikdon.telepooz.model.methods

import com.github.nikdon.telepooz.tags.QueryId
import shapeless.tag.@@


/**
  * Use this method to send answers to callback queries sent from inline keyboards. The answer will be displayed to the
  * user as a notification at the top of the chat screen or as an alert. On success, True is returned.
  *
  * @param callback_query_id  Unique identifier for the query to be answered
  * @param text               Text of the notification. If not specified, nothing will be shown to the user
  * @param show_alert         If true, an alert will be shown by the client instead of a notification at the top of the
  *                           chat screen. Defaults to false.
  */
case class AnswerCallbackQuery(callback_query_id: String @@ QueryId,
                               text: Option[String] = None,
                               show_alert: Option[Boolean] = None)
