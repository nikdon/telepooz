# Change Log

## Unreleased

## [0.3.4] 2017-01-04

- update dependencies to the present state
- refactor tests

## [0.3.3] 2016-10-25

### Changed

- fix scaladoc errors in `CirceEncoders.scala`, `Document.scala`, `File.scala`, `Reactor.scala`

## [0.3.2] 2016-10-22

### Changed

- update circe up to 0.5.4
- update scalacheck up to 1.13.3
- update .scalafmt

## [0.3.1] 2016-10-09

### Changed

- update circe up to 0.5.3

## [0.3.0] 2016-10-04

### Changed

- update akka version up to 2.4.11
- update akka-http-json up to 1.10.1

### Added

- New tools for building HTML5 games.
- New method sendGame, new object InlineQueryResultGame, new field game in Message.
- New parameter url in answerCallbackQuery. Create a game and accept the 
  conditions using Botfather to send custom urls that open your games for the user.
- New field callback_game in InlineKeyboardButton, new fields 
  game_short_name and chat_instance in CallbackQuery, new object CallbackGame.
- New methods setGameScore and getGameHighScores.
- Added new field switch_inline_query_current_chat in InlineKeyboardButton.
- Added caption fields to sendAudio, sendVoice, InlineQueryResultAudio, 
  InlineQueryResultVoice, InlineQueryResultCachedAudio, and 
  InlineQueryResultCachedVoice.
- New field all_members_are_admins in the Chat object.

## [0.2.0] 2016-09-23

### Changed

- `Map[String, ...]` replaced by `Reactions` container for IO ops #7
- bump cats up to 0.7.2~~, circe up to 0.5.1~~ #8
- update akka version up to 2.4.10 #10

### Added

- add webhook (without certificates)
    
## [0.1.0] 2016-08-30

### Added

- Implemented all models and methods available for the Telegram Bot API:
  
    - [Available types](https://core.telegram.org/bots/api#available-types)
    - [Available methods](https://core.telegram.org/bots/api#available-methods)
    - [Updating messages](https://core.telegram.org/bots/api#updating-messages)
    - [Inline mode](https://core.telegram.org/bots/api#inline-mode)
  
    However, this version does not support webhooks and sending payload other than JSON.
