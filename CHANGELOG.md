# Change Log

## [Unreleased]

### Changed

- `Map[String, ...]` replaced by `Reactions` container for IO ops #7
- bump cats up to 0.7.2~~, circe up to 0.5.1~~ #8
- add webhook (without certificates)
- update akka version up to 2.4.10 #10
    
## [0.1.0] 2016-08-30

### Added

- Implemented all models and methods available for the Telegram Bot API:
  
    - [Available types](https://core.telegram.org/bots/api#available-types)
    - [Available methods](https://core.telegram.org/bots/api#available-methods)
    - [Updating messages](https://core.telegram.org/bots/api#updating-messages)
    - [Inline mode](https://core.telegram.org/bots/api#inline-mode)
  
    However, this version does not support webhooks and sending payload other than JSON.