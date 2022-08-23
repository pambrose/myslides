import Utils.linkItem
import Utils.textItem
import com.kslides.KSlides
import com.kslides.atag
import com.kslides.codeSnippet
import com.kslides.include
import com.kslides.slide.DslSlide
import com.kslides.unorderedList
import kotlinx.css.*
import kotlinx.css.Float
import kotlinx.html.*

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

      presentationConfig {
        topRightHref = "#/redis"
        enableMermaid = true
      }

      fun SECTION.redisSlide(dslSlide: DslSlide, fileName: String, highlight: String = "") =
        codeSnippet {
          language = "bash"
          copyButton = false
          if (highlight.isNotEmpty())
            highlightPattern = highlight
          +dslSlide.include("src/main/kotlin/redis/$fileName")
        }

      dslSlide {
        content {
          id = "c"
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
            textItem("What is Redis?"),
            textItem("Getting it up and running"),
            textItem("Data Types"),
            linkItem("Commands", "https://redis.io/commands/"),
            textItem("Use Cases"),
            textItem("Programmatic Access"),
          ) {
            style = "font-size:30px; padding-left: 80px"
          }
        }
      }

      // https://collabnix.com/how-to-setup-and-run-redis-in-a-docker-container/

      dslSlide {
        content {
          h3 { +"🔦 What is Redis?" }
          unorderedList(
            "REmote DIctionary Server",
            "Fast NoSQL database written in ANSI C",
            "An in-memory database -- all data is stored in RAM",
            "Based on the key-value model",
            "All access is via a key",
            "Meant to be run as a network-atached server",
            "Simple TCP protocol, which is implmented in many languages",
            "Can act as a cache, database, pub-sub, and message broker",
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
            
            brew install redis  
            brew install iredis
            
            
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
            
            docker run -d -p 6379:6379 --name myredis redis
            
            
            ``` 
            or   
                 
            ```bash
            
            redis-server      
            
            
            ```                    
          """
        }
      }

      markdownSlide {
        content {
          """
            ## Start a Redis Client
                             
            ```bash
            
            redis-cli      
            
            
            ```    
            or
            ```bash
            
            iredis      
            
            
            ```                
          """
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
                linkItem("Sets", "https://redis.io/docs/data-types/sets/"),
                linkItem("Hashes", "https://redis.io/docs/data-types/hashes/"),
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
                "MSET/MGET",
                "INCR/DECR",
                "LPUSH/LPOP/RPOP/LLEN",
              ) { style = fmt }
            }
            div("column2") {
              unorderedList(
                "LMOVE/LRANGE/BLPOP",
                "SADD/SMEMBERS/SISMEMBER",
                "SINTER/SCARD",
                "HSET/HGET/HGETALL",
                "SUBSCRIBE/UNSUBSCRIBE/PUBLISH",
              ) { style = fmt }
            }
          }
        }
      }

      dslSlide {
        content { h1 { +"String Commands" } }
      }

      dslSlide {
        content {
          h2 {
            atag("SET", "https://redis.io/commands/set/")
            +"/"
            atag("GET", "https://redis.io/commands/get/")
            +"/"
            atag("DEL", "https://redis.io/commands/del/")
            +" Commands"
          }
          h4 { +"Set, get, and delete keys" }
          redisSlide(this@dslSlide, "set-get1.txt", "[|1-2|3-4|5-6|7-8|9-10|11-12|]")
        }
      }

      dslSlide {
        content {
          h2 {
            +"Timed "
            atag("SET", "https://redis.io/commands/set/")
            +" Commands"
          }
          h4 { +"Expiring keys" }
          redisSlide(this@dslSlide, "set-get2.txt", "[|1-2|3-4|5-6|]")
        }
      }

      dslSlide {
        content {
          h2 {
            atag("MSET", "https://redis.io/commands/mset/")
            +"/"
            atag("MGET", "https://redis.io/commands/mget/")
            +" Commands"
          }
          h4 { +"Set multiple values" }
          redisSlide(this@dslSlide, "mset-mget1.txt", "[|1-2|3-6|]")
        }
      }

      dslSlide {
        content {
          h2 {
            atag("INCR", "https://redis.io/commands/incr/")
            +"/"
            atag("INCRBY", "https://redis.io/commands/incrby/")
            +" Commands"
          }
          h4 { +"Increment a value" }
          redisSlide(this@dslSlide, "incr.txt", "[|1-2|3-4|5-6|7-8|9-10|]")
        }
      }

      dslSlide {
        content {
          h2 {
            atag("DECR", "https://redis.io/commands/decr/")
            +"/"
            atag("DECRBY", "https://redis.io/commands/decrby/")
            +" Commands"
          }
          h4 { +"Decrement a value" }
          redisSlide(this@dslSlide, "decr.txt", "[|1-2|3-4|5-6|7-8|9-10|]")
        }
      }

      dslSlide {
        content { h1 { +"List Commands" } }
      }

      dslSlide {
        content {
          h2 {
            atag("LPUSH", "https://redis.io/commands/lpush/")
            +"/"
            atag("RPOP", "https://redis.io/commands/rpop/")
            +"/"
            atag("LLEN", "https://redis.io/commands/llen/")
            +" Commands"
          }
          h4 {
            +"Implementing a queue (first in, first out)"
          }
          redisSlide(this@dslSlide, "list1.txt", "[|1-2|3-4|5-6|7-8|9-10|11-12|]")
        }
      }

      dslSlide {
        content {
          h2 {
            atag("LPUSH", "https://redis.io/commands/lpush/")
            +"/"
            atag("LPOP", "https://redis.io/commands/lpop/")
            +" Commands"
          }
          h4 { +"Implementing a stack (first in, last out)" }
          redisSlide(this@dslSlide, "list2.txt", "[|1-2|3-4|5-6|7-8|]")
        }
      }

      dslSlide {
        content {
          h2 {
            atag("LMOVE", "https://redis.io/commands/lmove/")
            +"/"
            atag("LRANGE", "https://redis.io/commands/lrange/")
            +" Commands"
          }
          h4 { +"Moving items between lists" }
          redisSlide(this@dslSlide, "list3.txt", "[|1-2|3-4|5-6|7-8|9-10|]")
        }
      }

      dslSlide {
        content {
          h2 {
            atag("BLPOP", "https://redis.io/commands/blpop/")
            +" Command"
          }
          h4 { +"Blocking list pop" }
          redisSlide(this@dslSlide, "list4.txt", "[|1|2-3|4|5-6|]")
        }
      }

      dslSlide {
        content { h1 { +"Set Commands" } }
      }

      dslSlide {
        content {
          h2 {
            atag("SADD", "https://redis.io/commands/sadd/")
            +"/"
            atag("SMEMBERS", "https://redis.io/commands/smembers/")
            +" Commands"
          }
          h4 { +"Set creation and membership" }
          redisSlide(this@dslSlide, "set1.txt", "[|1-2|3-4|5-6|7-8|9-12|]")
        }
      }

      dslSlide {
        content {
          h2 {
            atag("SISMEMBER", "https://redis.io/commands/sismember/")
            +" Command"
          }
          h4 { +"Set membership: does user 123 like books 742 and 299?" }
          redisSlide(this@dslSlide, "set2.txt", "[|1-2|3-4|]")
        }
      }

      dslSlide {
        content {
          h2 {
            atag("SINTER", "https://redis.io/commands/sinter/")
            +" Command"
          }
          h4 { +"Set intersection: do users 123 and 456 have any favorite books in common?" }
          redisSlide(this@dslSlide, "set3.txt")
        }
      }

      dslSlide {
        content {
          h2 {
            atag("SCARD", "https://redis.io/commands/scard/")
            +" Command"
          }
          h4 { +"Set size: how many books has user 123 favorited?" }
          redisSlide(this@dslSlide, "set4.txt")
        }
      }

      dslSlide {
        content { h1 { +"Hashes" } }
      }

      dslSlide {
        content {
          h2 {
            atag("HSET", "https://redis.io/commands/hset/")
            +"/"
            atag("HGET", "https://redis.io/commands/hget/")
            +"/"
            atag("HGETALL", "https://redis.io/commands/hgetall/")
            +" Commands"
          }
          h4 { +"Save a basic user profile as a hash" }
          redisSlide(this@dslSlide, "hash1.txt", "|1-2|3-4|5-11|")
        }
      }

      dslSlide {
        content { h1 { +"Publish/Subscribe" } }
      }

      dslSlide {
        content {
          h3 {
            atag("SUBSCRIBE", "https://redis.io/commands/subscribe/")
            +"/"
            atag("UNSUBSCRIBE", "https://redis.io/commands/unsubscribe/")
            +"/"
            atag("PUBLISH", "https://redis.io/commands/publish/")
            +" Commands"
          }
          h4 { +"Publish/Subscribe" }
          redisSlide(this@dslSlide, "pubsub1.txt", "[|1-3|5-6|4|]")
        }
      }

      dslSlide {
        content {
          h3 {
            atag("PSUBSCRIBE", "https://redis.io/commands/psubscribe/")
            +"/"
            atag("PUNSUBSCRIBE", "https://redis.io/commands/punsubscribe/")
            +" Commands"
          }
          h4 { +"Pattern-matching subscriptions" }
          redisSlide(this@dslSlide, "pubsub2.txt", "|1-2|5-6|3|7-8|4|")
        }
      }

      dslSlide {
        content { h1 { +"Use Cases" } }
      }

      dslSlide {
        content {
          h2 { +"Session Cache" }
          mermaid(
            """
                flowchart TD
                    A[Load Balancer]
                    A --> B[Server 1]
                    A --> C[Server 2]
                    A --> D[Server 3]
                    B --> E[Redis]
                    C --> E
                    D --> E
              """
          )
          h4 {
            +"Profiles, high scores, page hits, shopping cart items"
          }
        }
      }

      dslSlide {
        content {
          h2 { +"Work Distribution" }
          mermaid(
            """
                flowchart TD
                    A[Work Submitter]
                    A --> B[Redis]
                    B <--> C[Server 1]
                    B <--> D[Server 2]
                    B <--> E[Server 3]
              """
          )
//          h4 {
//            +"Compute intensive jobs"
//          }
        }
      }


      // Cache
      // Give an example in SET with rate limiting
      // https://architecturenotes.co/redis/
    }
  }
}