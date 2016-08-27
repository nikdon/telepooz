package com.github.nikdon.telepooz.model.inline

import com.github.nikdon.telepooz.model.InlineKeyboardMarkup
import com.github.nikdon.telepooz.tags.{FoursquareId, ResultId}
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


/**
  * Represents a link to an animated GIF file. By default, this animated GIF file will be sent by the user with optional
  * caption. Alternatively, you can use input_message_content to send a message with the specified content instead of
  * the animation.
  *
  * @param `type`                 Type of the result, must be gif
  * @param id                     Unique identifier for this result, 1-64 bytes
  * @param gif_url                A valid URL for the GIF file. File size must not exceed 1MB
  * @param gif_width              Width of the GIF
  * @param gif_height             Height of the GIF
  * @param thumb_url              URL of the static thumbnail for the result (jpeg or gif)
  * @param title                  Title for the result
  * @param caption                Caption of the GIF file to be sent, 0-200 characters
  * @param reply_markup           Inline keyboard attached to the message
  * @param input_message_content  Content of the message to be sent instead of the GIF animation
  */
case class InlineQueryResultGif(`type`: String = "gif", // TODO Move inside?
                                id: String @@ ResultId,
                                gif_url: String,
                                gif_width: Option[Int] = None,
                                gif_height: Option[Int] = None,
                                thumb_url: String,
                                title: Option[String] = None,
                                caption: Option[String] = None,
                                reply_markup: Option[InlineKeyboardMarkup] = None,
                                input_message_content: Option[InputMessageContent] = None) extends InlineQueryResult


/**
  * Represents a link to a video animation (H.264/MPEG-4 AVC video without sound). By default, this animated MPEG-4 file
  * will be sent by the user with optional caption. Alternatively, you can use input_message_content to send a message
  * with the specified content instead of the animation.
  *
  * @param `type`                 Type of the result, must be mpeg4_gif
  * @param id                     Unique identifier for this result, 1-64 bytes
  * @param mpeg4_url              A valid URL for the MP4 file. File size must not exceed 1MB
  * @param mpeg4_width            Video width
  * @param mpeg4_height           Video height
  * @param thumb_url              URL of the static thumbnail (jpeg or gif) for the result
  * @param title                  Title for the result
  * @param caption                Caption of the MPEG-4 file to be sent, 0-200 characters
  * @param reply_markup           Inline keyboard attached to the message
  * @param input_message_content  Content of the message to be sent instead of the video animation
  */
case class InlineQueryResultMpeg4Gif(`type`: String = "mpeg4_gif",
                                     id: String @@ ResultId,
                                     mpeg4_url: String,
                                     mpeg4_width: Option[Int] = None,
                                     mpeg4_height: Option[Int] = None,
                                     thumb_url: String,
                                     title: Option[String] = None,
                                     caption: Option[String] = None,
                                     reply_markup: Option[InlineKeyboardMarkup] = None,
                                     input_message_content: Option[InputMessageContent] = None) extends InlineQueryResult


/**
  * Represents a link to a page containing an embedded video player or a video file. By default, this video file will be
  * sent by the user with an optional caption. Alternatively, you can use input_message_content to send a message with
  * the specified content instead of the video.
  *
  * @param `type`                 Type of the result, must be video
  * @param id                     Unique identifier for this result, 1-64 bytes
  * @param video_url              A valid URL for the embedded video player or video file
  * @param mime_type              Mime type of the content of video url, “text/html” or “video/mp4”
  * @param thumb_url              URL of the thumbnail (jpeg only) for the video
  * @param title                  Title for the result
  * @param caption                Caption of the video to be sent, 0-200 characters
  * @param video_width            Video width
  * @param video_height           Video height
  * @param video_duration         Video duration in seconds
  * @param description            Short description of the result
  * @param reply_markup           Inline keyboard attached to the message
  * @param input_message_content  Content of the message to be sent instead of the video
  */
