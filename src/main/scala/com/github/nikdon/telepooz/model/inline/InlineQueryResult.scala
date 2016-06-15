package com.github.nikdon.telepooz.model.inline

import com.github.nikdon.telepooz.model.InlineKeyboardMarkup
import com.github.nikdon.telepooz.tags.ResultId
import shapeless.tag.@@


/** This object represents one result of an inline query */
sealed trait InlineQueryResult extends Product with Serializable


/**
  * Represents a link to an article or web page.
  *
  * @param `type`                 Type of the result, must be article
  * @param id                     Unique identifier for this result, 1-64 Bytes
  * @param title                  Title of the result
  * @param input_message_content  Content of the message to be sent
  * @param reply_markup           Inline keyboard attached to the message
  * @param url                    URL of the result
  * @param hide_url               Pass True, if you don't want the URL to be shown in the message
  * @param description            Short description of the result
  * @param thumb_url              Url of the thumbnail for the result
  * @param thumb_width            Thumbnail width
  * @param thumb_height           Thumbnail height
  */
case class InlineQueryResultArticle(`type`: String = "article", // TODO Move inside?
                                    id: String @@ ResultId,
                                    title: String,
                                    input_message_content: InputMessageContent,
                                    reply_markup: Option[InlineKeyboardMarkup] = None,
                                    url: Option[String] = None,
                                    hide_url: Option[Boolean] = None,
                                    description: Option[String] = None,
                                    thumb_url: Option[String] = None,
                                    thumb_width: Option[Int] = None,
                                    thumb_height: Option[Int] = None) extends InlineQueryResult


/**
  * Represents a link to a photo. By default, this photo will be sent by the user with optional caption. Alternatively,
  * you can use input_message_content to send a message with the specified content instead of the photo.
  *
  * @param `type`                 Type of the result, must be photo
  * @param id                     Unique identifier for this result, 1-64 bytes
  * @param photo_url              A valid URL of the photo. Photo must be in jpeg format. Photo size must not exceed 5MB
  * @param thumb_url              URL of the thumbnail for the photo
  * @param photo_width            Width of the photo
  * @param photo_height           Height of the photo
  * @param title                  Title for the result
  * @param description            Short description of the result
  * @param caption                Caption of the photo to be sent, 0-200 characters
  * @param reply_markup           Inline keyboard attached to the message
  * @param input_message_content  Content of the message to be sent instead of the photo
  */
case class InlineQueryResultPhoto(`type`: String = "photo", // TODO Move inside?
                                  id: String @@ ResultId,
                                  photo_url: String,
                                  thumb_url: String,
                                  photo_width: Option[Int] = None,
                                  photo_height: Option[Int] = None,
                                  title: Option[String] = None,
                                  description: Option[String] = None,
                                  caption: Option[String] = None,
                                  reply_markup: Option[InlineKeyboardMarkup] = None,
                                  input_message_content: Option[InputMessageContent] = None) extends InlineQueryResult
