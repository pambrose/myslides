import com.kslides.PlaygroundTheme
import com.kslides.PresentationTheme
import com.kslides.Speed
import com.kslides.Transition
import com.kslides.codeSnippet
import com.kslides.include
import com.kslides.kslides
import com.kslides.listHref
import com.kslides.permuteBy
import com.kslides.playground
import com.kslides.toLinePatterns
import com.kslides.unorderedList
import kotlinx.css.*
import kotlinx.css.properties.*
import kotlinx.html.*

fun main() {

  kslides {

    val blue = "#258BD2;"

    output {
      // Write the presentation html files to /docs for Github Pages or netlify.com
      enableFileSystem = true

      // Run locally or on Heroku
      enableHttp = true
    }

    // CSS values assigned here are applied to all the presentations
    css +=
      """
      #githubCorner path { fill: #258BD2; }
      """

    presentationConfig {
      history = true
      transition = Transition.SLIDE
      transitionSpeed = Speed.SLOW

      topLeftHref = "https://github.com/pambrose/myslides"
      topLeftTitle = "View presentation source on Github"

      topRightHref = ""
      topRightTitle = "Go to 1st Slide"
      topRightText = "🏠"

      enableMenu = true
      theme = PresentationTheme.SOLARIZED
      slideNumber = "c/t"

      playgroundConfig {
        theme = PlaygroundTheme.DARCULA
        lines = true
        style = "border:none;"
        width = "100%"
        height = "250px"

        css {
          rule(".CodeMirror pre") {
            lineHeight = LineHeight("25px")
          }

          rule(".CodeMirror") {
            fontSize = LinearDimension("20px")
          }

          rule(".code-output") {
            lineHeight = LineHeight("25px")
            fontSize = LinearDimension("20px")
          }
        }
      }
    }

    presentation {
      markdownSlide {
        content {
          """
          # Paul's Slides
          
          * [HTTP and REST Basics](http.html)
          * [Functional Programming](functional.html)
          """
        }
      }
    }

    presentation {
      path = "http.html"

      css += """
        .reveal h2 {
          color: red;
        }
        
        img[alt=upload-image] { 
          width: 300px; 
        }
        
        img[alt=cloud-image] { 
          width: 500px; 
        }
        
        .column2 {
          float: left;
          width: 50%;
        }
        
        .column2 li {
          margin-bottom:10px;
        }
        
        /* Clear floats after the columns */
        .multiColumn2:after {
          content: "";
          display: table;
          clear: both;
        }

        .column3 {
          float: left;
          width: 33%;
        }
        
        .column3 li {
          margin-bottom:10px;
        }
        
        /* Clear floats after the columns */
        .multiColumn3:after {
          content: "";
          display: table;
          clear: both;
        }
      """

      css {
        rule(".playitems a") {
          fontSize = LinearDimension("30px")
        }
      }

      presentationConfig {
        topRightHref = "#/http"
      }

      markdownSlide {
        id = "http"
        classes = "title-slide"
        content {
          """
          ![upload-image](https://img.ifunny.co/images/0b6a5f5a9dbc19563eb13dcd7ae3e984547fe2a80fd9ce736119fc21f95d96d3_1.jpg)

          ## HTTP and REST Basics  
          https://pambrose.github.io/myslides          
          """
        }
      }

      markdownSlide {
        content {
          """
          ## What are we talking about today?  

          * The Internet is a huge part of our lives 
          
          * At the heart of everything we want to build 
          
          * Bird's eye view
          """
        }
      }

      markdownSlide {
        content {
          """
          ## How did we get here?  

          ![history-image](images/timeline.jpeg)
          """
        }
      }

      dslSlide {
        content {
          h2 { +"HTTP Usage Progression" }

          div {
            style = "padding-top: 10px; padding-bottom: 10px; outline: 3px solid $blue"
            p { +"Static Browser Content" }
            +"👇"
            p { +"Dynamic Browser Content" }
            +"👇"
            p { +"Dynamic Programmable Content" }
          }
        }
      }

      markdownSlide {
        content {
          """
          ## Cloud Computing Universe
          
          ![cloud-image](https://upload.wikimedia.org/wikipedia/commons/thumb/b/b5/Cloud_computing.svg/1920px-Cloud_computing.svg.png)
          """
        }
      }

      markdownSlide {
        content {
          """
          ## Cloud Endpoints
          
          * [Google Vision API](https://cloud.google.com/vision)
          * [Google Translation API](https://cloud.google.com/translate)
          * [Google Speech to Text API](https://cloud.google.com/speech-to-text)
          * [Google Natural Language API](https://cloud.google.com/natural-language)
          * [Twilio API](https://www.twilio.com/docs/)
          * [Expedia API](https://expediapartnersolutions.com/products/api)
          * [Alexa API](https://developer.amazon.com/en-US/alexa/alexa-skills-kit/get-deeper/dev-tools-skill-management-api)
          * [And many, many more APIs...](https://rapidapi.com/hub)
          """
        }
      }

      markdownSlide {
        content {
          """          
          ## HTTP Toolkit Demo
          
          ![history-image](https://www.drupal.org/files/project-images/proxy.png)
          """
        }
      }

      markdownSlide {
        content {
          """
          ## Connecting to a Network   
          
          * IP Address: 230.168.123.12 
          
          * 4,294,967,296 possible addresses -- Subnets
          
          * Domain name: www<span>&#46;</span>example<span>&#46;</span>com
          
          * Port: 80, 22, 8080 
                             
          * Network packets: TCP, UDP   
          """
        }
      }

      dslSlide {
        content {
          h2 { style = "margin-bottom:20px;"; +"Network Protocols" }
          div("multiColumn2") {
            val fmt = "font-size:30px; padding-top:10px; list-style-type:circle;"
            div("column2") {
              p { style = "color: red;"; +"TCP" }
              unorderedList("Transmission Control Protocol", "Connection-oriented", "Reliable", "Slow") {
                style = fmt
              }
            }
            div("column2") {
              p { style = "color: red;"; +"UDP" }
              unorderedList("User Datagram Protocol", "Connectionless", "Not guaranteed", "Fast") {
                style = fmt
              }
            }
          }
        }
      }

      markdownSlide {
        content {
          """
          ## Protocols using UDP  
                    
          * Media streaming (lost frames are ok)
          
          * VOIP 
          
          * Games that don't care if you get every update
          
          * Local broadcast mechanisms (different machines "discovering" each other)

          """
        }
      }

      markdownSlide {
        content {
          """
          ## Protocols using TCP  
          
          * SSH (22), FTP (21), telnet (23)
          
          * SMTP (25) -- sending mail
          
          * IMAP (143), POP (110) -- receiving mail
          
          * HTTP (80), HTTPS (443)
          """
        }
      }

      markdownSlide {
        content {
          """
          ## HTTP Protocol 
          
          ![cs-image](images/client-server-transparent.png)

          * Asymmetric client/server
          * Synchronous request/response
          * Non-binary data -- you can read it!
          * Simple, easy to use, easy to implement
          """
        }
      }

      markdownSlide {
        content {
          """
          ## HTTP Methods  
          
          * ✅ GET
          * ✅ POST
          * PUT
          * DELETE
          * HEAD
          * OPTIONS
          * TRACE
          * CONNECT
         """
        }
      }

      // https://www.geeksforgeeks.org/components-of-a-url/
      markdownSlide {
        content {
          """
          ## URL Components  
          
          <span style="font-size:60%;color:#016638;">
          https://www.example.co.uk:443/blog/article/search?docid=720<span>&#38;</span>hl=en#dayone
          </span>
          
          ![url-image](images/url-parts.png)
          """
        }
      }

      dslSlide {
        content {
          h2 { +"HTTP Headers" }
          div("multiColumn3") {
            val fmt = "font-size:30px; list-style-type:square;"
            style = "padding-top:30px;"
            div("column3") {
              unorderedList("Content-Type", "Content-Length", "Connection", "Host", "Referer") {
                style = fmt
              }
            }
            div("column3") {
              unorderedList("Accept", "Accept-Encoding", "Accept-Language", "Cache-Control", "User-Agent") {
                style = fmt
              }
            }
            div("column3") {
              unorderedList("Cookie", "Authorization", "If-Modified-Since", "If-Unmodified-Since", "If-None-Match") {
                style = fmt
              }
            }
          }
        }
      }

      dslSlide {
        content {
          h2 { +"HTTP Status Codes" }
          div("multiColumn3") {
            val fmt = "font-size:30px;"
            div("column3") {
              unorderedList(
                "200 OK",
                "201 Created",
                "202 Accepted",
                "204 No Content",
                "301 Moved Permanently",
                "302 Found",
                "304 Not Modified",
              ) { style = fmt }
            }
            div("column3") {
              unorderedList(
                "400 Bad Request",
                "401 Unauthorized",
                "403 Forbidden",
                "404 Not Found",
                "405 Method Not Allowed",
                "409 Conflict",
              ) { style = fmt }
            }
            div("column3") {
              unorderedList(
                "500 Internal Server Error",
                "501 Not Implemented",
                "502 Bad Gateway",
                "503 Service Unavailable",
                "504 Gateway Timeout"
              ) { style = fmt }
            }
          }
        }
      }

      dslSlide {
        content {
          h2 { +"HTTP Response Payloads" }
          div("multiColumn2") {
            style = "padding-top:30px;"
            div("column2") {
              unorderedList(
                "HTML",
                "JSON",
                "XML",
                "Plain Text",
                "JS/CSS",
              )
            }
            div("column2") {
              unorderedList(
                "Binary Data",
                "JPG/PNG/GIF",
                "Audio",
                "Video",
                "Other"
              )
            }
          }
        }
      }
      markdownSlide {
        content {
          """
          ## HTML Response
          
          ```html [1-10 | 1,10 | 2-5 | 7-9 | ]
          ${include("src/main/resources/html-example.html")}
          ```
          """
        }
      }

      for (lines in "[8-12 | 3-12 | 2-13 | ]".toLinePatterns().zip(listOf(3, 3, 2, 1))) {
        dslSlide {
          autoAnimate = true
          content {
            h2 { +"JSON Response" }
            val file = "src/main/resources/json-example.json"
            codeSnippet {
              dataId = "code-animation"
              language = "json"
              lineOffSet = lines.second
              +include(file, linePattern = lines.first)
            }
          }
        }
      }

      markdownSlide {
        content {
          """
          ## HTTP Clients
                    
          * Browser: Chrome, Safari, IE
          
          * CLI: httpie, curl, wget

          * Code: Kotlin, Java, JS, Python
          """
        }
      }

      markdownSlide {
        content {
          """
          ## HTTP Servers
            
          * Static Servers: Apache, IIS, Nginx
          
          * Dynamic Servers: Ktor, Spring, Express, Flask
          """
        }
      }

      dslSlide {
        content {
          h2 { +"Sandbox Repos" }

          div("playitems") {
            unorderedList(
              { listHref("https://github.com/athenian-programming/static-http-demo") },
              { listHref("https://github.com/athenian-programming/heroku-demo") },
              { listHref("https://github.com/athenian-programming/web-services-demo") },
              { listHref("https://github.com/athenian-programming/web-database-demo") },
              { listHref("https://github.com/athenian-programming/web_flutter_demo") },
            ) {
              style = "padding-top:30px; list-style-type:square;"
            }
          }
        }
      }
    }

    presentation {
      path = "functional.html"

      presentationConfig {
        topRightHref = "#/functional"
      }

      dslSlide {
        content {
          h2 { +"Lambdas and Higher-Order Functions" }
          img { src = "images/lambda.png" }
        }
      }

//      dslSlide {
//        content {
//          h2 { +"Imperative vs Declarative Programming" }
//          br {}
//          p("fragment fade-right") {
//            b { +"Imperative programming " }
//            +"is a programming paradigm that uses statements that change a program’s state."
//          }
//          br {}
//          p("fragment fade-right") {
//            b { +"Declarative programming " }
//            +"is a programming paradigm that expresses the logic of a computation without describing its control flow."
//          }
//        }
//      }

      dslSlide {
        id = "unclebob"
        content {
          h2 { +"Uncle Bob Quote" }
          blockQuote(classes = "twitter-tweet tw-align-center") {
            style = ""
            p {
              lang = "en"
              dir = Dir.ltr
              +"Procedural programming is good."
              +"Object oriented programming is good."
              +"Functional programming is good."
              +"Programming with all three is best."
            }
            +"&mdash; Uncle Bob Martin (@unclebobmartin)"
            a {
              href = "https://twitter.com/unclebobmartin/status/1522904306948657160?ref_src=twsrc%5Etfw"
              +"May 7, 2022"
            }
          }
          script {
            async = true
            src = "https://platform.twitter.com/widgets.js"
            charset = "utf-8"
          }
        }
      }

      // https://hmkcode.com/kotlin/kotlin-function-type-lambda/
      // https://doordash.engineering/2022/03/22/how-to-leverage-functional-programming-in-kotlin-to-write-better-cleaner-code/

      dslSlide {
        content {
          h2 { +"Fundamental Types" }
          playground("src/main/kotlin/lambdas/FundamentalTypes.kt") {
            height = "400px"
            foldedButton = false
          }
        }
      }

      dslSlide {
        content {
          h2 { +"Object Types" }
          playground("src/main/kotlin/lambdas/ObjectTypes.kt") {
            height = "400px"
            foldedButton = false
          }
        }
      }

      dslSlide {
        content {
          h2 { +"Function Invocation" }
          playground("src/main/kotlin/lambdas/FunctionInvoke.kt") {
            height = "450px"
            foldedButton = false
          }
        }
      }

      dslSlide {
        content {
          h2 { +"Assigning a Function Reference" }
          playground("src/main/kotlin/lambdas/FuncReference1.kt") {
            height = "450px"
            foldedButton = false
          }
        }
      }

      dslSlide {
        content {
          h2 { +"Invoking a Function Reference" }
          playground("src/main/kotlin/lambdas/FuncReference2.kt") {
            height = "450px"
            foldedButton = false
          }
        }
      }

      dslSlide {
        content {
          h2 { +"How do we describe the type of a function?" }
        }
      }

      listOf("Name", "Parameters", "Return Type", "Body")
        .permuteBy(
          listOf(),
          listOf(0),
          listOf(0, 1),
          listOf(0, 1, 2),
          listOf(0, 1, 2, 3),
          listOf(1, 2, 3),
          listOf(1, 2),
        )
        .forEach { items ->
          dslSlide {
            autoAnimate = true
            content {
              h2 { +"Elements of a Function" }
              playground("src/main/kotlin/lambdas/BasicFunction.kt") {
                height = "220px"
                dataHighlightOnly = true
                foldedButton = false
              }
              unorderedList(*items.toTypedArray())
            }
          }
        }

      dslSlide {
        content {
          h2 { +"Specifying a Function Type" }
          playground("src/main/kotlin/lambdas/FuncReference3.kt") {
            height = "450px"
            foldedButton = false
          }
        }
      }

      dslSlide {
        content {
          h2 { +"Type Equivalence" }
          playground("src/main/kotlin/lambdas/FuncReference4.kt") {
            height = "450px"
            dataHighlightOnly = true
          }
        }
      }

      dslSlide {
        content {
          h2 { +"Break Through Moment #1 of 4" }
          img { width = "400px"; src = "images/breakthrough1.jpg" }
          h3 { style = "color: red;"; +"Functions are 1st Class Types!!" }
        }
      }

      dslSlide {
        content {
          h2 { +"Defining a Lambda" }
          playground("src/main/kotlin/lambdas/Lambda1.kt") {
            height = "400px"
            foldedButton = false
          }
        }
      }

      dslSlide {
        content {
          h2 { +"Lambda Simplification" }
          playground("src/main/kotlin/lambdas/Lambda2.kt") {
            height = "400px"
            dataHighlightOnly = true
            foldedButton = false
          }
        }
      }

      dslSlide {
        content {
          h2 { +"Single Arg Lambdas" }
          playground("src/main/kotlin/lambdas/Lambda3.kt") {
            height = "400px"
            dataHighlightOnly = true
            foldedButton = false
          }
        }
      }

      dslSlide {
        content {
          h2 { +"Lambda Examples" }
          playground("src/main/kotlin/lambdas/LambdaExample1.kt") {
            height = "400px"
            foldedButton = false
          }
        }
      }

      dslSlide {
        content {
          h2 { +"Lambda Examples" }
          playground("src/main/kotlin/lambdas/LambdaExample2.kt") {
            height = "400px"
            foldedButton = false
          }
        }
      }

      dslSlide {
        content {
          h2 { +"Lambda Examples" }
          playground("src/main/kotlin/lambdas/LambdaExample3.kt") {
            height = "400px"
            foldedButton = false
          }
        }
      }

      dslSlide {
        content {
          h2 { +"Break Through Moment #2 of 4" }
          img { width = "400px"; src = "images/breakthrough2.jpg" }
          h3 { style = "color: red;"; +"We can work with lambdas!!" }
        }
      }


      dslSlide {
        content {
          h2 { +"Break Through Moment #3 of 4" }
          img { width = "400px"; src = "images/breakthrough3.jpg" }
          h3 { style = "color: red;"; +"We can work with higher-order functions!!" }
        }
      }
    }
  }
}