case class InlineQueryResultVideo(`type`: String = "video",
                                  id: String @@ ResultId,
                                  video_url: String,
                                  mime_type: String,
                                  thumb_url: String,
                                  title: String,
                                  caption: Option[String] = None,
                                  video_width: Option[Int] = None,
                                  video_height: Option[Int] = None,
                                  video_duration: Option[Int] = None,
                                  description: Option[String] = None,
                                  reply_markup: Option[InlineKeyboardMarkup] = None,
                                  input_message_content: Option[InputMessageContent] = None) extends InlineQueryResult


/**
  * Represents a link to an mp3 audio file. By default, this audio file will be sent by the user. Alternatively, you can
  * use input_message_content to send a message with the specified content instead of the audio.
  *
  * @param `type`                 Type of the result, must be audio
  * @param id                     Unique identifier for this result, 1-64 bytes
  * @param audio_url              A valid URL for the audio file
  * @param title                  Title
  * @param performer              Performer
  * @param audio_duration         Audio duration in seconds
  * @param reply_markup           Inline keyboard attached to the message
  * @param input_message_content  Content of the message to be sent instead of the audio
  */
case class InlineQueryResultAudio(`type`: String = "audio",
                                  id: String @@ ResultId,
                                  audio_url: String,
                                  title: String,
                                  performer: Option[String],
                                  audio_duration: Option[Int],
                                  reply_markup: Option[InlineKeyboardMarkup] = None,
                                  input_message_content: Option[InputMessageContent] = None) extends InlineQueryResult


/**
  * Represents a link to a voice recording in an .ogg container encoded with OPUS. By default, this voice recording will
  * be sent by the user. Alternatively, you can use input_message_content to send a message with the specified content
  * instead of the the voice message.
  *
  * @param `type`                 Type of the result, must be voice
  * @param id                     Unique identifier for this result, 1-64 bytes
  * @param voice_url              A valid URL for the voice recording
  * @param title                  Recording title
  * @param voice_duration         Recording duration in seconds
  * @param reply_markup           Inline keyboard attached to the message
  * @param input_message_content  Content of the message to be sent instead of the voice recording
  */
case class InlineQueryResultVoice(`type`: String = "voice",
                                  id: String @@ ResultId,
                                  voice_url: String,
                                  title: String,
                                  voice_duration: Option[Int],
                                  reply_markup: Option[InlineKeyboardMarkup] = None,
                                  input_message_content: Option[InputMessageContent] = None) extends InlineQueryResult


/**
  * Represents a link to a file. By default, this file will be sent by the user with an optional caption. Alternatively,
  * you can use input_message_content to send a message with the specified content instead of the file. Currently, only
  * .PDF and .ZIP files can be sent using this method.
  *
  * @param `type`                 Type of the result, must be document
  * @param id                     Unique identifier for this result, 1-64 bytes
  * @param title                  Title for the result
  * @param caption                Caption of the document to be sent, 0-200 characters
  * @param document_url           A valid URL for the file
  * @param mime_type              Mime type of the content of the file, either “application/pdf” or “application/zip”
  * @param description            Short description of the result
  * @param thumb_url              URL of the thumbnail (jpeg only) for the file
  * @param thumb_width            Thumbnail width
  * @param thumb_height           Thumbnail height
  * @param reply_markup           Inline keyboard attached to the message
  * @param input_message_content  Content of the message to be sent instead of the file
  */
case class InlineQueryResultDocument(`type`: String = "document",
                                     id: String,
                                     title: String,
                                     caption: Option[String] = None,
                                     document_url: String,
                                     mime_type: String,
                                     description: Option[String] = None,
                                     thumb_url: Option[String] = None,
                                     thumb_width: Option[Int] = None,
                                     thumb_height: Option[Int] = None,
                                     reply_markup: Option[InlineKeyboardMarkup] = None,
                                     input_message_content: Option[InputMessageContent] = None) extends InlineQueryResult


