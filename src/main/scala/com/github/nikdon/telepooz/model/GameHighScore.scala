package com.github.nikdon.telepooz.model


/**
  * This object represents one row of the high scores table for a game.
  *
  * @param position Position in high score table for the game
  * @param user     User
  * @param score    Score
  */
case class GameHighScore(
  position: Int,
  user: User,
  score: Int
)
