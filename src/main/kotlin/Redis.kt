import Utils.linkItem
import Utils.textItem
import com.kslides.KSlides
import com.kslides.atag
import com.kslides.codeSnippet
import com.kslides.diagram
import com.kslides.githubRawUrl
import com.kslides.include
import com.kslides.slide.DslSlide
import com.kslides.unorderedList
import kotlinx.css.Clear
import kotlinx.css.Display
import kotlinx.css.Float
import kotlinx.css.LinearDimension
import kotlinx.css.QuotedString
import kotlinx.css.clear
import kotlinx.css.content
import kotlinx.css.display
import kotlinx.css.float
import kotlinx.css.marginBottom
import kotlinx.css.width
import kotlinx.html.FlowOrInteractiveOrPhrasingContent
import kotlinx.html.SECTION
import kotlinx.html.a
import kotlinx.html.br
import kotlinx.html.div
import kotlinx.html.h1
import kotlinx.html.h2
import kotlinx.html.h3
import kotlinx.html.h4
import kotlinx.html.id
import kotlinx.html.img
import kotlinx.html.style

object Redis {

  fun KSlides.redisPresentation(useLocal: Boolean) {
    presentation {
      path = "redis.html"

      css += """
        .smallText {
          padding-bottom: 20px;
        }
        .smallText li {
          font-size: 0.8em;
        }
      """

      css {
        /* Clear floats after the columns */
        rule(".multiColumn2:after") {
          content = QuotedString("")
          display = Display.table
          clear = Clear.both
        }

        rule(".column2") {
          float = Float.left
          width = LinearDimension("50%")
        }

        rule(".column2 li") {
          marginBottom = LinearDimension("10px")
        }
      }

      val owner = "pambrose"
      val repoName = "redis-demo"
      val base = "src/main/kotlin"
      val SP = "&nbsp;"

      presentationConfig {
        topRightHref = "#/redis"
      }

      fun SECTION.redisCode(
        dslSlide: DslSlide,
        fileName: String,
        highlight: String = "",
      ) =
        codeSnippet {
          language = "bash"
          copyButton = false
          if (highlight.isNotEmpty())
            highlightPattern = highlight
          +dslSlide.include("$base/redis/$fileName")
        }

      fun SECTION.jedisCode(
        dslSlide: DslSlide,
        fileName: String,
        highlight: String,
        tokenName: String = "slide",
      ) =
        codeSnippet {
          language = "kotlin"
          copyButton = false
          if (highlight.isNotEmpty())
            highlightPattern = highlight
          +dslSlide.include(
            if (useLocal) "redis-demo/$base/$fileName" else githubRawUrl(owner, repoName, "$base/$fileName"),
            beginToken = "// $tokenName begin",
            endToken = "// $tokenName end",
          )
        }

      fun FlowOrInteractiveOrPhrasingContent.rc(name: String) =
        atag(name.uppercase(), "https://redis.io/commands/${name.lowercase()}/")

      dslSlide {
        content {
          id = "redis"
          h2 { +"Redis Basics" }
          a {
            href = "https://redis.io"
            target = "_blank"
            img { width = "250px"; src = "images/redis/redis.png" }
          }
          a {
            href = "https://pambrose.github.io/myslides/redis.html"
            +"https://pambrose.github.io/myslides/redis.html"
          }
        }
      }

      dslSlide {
        content {
          h3 { +"🕯 Overview of Slides" }
          unorderedList(
            linkItem("Getting Redis up and running", "https://redis.io/docs/getting-started/"),
            textItem("Problem Statement"),
            textItem("What is Redis?"),
            linkItem("Data Types", "https://redis.io/docs/data-types/"),
            linkItem("Redis Commands", "https://redis.io/commands/"),
            linkItem("Programmatic APIs", "https://redis.io/docs/clients/"),
            textItem("Use Cases"),
            linkItem("Pub/Sub", "https://redis.io/docs/manual/pubsub/"),
          ) {
            style = "font-size:30px; padding-left: 80px"
          }
        }
      }

      markdownSlide {
        content {
          """
            ## OSX Install for Redis Client and Server

            ```bash

            $ brew install redis iredis


            ```

            #### [Other Platforms](https://redis.io/docs/getting-started/installation/)
          """
        }
      }

      markdownSlide {
        content {
          """
            ## Start a Redis Server

            ```bash

            $ docker run -d -p 6379:6379 --name myredis redis


            ```
            or

            ```bash

            $ redis-server


            ```
          """
        }
      }

      markdownSlide {
        content {
          """
            ## Start a Redis Client

            ```bash

            $ redis-cli


            ```
            or
            ```bash

            $ iredis


            ```
          """
        }
      }

      // https://collabnix.com/how-to-setup-and-run-redis-in-a-docker-container/

      dslSlide {
        content {
          h3 { +"❓What Problems Are We Trying To Solve?" }
          unorderedList(
            "Anyone with a credit card buy 100 servers",
            "How do we orchestrate them?",
            "How do we share data among them?",
            "How do we distribute work?",
            "How do we collect results?",
            "How do we count things?",
            "How do we elect leaders?",
            "How do we ensure availability?",
          ) {
            style = "font-size:30px; padding-left: 80px"
          }
        }
      }

      dslSlide {
        content {
          h3 { +"🔦 What is Redis?" }
          unorderedList(
            "REmote DIctionary Server",
            "Fast NoSQL database written in ANSI C",
            "An in-memory database -- all data is stored in RAM",
            "Based on the key-value model",
            "All access is via a key",
            "Meant to be run as a network-attached server",
            "Simple TCP protocol, which is implemented in many languages",
            "Can act as a cache, database, message broker, and pub/sub engine",
            "One of many possible solutions in this space",
          ) {
            style = "font-size:30px; padding-left: 80px"
          }
        }
      }

      dslSlide {
        content {
          br { }
          br { }
          h2 { +"High Level Architecture" }
          br { }
          br { }
          diagram("mermaid") {
            height = "300px"
            source =
              """
            flowchart TB
                S1("Server 1") --> R["Redis"]
                S2("Server 2") --> R
                S3("Server 3") --> R
                S4("Server 4") --> R
                S5("Server 5") --> R
                style R fill:#f66,stroke:#000,stroke-width:1px
            """
          }
        }
      }

      dslSlide {
        content {
          h2 { +"🧭 Redis DataTypes"; style = "margin-bottom:20px;" }
          div("multiColumn2") {
            val fmt = "font-size:30px; padding-top:10px;"
            div("column2") {
              unorderedList(
                linkItem("Strings", "https://redis.io/docs/data-types/strings/"),
                linkItem("Lists", "https://redis.io/docs/data-types/lists/"),
                linkItem("Hashes", "https://redis.io/docs/data-types/hashes/"),
                linkItem("Sets", "https://redis.io/docs/data-types/sets/"),
                linkItem("Sorted Sets", "https://redis.io/docs/data-types/sorted-sets/"),
              ) { style = fmt }
            }
            div("column2") {
              unorderedList(
                linkItem("Streams", "https://redis.io/docs/data-types/streams/"),
                linkItem("Geospatial", "https://redis.io/docs/data-types/geospatial/"),
                linkItem("HyperLogLog", "https://redis.io/docs/data-types/hyperloglogs/"),
                linkItem("Bitmaps", "https://redis.io/docs/data-types/bitmaps/"),
                linkItem("Bitfields", "https://redis.io/docs/data-types/bitfields/"),
              ) { style = fmt }
            }
          }
        }
      }

      dslSlide {
        content {
          h2 {
            +"🚌 "
            atag("Redis Commands", "https://redis.io/commands/")
            style = "margin-bottom:20px;"
          }
          div("multiColumn2") {
            val fmt = "font-size:30px; padding-top:10px;"
            div("column2") {
              unorderedList(
                "SET/GET/DEL",
                "EXPIRE/PERSIST",
                "MSET/MGET",
                "INCR/DECR",
              ) { style = fmt }
            }
            div("column2") {
              unorderedList(
                //"LMOVE/LRANGE/BLPOP",
                "LPUSH/LPOP/RPOP/LLEN",
                //"SADD/SMEMBERS/SISMEMBER",
                //"SINTER/SCARD",
                "HSET/HGET/HGETALL",
                //"SUBSCRIBE/UNSUBSCRIBE/PUBLISH",
              ) { style = fmt }
            }
          }
        }
      }

      dslSlide {
        content { h1 { atag("Redis Strings", "https://redis.io/docs/data-types/strings/") } }
      }

      dslSlide {
        content {
          h2 { rc("SET"); +"/"; rc("GET"); +"/"; rc("DEL"); +" Commands" }
          h4 { +"Set, get, and delete keys" }
          redisCode(this@dslSlide, "string1.txt", "|1-2|3-4|5-6|7-8|9-10|11-12")
        }
      }

      dslSlide {
        content {
          h2 { rc("EXPIRE"); +"/"; rc("PERSIST"); +" Commands" }
          h4 { +"Expiring keys (1)" }
          redisCode(this@dslSlide, "string2.txt", "|1-2|3-4|5-6|7-8")
        }
      }

      dslSlide {
        content {
          h2 { rc("SET"); +" Command Options" }
          h4 { +"Expiring keys (2)" }
          redisCode(this@dslSlide, "string3.txt", "|1-2|3-4|5-6")
        }
      }

      dslSlide {
        content {
          h2 {
            rc("MSET"); +"/"
            rc("MGET"); +" Commands"
          }
          h4 { +"Set/Get multiple values" }
          redisCode(this@dslSlide, "mset-mget1.txt", "|1-2|3-7")
        }
      }

      dslSlide {
        content {
          h2 {
            rc("INCR"); +"/"
            rc("INCRBY"); +" Commands"
          }
          h4 { +"Increment a value (atomically)" }
          redisCode(this@dslSlide, "incr.txt", "|1-2|3-4|5-6|7-8|9-10|11-12")
        }
      }

      dslSlide {
        content {
          h2 {
            rc("DECR"); +"/"
            rc("DECRBY"); +" Commands"
          }
          h4 { +"Decrement a value (atomically)" }
          redisCode(this@dslSlide, "decr.txt", "|1-2|3-4|5-6|7-8|9-10|11-12")
        }
      }

      dslSlide {
        content { h1 { atag("Redis Lists", "https://redis.io/docs/data-types/lists/") } }
      }

      dslSlide {
        content {
          h2 { rc("LPUSH"); +"/"; rc("RPOP"); +"/"; rc("LLEN"); +" Commands" }
          h4 { +"Implementing a queue (first in, first out)" }
          redisCode(this@dslSlide, "list1.txt", "|1-2|3-4|5-6|7-8|9-10|11-12")
        }
      }

      dslSlide {
        content {
          h2 { rc("LPUSH"); +"/"; rc("LPOP"); +" Commands" }
          h4 { +"Implementing a stack (first in, last out)" }
          redisCode(this@dslSlide, "list2.txt", "|1-2|3-4|5-6|7-8")
        }
      }

//      dslSlide {
//        content {
//          h2 { command("LMOVE"); +"/"; command("LRANGE"); +" Commands" }
//          h4 { +"Moving items between lists" }
//          redisCode(this@dslSlide, "list3.txt", "[|1-2|3-4|5-6|7-8|9-10|]")
//        }
//      }

      dslSlide {
        content {
          h2 { rc("BLPOP"); +" Command" }
          h4 { +"Blocking list pop" }
          redisCode(this@dslSlide, "list4.txt", "|1|2-3|4|5-6|7|8-9|")
        }
      }

      /*
      dslSlide {
        content { h1 { atag("Redis Sets", "https://redis.io/docs/data-types/sets/") } }
      }

      dslSlide {
        content {
          h2 { rc("SADD"); +"/"; rc("SMEMBERS"); +"/"; rc("SCARD"); +" Commands" }
          h4 { +"Set creation and membership" }
          redisCode(this@dslSlide, "set1.txt", "|1-2|3-4|5-6|7-10|11-12")
        }
      }

      dslSlide {
        content {
          h2 { rc("SISMEMBER"); +" Command" }
          h4 { +"Set membership: does user 123 like books 742 and 299?" }
          redisCode(this@dslSlide, "set2.txt", "|1-2|3-4")
        }
      }

      dslSlide {
        content {
          h2 { rc("SINTER"); +" Command" }
          h4 { +"Set intersection: do users 123 and 456 have any favorite books in common?" }
          redisCode(this@dslSlide, "set3.txt")
        }
      }
      */
      dslSlide {
        content { h1 { atag("Redis Hashes", "https://redis.io/docs/data-types/hashes/") } }
      }

      dslSlide {
        content {
          h2 { rc("HSET"); +"/"; rc("HGET"); +"/"; rc("HGETALL"); +" Commands" }
          h4 { +"Save a basic user profile as a hash" }
          redisCode(this@dslSlide, "hash1.txt", "|1-2|3-4|5-11")
        }
      }

      dslSlide {
        content { h1 { atag("Programmatic APIs", "https://redis.io/docs/clients/") } }
      }

      dslSlide {
        content {
          h2 {
            atag("Jedis", "https://github.com/redis/jedis")
            +" Example (SET/GET/DEL)"
          }
          jedisCode(this@dslSlide, "strings/Set.kt", "|1|3|5-6|8|10-11")
        }
      }

      dslSlide {
        content {
          h2 {
            atag("Jedis", "https://github.com/redis/jedis")
            +" Example (HSET/HGETALL)"
          }
          jedisCode(this@dslSlide, "strings/HSet.kt", "|1|3-10|12-13")
        }
      }

      dslSlide {
        content { h1 { +"Use Cases" } }
      }

      dslSlide {
        content {
          h2 { +"Session Cache" }
          diagram("mermaid") {
            height = "375px"
            source =
              """
                flowchart TD
                    WC1("Web Client 1") <--> LB("Load Balancer")
                    WC2("Web Client 2") <--> LB
                    WC3("Web Client 3") <--> LB
                    LB <--> S1("HTTP Server 1") & S2("HTTP Server 2") & S3("HTTP Server 3")
                    S1 & S2 & S3 <--> R("Redis")
                    style R fill:#f66,stroke:#000,stroke-width:1px
              """
          }
          h4 { +"Cache user profiles, high scores, page hits, shopping cart items, last 10 user posts" }
          h4 { +"Rate Limiter" }
        }
      }

      dslSlide {
        content {
          h2 { +"Saving User Profile using HSET" }
          jedisCode(this@dslSlide, "cache/MapValues.kt", "|1-5|7-11|12|13|15|16|17")
        }
      }

      dslSlide {
        content {
          h2 { +"Saving User Profile using SET" }
          jedisCode(this@dslSlide, "cache/JsonValues.kt", "|1-6|8|9|10|12|13|14")
        }
      }

      // https://redis.io/commands/incr/
      dslSlide {
        content {
          h2 { +"Naive Rate Limiter using INCR (1)" }
          redisCode(this@dslSlide, "ratelimit1.txt", "|1|2|3|4-7|8|9-10|11-13")
          h4 { +"Limit each IP to 10 requests/sec" }
        }
      }

      dslSlide {
        content {
          h2 { +"Naive Rate Limiter using INCR (2)" }
          redisCode(this@dslSlide, "ratelimit2.txt", "|1|2|3-4|5|6-9|10|11")
          h4 { +"Limit each IP to 10 requests/sec" }
        }
      }

      dslSlide {
        content {
          h2 { +"Naive Rate Limiter using a List" }
          redisCode(this@dslSlide, "ratelimit3.txt", "|1|2|3-4|5|6|7-10|11-13|14|15")
          h4 { +"Limit each IP to 10 requests/sec" }
        }
      }

      dslSlide {
        content {
          h2 { +"Work Distribution" }
          diagram("mermaid") {
            height = "275px"
            source =
              """
              flowchart TD
                  WS["Work Submitter"] --> R["Redis"]
                  R <--> S1["Work Executor 1"] & S2["Work Executor 2"] & S3["Work Executor 3"]
                  style R fill:#f66,stroke:#000,stroke-width:1px
              """
          }
          h4 { +"Distribute compute-intensive jobs" }
        }
      }

      dslSlide {
        content {
          h2 { +"Submit Work" }
          redisCode(this@dslSlide, "worksubmit.txt", "|1|2")
        }
      }

      dslSlide {
        content {
          h2 { +"Execute Work" }
          redisCode(this@dslSlide, "workperform.txt", "|1|2,5|3|4")
        }
      }

      dslSlide {
        content {
          h2 { +"WorkDesc Class" }
          jedisCode(this@dslSlide, "workers/WorkDesc.kt", "")
        }
      }

      dslSlide {
        content {
          h2 { +"Submit Work" }
          jedisCode(this@dslSlide, "workers/SubmitWork.kt", "|1,7|2|3|4|5|6")
        }
      }

      dslSlide {
        content {
          h2 { +"Execute Work" }
          jedisCode(this@dslSlide, "workers/ExecuteWork.kt", "|1|3|4,11|5,10|6,9|7|8")
        }
      }

      dslSlide {
        content { h1 { atag("Redis Pub/Sub", "https://redis.io/docs/manual/pubsub/") } }
      }

      dslSlide {
        content {
          h3 { rc("SUBSCRIBE"); +"/"; rc("UNSUBSCRIBE"); +"/"; rc("PUBLISH"); +" Commands" }
          h4 { +"Event-based programming" }
          redisCode(this@dslSlide, "pubsub1.txt", "|1-3|5-6|4")
        }
      }

      dslSlide {
        content {
          h3 { rc("PSUBSCRIBE"); +"/"; rc("PUNSUBSCRIBE"); +" Commands" }
          h4 { +"Pattern-matching subscriptions" }
          redisCode(this@dslSlide, "pubsub2.txt", "|1-2|5-6|3|7-8|4")
        }
      }

      dslSlide {
        content {
          h2 { +"Stock Ticker Prices" }
          diagram("mermaid") {
            height = "375px"
            source =
              """
              flowchart TB
                  WC1("Web Client 1") & WC2("Web Client 2") & WC3("Web Client 3") <--> WS("Web Server")
                  WS <--> R("Redis")
                  R <--> PS1("NYSE\nPrice Source") & PS2("NASDAQ\nPrice Source") & PS3("CBOT\nPrice Source")
                  style R fill:#f66,stroke:#000,stroke-width:1px
              """
          }
          h4 { +"Distributing stock ticker prices" }
        }
      }

      dslSlide {
        content {
          h2 { +"Publish Stock Prices" }
          jedisCode(this@dslSlide, "pubsub/Publish.kt", "|1,15|2-6|8-9|11-12|14")
        }
      }

      dslSlide {
        content {
          h2 { +"Subscribe to a Stock Price" }
          jedisCode(this@dslSlide, "pubsub/Subscribe.kt", "|1-11|2-4|13-15|17-19|21")
        }
      }

      // https://architecturenotes.co/redis/
    }
  }
}
