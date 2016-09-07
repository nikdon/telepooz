# Change Log

## [Unreleased]

### Changed

- #7 `Map[String, ...]` replaced by `Reactions` container for IO ops
    
## [0.1.0] 2016-08-30

### Added

- Implemented all models and methods available for the Telegram Bot API:
  
    - [Available types](https://core.telegram.org/bots/api#available-types)
    - [Available methods](https://core.telegram.org/bots/api#available-methods)
    - [Updating messages](https://core.telegram.org/bots/api#updating-messages)
    - [Inline mode](https://core.telegram.org/bots/api#inline-mode)
  
    However, this version does not support webhooks and sending payload other than JSON.