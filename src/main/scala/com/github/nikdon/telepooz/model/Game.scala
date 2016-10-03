package com.github.nikdon.telepooz.model


/**
  * This object represents a game. Use BotFather to create and edit games, their short names will act as unique
  * identifiers.
  *
  * @param title         Title of the game
  * @param description   Description of the game
  * @param photo         Photo that will be displayed in the game message in chats.
  * @param text          Brief description of the game or high scores included in the game message. Can be
  *                      automatically edited to include current high scores for the game when the bot calls
  *                      setGameScore, or manually edited using editMessageText. 0-4096 characters.
  * @param text_entities Special entities that appear in text, such as usernames, URLs, bot commands, etc.
  * @param animation     Animation that will be displayed in the game message in chats. Upload via BotFather
  */
case class Game(
  title: String,
  description: String,
  photo: Vector[Vector[PhotoSize]],
  text: Option[String] = None,
  text_entities: Option[Vector[MessageEntity]] = None,
  animation: Option[Animation] = None
)