/**
  * Represents a location on a map. By default, the location will be sent by the user. Alternatively, you can use
  * input_message_content to send a message with the specified content instead of the location.
  *
  * @param `type`                 Type of the result, must be location
  * @param id                     Unique identifier for this result, 1-64 Bytes
  * @param title                  Location title
  * @param latitude               Location latitude in degrees
  * @param longitude              Location longitude in degrees
  * @param thumb_url              URL of the thumbnail (jpeg only) for the file
  * @param thumb_width            Thumbnail width
  * @param thumb_height           Thumbnail height
  * @param reply_markup           Inline keyboard attached to the message
  * @param input_message_content  Content of the message to be sent instead of the file
  */
case class InlineQueryResultLocation(`type`: String = "location",
                                     id: String,
                                     title: String,
                                     latitude: Double,
                                     longitude: Double,
                                     thumb_url: Option[String] = None,
                                     thumb_width: Option[Int] = None,
                                     thumb_height: Option[Int] = None,
                                     reply_markup: Option[InlineKeyboardMarkup] = None,
                                     input_message_content: Option[InputMessageContent] = None) extends InlineQueryResult


/**
  * Represents a venue. By default, the venue will be sent by the user. Alternatively, you can use input_message_content
  * to send a message with the specified content instead of the venue.
  *
  * @param `type`                 Type of the result, must be venue
  * @param id                     Unique identifier for this result, 1-64 Bytes
  * @param title                  Title of the venue
  * @param latitude               Latitude of the venue location in degrees
  * @param longitude              Longitude of the venue location in degrees
  * @param address                Address of the venue
  * @param foursquare_id          Foursquare identifier of the venue if known
  * @param thumb_url              URL of the thumbnail (jpeg only) for the file
  * @param thumb_width            Thumbnail width
  * @param thumb_height           Thumbnail height
  * @param reply_markup           Inline keyboard attached to the message
  * @param input_message_content  Content of the message to be sent instead of the file
  */
case class InlineQueryResultVenue(`type`: String = "venue",
                                  id: String,
                                  title: String,
                                  latitude: Double,
                                  longitude: Double,
                                  address: String,
                                  foursquare_id: Option[String @@ FoursquareId] = None,
                                  thumb_url: Option[String] = None,
                                  thumb_width: Option[Int] = None,
                                  thumb_height: Option[Int] = None,
                                  reply_markup: Option[InlineKeyboardMarkup] = None,
                                  input_message_content: Option[InputMessageContent] = None) extends InlineQueryResult


/**
  * Represents a contact with a phone number. By default, this contact will be sent by the user. Alternatively, you can
  * use input_message_content to send a message with the specified content instead of the contact.
  *
  * @param `type`                 Type of the result, must be contact
  * @param id                     Unique identifier for this result, 1-64 Bytes
  * @param phone_number           Contact's phone number
  * @param first_name             Contact's first name
  * @param last_name              Contact's last name
  * @param thumb_url              URL of the thumbnail (jpeg only) for the file
  * @param thumb_width            Thumbnail width
  * @param thumb_height           Thumbnail height
  * @param reply_markup           Inline keyboard attached to the message
  * @param input_message_content  Content of the message to be sent instead of the file
  */
case class InlineQueryResultContact(`type`: String = "contact",
                                    id: String,
                                    phone_number: String,
                                    first_name: String,
                                    last_name: Option[String] = None,
                                    thumb_url: Option[String] = None,
                                    thumb_width: Option[Int] = None,
                                    thumb_height: Option[Int] = None,
                                    reply_markup: Option[InlineKeyboardMarkup] = None,
                                    input_message_content: Option[InputMessageContent] = None) extends InlineQueryResult


