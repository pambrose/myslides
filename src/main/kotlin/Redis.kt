import Utils.alink
import Utils.linkItem
import Utils.textItem
import com.kslides.KSlides
import com.kslides.codeSnippet
import com.kslides.include
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
      }

      fun FlowOrInteractiveOrPhrasingContent.image(fname: String, widthPx: Int = 500) {
        img { src = "images/redis/$fname"; width = "${widthPx}px" }
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
            linkItem("Commands", "https://redis.io/commands/"),
            textItem("Kotlin"),
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
          """
        }
      }

      dslSlide {
        content {
          h2 { +"🚌 Redis Commands"; style = "margin-bottom:20px;" }
          div("multiColumn2") {
            val fmt = "font-size:30px; padding-top:10px;"
            div("column2") {
              //p { +"Header 1"; style = "color: red;" }
              unorderedList("SET/GET", "MSET/MGET", "Item 3", "Item 4") { style = fmt }
            }
            div("column2") {
              //p { +"Header 2"; style = "color: red;" }
              unorderedList("Item 5", "Item 6", "Item 7", "Item 8") { style = fmt }
            }
          }
        }
      }

      dslSlide {
        content {
          h2 {
            alink("SET", "https://redis.io/commands/set/")
            +"/"
            alink("GET", "https://redis.io/commands/get/")
            +" Commands (1)"
          }
          codeSnippet {
            language = "bash"
            highlightPattern = "[|1|2|3|4|5|6|7|8|9|10|]"
            +include("src/main/kotlin/redis/set-get1.txt")
          }
        }
      }

      dslSlide {
        content {
          h2 {
            alink("SET", "https://redis.io/commands/set/")
            +"/"
            alink("GET", "https://redis.io/commands/get/")
            +" Commands (2)"
          }
          codeSnippet {
            language = "bash"
            highlightPattern = "[|1|3|5|]"
            +include("src/main/kotlin/redis/set-get2.txt")
          }
        }
      }

      dslSlide {
        content {
          h2 {
            alink("MSET", "https://redis.io/commands/mset/")
            +"/"
            alink("MGET", "https://redis.io/commands/mget/")
            +" Commands"
          }
          codeSnippet {
            language = "bash"
            highlightPattern = "[|1|2|3|4-6|]"
            +include("src/main/kotlin/redis/mset-mget1.txt")
          }
        }
      }

      dslSlide {
        content {
          h2 {
            alink("INCR", "https://redis.io/commands/incr/")
            +"/"
            alink("INCRBY", "https://redis.io/commands/incrby/")
            +" Command"
          }
          codeSnippet {
            language = "bash"
            highlightPattern = "[|1-2|3|4|5-6|7|8|9-10|]"
            +include("src/main/kotlin/redis/incr.txt")
          }
        }
      }

      dslSlide {
        content {
          h2 {
            alink("DECR", "https://redis.io/commands/decr/")
            +"/"
            alink("DECRBY", "https://redis.io/commands/decrby/")
            +" Command"
          }
          codeSnippet {
            language = "bash"
            highlightPattern = "[|1-2|3|4|5-6|7|8|9-10|]"
            +include("src/main/kotlin/redis/decr.txt")
          }
        }
      }

    }
  }
}