# Change Log

## Unreleased

## [0.5.4] 2017-06-13

- fix errors

## [0.5.3] 2017-06-02

- fix errors

## [0.5.2] 2017-06-02

- fix chat decoder

## [0.5.1] 2017-05-25

- update akka to 2.5.2
- refactor webhooks

## [0.5.0] 2017-05-23

- 18th of May Telegram API changes
- Fix webhook endpoint setting

## [0.4.6] 2017-05-22

- update akka to 2.5.0
- update akka-http-json to 1.15.0
- update circe to 7.0.1

## [0.4.5] 2017-03-23

- update akka-http-json to 1.14.0

## [0.4.4] 2017-03-19

- update akka-http to 10.0.5
- update scalacheck to 1.13.5

## [0.4.3] 2017-03-09

- update akka-http-json to 1.13.0 - using `FailFastCirceSupport`

## [0.4.2] 2017-02-11

- update akka-http to 10.0.4

## [0.4.1] 2017-02-11

- update akka to 2.4.17

## [0.4.0] 2017-01-30

- remove shapeless tags and simplify models
- fix json encoder errors
- update to December's api changes
- shift to scala 2.12
- update dependencies to the current state
- update readme to last changes
- refactor api calls (remove redundant calls of **Free** fns instead of plain `_.lift`)

## [0.3.6] 2017-01-07

- fix incorrect construction of jsons for request api

## [0.3.5] 2017-01-06

- fix incorrect post request payloads

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