/**
  * Represents a link to a photo stored on the Telegram servers. By default, this photo will be sent by the user with an
  * optional caption. Alternatively, you can use input_message_content to send a message with the specified content
  * instead of the photo.
  *
  * @param `type`                 Type of the result, must be photo
  * @param id                     Unique identifier for this result, 1-64 bytes
  * @param photo_file_id          A valid file identifier of the photo
  * @param title                  Title for the result
  * @param description            Short description of the result
  * @param caption                Caption of the photo to be sent, 0-200 characters
  * @param reply_markup           Inline keyboard attached to the message
  * @param input_message_content  Content of the message to be sent instead of the photo
  */
case class InlineQueryResultCachedPhoto(`type`: String = "photo",
                                        id: String,
                                        photo_file_id: String,
                                        title: Option[String] = None,
                                        description: Option[String] = None,
                                        caption: Option[String] = None,
                                        reply_markup: Option[InlineKeyboardMarkup] = None,
                                        input_message_content: Option[InputMessageContent] = None) extends InlineQueryResult


/**
  * Represents a link to an animated GIF file stored on the Telegram servers. By default, this animated GIF file will be
  * sent by the user with an optional caption. Alternatively, you can use input_message_content to send a message with
  * specified content instead of the animation.
  *
  * @param `type`                 Type of the result, must be gif
  * @param id                     Unique identifier for this result, 1-64 bytes
  * @param gif_file_id            A valid file identifier for the GIF file
  * @param title                  Title for the result
  * @param caption                Caption of the GIF file to be sent, 0-200 characters
  * @param reply_markup           An Inline keyboard attached to the message
  * @param input_message_content  Content of the message to be sent instead of the GIF animation
  */
case class InlineQueryResultCachedGif(`type`: String = "gif",
                                      id: String @@ ResultId,
                                      gif_file_id: String,
                                      title: Option[String] = None,
                                      caption: Option[String] = None,
                                      reply_markup: Option[InlineKeyboardMarkup] = None,
                                      input_message_content: Option[InputMessageContent] = None) extends InlineQueryResult


/**
  * Represents a link to a video animation (H.264/MPEG-4 AVC video without sound) stored on the Telegram servers. By
  * default, this animated MPEG-4 file will be sent by the user with an optional caption. Alternatively, you can use
  * input_message_content to send a message with the specified content instead of the animation.
  *
  * @param `type`                 Type of the result, must be mpeg4_gif
  * @param id                     Unique identifier for this result, 1-64 bytes
  * @param mpeg4_file_id          A valid file identifier for the MP4 file
  * @param title                  Title for the result
  * @param caption                Caption of the MPEG-4 file to be sent, 0-200 characters
  * @param reply_markup           An Inline keyboard attached to the message
  * @param input_message_content  Content of the message to be sent instead of the video animation
  */
case class InlineQueryResultCachedMpeg4Gif(`type`: String = "mpeg4_gif",
                                           id: String @@ ResultId,
                                           mpeg4_file_id: String,
                                           title: Option[String] = None,
                                           caption: Option[String] = None,
                                           reply_markup: Option[InlineKeyboardMarkup] = None,
                                           input_message_content: Option[InputMessageContent] = None) extends InlineQueryResult


/**
  * Represents a link to a sticker stored on the Telegram servers. By default, this sticker will be sent by the user.
  * Alternatively, you can use input_message_content to send a message with the specified content instead of the sticker.
  *
  * @param `type`                 Type of the result, must be sticker
  * @param id                     Unique identifier for this result, 1-64 bytes
  * @param sticker_file_id        A valid file identifier of the sticker
  * @param reply_markup           An Inline keyboard attached to the message
  * @param input_message_content  Content of the message to be sent instead of the sticker
  */
case class InlineQueryResultCachedSticker(`type`: String = "sticker",
                                          id: String @@ ResultId,
                                          sticker_file_id: String,
                                          reply_markup: Option[InlineKeyboardMarkup] = None,
                                          input_message_content: Option[InputMessageContent] = None) extends InlineQueryResult
