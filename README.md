# telepooz
[![Build Status](https://travis-ci.org/nikdon/telepooz.svg?branch=master)](https://travis-ci.org/nikdon/telepooz)
[![codecov](https://codecov.io/gh/nikdon/telepooz/branch/master/graph/badge.svg)](https://codecov.io/gh/nikdon/telepooz)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/42eedce6ec0d4c03bab471f610020aec)](https://www.codacy.com/app/nd/telepooz?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=nikdon/telepooz&amp;utm_campaign=Badge_Grade)
[![](https://jitpack.io/v/nikdon/telepooz.svg)](https://jitpack.io/#nikdon/telepooz)
[![Telegram API](https://img.shields.io/badge/Telegram%20API-May%2018%2C%202017-green.svg)](https://core.telegram.org/bots/api#recent-changes)

**telepooz** is a scala wrapper for **[Telegram Bot API][telegram-api]**.

## Table of contents

1. [Quick start](#quick-start)
2. [Why?](#why)
3. [Usage](#usage)
4. [Contributors and participation](#contribution-and-participation)
5. [License](#license)

## Quick start

telepooz built for **scala-2.12**. To use it add following to build file:

```scala
resolvers += "jitpack" at "https://jitpack.io"
libraryDependencies += "com.github.nikdon" % "telepooz" % "0.5.5"
```

And configure telepooz via the `reference.conf` or `aplication.conf` or by, for ex., env variables:
 
```scala
telegram {
  host = "api.telegram.org"
  token = "123456:ABC-DEF1234ghIkl-zyx57W2v1u123ew11"
  token = ${?telegram_token}

  polling {
    interval = 1000 // in milliseconds
    interval = ${?telegram_update_interval}
    limit = 100
    limit = ${?telegram_update_limit}
    parallelism = 2
    parallelism = ${?telegram_polling_parallelism}
  }

  reactor {
    parallelism = 2
    parallelism = ${?telegram_reactor_parallelism}
  }
} 
```

## Why?

The only one reason to write was curiosity. Telepooz written on top of **[Akka Streams][akka]** with intention to 
maximize using of functional abstractions provided by **[cats][cats]**. For example, API 
requests are composable:

```scala
import com.github.nikdon.telepooz.api._
import com.github.nikdon.telepooz.model.methods
import com.github.nikdon.telepooz.engine.ApiRequestExecutor

val apiRequestExecutor = new ApiRequestExecutor() {}

val req = for {
  a ← methods.GetMe
  b ← methods.SendMessage("abc", a.result.fold("empty")(_.first_name))
} yield b

val res = req.foldMap(apiRequestExecutor)

whenReady(res){ m ⇒
  m shouldBe a [Response[_]]
  m.ok shouldEqual true
  m.result shouldBe defined
  m.result.value shouldBe a [model.Message]
}
```

telepooz is far from completion, here is a list of some desired features to implemented in future:

- File uploading via multipart/form-data

## Usage

In general, bot consists of three parts: `ApiRequestExecutor`, `Polling` or `WebHook` and `Reactor`. 
`ApiRequestExecutor` creates requests to the telegram bot API endpoint. `Polling` asks telegram server about new 
updates via `ApiRequestExecutor` and send them to the `Reactor`. `WebHook` receives new updates via incoming requests 
from telegram. Finally `Reactor` reacts on an input stream of incoming updates from the `Polling` or `WebHook`. 
Toplevel `Telepooz` trait provides a method `instance` that is a 
`ReaderT[Future, (ApiRequestExecutor, Polling, Reactor), Done]`. To start a bot provide a valid input 
for `instance.run(...)` with all three components described above.

### Polling

```scala
/** Just an example of how the bot might look like */
import com.github.nikdon.telepooz.engine._

object NaiveBot extends Telepooz with App {

  implicit val are = new ApiRequestExecutor {}
  val poller       = new Polling
  val reactor      = new Reactor {
    val reactions = CommandBasedReactions()
      .on("/start")(implicit message ⇒ args ⇒ reply("You are started!"))
      .on("/test")(implicit message ⇒ args ⇒ reply("Hi there!"))
  }

  instance.run((are, poller, reactor))
}
```

### Webhook

```scala
import akka.stream.ActorMaterializer
import com.github.nikdon.telepooz.engine._

object WebhookBot extends Telepooz with App {

  implicit val asm = ActorMaterializer()
  implicit val are = new MockApiRequestExecutor(1) {}
  val poller       = new Webhook(endpoint = "test", interface = "127.0.0.1")
  val reactor = new Reactor {
    val reactions = CommandBasedReactions()
      .on("/start")(implicit message ⇒
        args ⇒ {
          println(s"You are started! $args")
          reply("You are started!")
      })
      .on("/test")(implicit message ⇒
        args ⇒ {
          println(s"You are tested! $args")
          reply("You are tested!")
      })
  }

  webhook.run((poller, reactor))
}
```

## Contributors and participation

* [Nikolay Donets](https://github.com/nikdon)

**telepooz** support the [Typelevel][typelevel] [code of conduct][code-of-conduct], 
contributions are always welcome. Good ways to contribute include:

 * Raising bugs and feature requests,
 * Fixing bugs and developing new features (I will attempt to merge in pull requests ASAP),
 * Improving the performance of **telepooz**,
 * Provide examples of bots.

## License

**telepooz** is licensed under the **[Apache License, Version 2.0][apache]** (the "License"); 
you may not use this software except in compliance with the License.

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

[akka]: https://github.com/akka/akka
[apache]: http://www.apache.org/licenses/LICENSE-2.0
[cats]: https://github.com/typelevel/cats
[code-of-conduct]: http://typelevel.org/conduct.html
[shapeless]: https://github.com/milessabin/shapeless
[telegram-api]: https://core.telegram.org/bots/api
[typelevel]: http://typelevel.org/
[webhooks]: https://core.telegram.org/bots/api#getting-updates


[![Analytics](https://ga-beacon.appspot.com/UA-91956314-1/telepooz/readme?pixel)](https://github.com/igrigorik/ga-beacon)
