# telepooz

telepooz is a scala wrapper for **[Telegram Bot API][telegram-api]**.

## Table of contents

1. [Quick start](#quick-start)
2. [Why?](#why)
3. [Usage](#usage)
4. [License](#license)

## Quick start

telepooz built for scala-2.11. To use it add following to build file:

```scala
resolvers += "jitpack" at "https://jitpack.io"
libraryDependencies += <!--TODO-->
```

And configure 


## Why?

The only one reason to write was curiosity. Telepooz written on top of **[akka][akka]** 
with intention to maximize using of functional abstractions provided by **[cats][cats]** 
and [shapeless][shapeless].

telepooz is far from completion, here is a list of some desired features to implemented in future:

- File uploading via multipart/form-data

## Usage

<!--TODO-->

    ```scala
    object EchoPooz extends Telepooz with ApiRequestExecutor {
      override def reactions = Map(
        "/echo" → (implicit message ⇒ args ⇒ {
          reply(message.text.fold("")(identity))
        })
      )
    }
    
    EchoPooz.run()
    ```


## License

telepooz is licensed under the **[Apache License, Version 2.0][apache]** (the "License"); 
you may not use this software except in compliance with the License.

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

[akka]: https://github.com/akka/akka
[apache]: http://www.apache.org/licenses/LICENSE-2.0
[cats]: https://github.com/typelevel/cats
[shapeless]: https://github.com/milessabin/shapeless
[telegram-api]: https://core.telegram.org/bots/api