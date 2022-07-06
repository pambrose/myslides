import com.kslides.PlaygroundTheme
import com.kslides.PresentationTheme
import com.kslides.Speed
import com.kslides.Transition
import com.kslides.codeSnippet
import com.kslides.include
import com.kslides.kslides
import com.kslides.listHref
import com.kslides.orderedList
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
        dataAutocomplete = true
        matchBrackets = true
        foldedButton = false
        lines = true

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
          * [Unix Basics](unix.html)
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

      css += """
        .agenda li {
          padding-top: 10px;
        }

        .cbo li {
          padding-top: 10px;
        }

        .benefits li {
          padding-top: 10px;
        }
      """

      presentationConfig {
        topRightHref = "#/functional"
      }

      dslSlide {
        content {
          id = "functional"
          h2 { +"Lambdas and Higher-Order Functions" }
          img { src = "images/lambda.png" }
          a {
            href = "https://pambrose.github.io/myslides/functional.html"
            +"https://pambrose.github.io/myslides/functional.html"
          }
        }
      }

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
          h2 { +"Agenda" }
          div("agenda") {
            orderedList(
              "Functions as Types",
              "Lambdas",
              "Higher-Order Functions",
              "Collection-based Operations"
            ) {
              style = "color: red; font-size:35px; padding-top:5px;"
            }
          }
        }
      }

      dslSlide {
        content {
          h2 { style = "color: red;"; +"Functions as Types" }
        }
      }

      val lambdas = "src/main/kotlin/lambdas"
      dslSlide {
        content {
          h2 { +"Simple Types" }
          playground("$lambdas/SimpleTypes.kt") {
            height = "400px"
          }
        }
      }

      dslSlide {
        content {
          h2 { +"User Object Types" }
          playground("$lambdas/ObjectTypes.kt") {
            height = "400px"
          }
        }
      }

      dslSlide {
        content {
          h2 { +"Function Invocation" }
          playground("$lambdas/FunctionInvoke.kt") {
            height = "450px"
          }
        }
      }

      dslSlide {
        content {
          h2 { +"Assigning a Function Reference" }
          playground("$lambdas/FuncReference1.kt") {
            height = "450px"
          }
        }
      }

      dslSlide {
        content {
          h2 { +"Invoking a Function Reference" }
          playground("$lambdas/FuncReference2.kt") {
            height = "500px"
          }
        }
      }

      dslSlide {
        content {
          h2 { +"How do we describe the type of a function?" }
        }
      }

      listOf(
        "Name (sum)",
        "Parameters (a: Int, b: Int)",
        "Return Type (Int)",
        "Body (return a + b)",
        "Parameters (Int, Int)",
        "(Int, Int) -> Int"
      )
        .permuteBy(
          listOf(),
          listOf(0),
          listOf(0, 1),
          listOf(0, 1, 2),
          listOf(0, 1, 2, 3),
          listOf(1, 2, 3),
          listOf(1, 2),
          listOf(4, 2),
          listOf(4, 2, 5),
        )
        .forEach { items ->
          dslSlide {
            autoAnimate = true
            content {
              h2 { +"Elements of a Function" }
              codeSnippet {
                copyButton = false
                trim = false
                highlightPattern = "none"
                +include("$lambdas/BasicFunction.kt", "5-10")
              }
              unorderedList(*items.toTypedArray())
            }
          }
        }

      dslSlide {
        content {
          h2 { +"Specifying a Function Type" }
          playground("$lambdas/FuncReference3.kt") {
            height = "450px"
            dataHighlightOnly = true
          }
        }
      }

      dslSlide {
        content {
          h2 { +"Specifying a Function Type" }
          playground("$lambdas/FuncReference4.kt") {
            height = "450px"
            dataHighlightOnly = true
          }
        }
      }

      dslSlide {
        content {
          h2 { +"Specifying a Function Type" }
          playground("$lambdas/FuncReference5.kt") {
            height = "450px"
            dataHighlightOnly = true
          }
        }
      }

      dslSlide {
        content {
          h2 { +"Type Equivalence" }
          playground("$lambdas/FuncReference6.kt") {
            height = "450px"
            dataHighlightOnly = true
            foldedButton = true
          }
        }
      }

      dslSlide {
        content {
          h2 { +"Break Through Moment #1 of 4" }
          img { width = "400px"; src = "images/breakthrough1.jpg" }
          h3 { style = "color: red;"; +"We understand that functions are 1st class types!!" }
        }
      }

      dslSlide {
        content {
          h2 { style = "color: red;"; +"Lambdas" }
          p {
            +"Lambdas are nameless functions that can be passed around as values. "
            +"They are also called closures."
          }
        }
      }

      dslSlide {
        content {
          h3 { +"Defining and Invoking a Lambda" }
          playground("$lambdas/Lambda1.kt") {
            height = "550px"
          }
        }
      }

      dslSlide {
        content {
          h3 { +"Lambda Simplification" }
          playground("$lambdas/Lambda2.kt") {
            height = "400px"
            dataHighlightOnly = true
          }
        }
      }

      dslSlide {
        content {
          h3 { +"Single Parameter Lambdas" }
          playground("$lambdas/Lambda3.kt") {
            height = "400px"
            dataHighlightOnly = true
          }
        }
      }

      dslSlide {
        content {
          h3 { +"Lambda Examples" }
          playground("$lambdas/LambdaExample1.kt") {
            height = "400px"
          }
        }
      }

      dslSlide {
        content {
          h3 { +"Lambda Examples" }
          playground("$lambdas/LambdaExample2.kt") {
            height = "400px"
          }
        }
      }

      dslSlide {
        content {
          h3 { +"Lambda Examples" }
          playground("$lambdas/LambdaExample3.kt") {
            height = "400px"
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
          h2 { style = "color: red;"; +"Higher-Order Functions" }
          p {
            +"""
              A function that takes another function as an argument, 
              or returns a function as a result.
            """.trimIndent()
          }
          p {
            a {
              href = "https://en.wikipedia.org/wiki/Higher-order_function"; +"(Languages with Higher-Order Functions)"
            }
          }
        }
      }

      dslSlide {
        content {
          h3 { +"Functional Parameter" }
          playground("$lambdas/Hof1.kt") {
            height = "400px"
            dataHighlightOnly = true
          }
        }
      }

      dslSlide {
        content {
          h3 { +"Functional Argument" }
          playground("$lambdas/Hof2.kt") {
            height = "575px"
          }
        }
      }

      dslSlide {
        content {
          h3 { +"List of Functional Arguments" }
          playground("$lambdas/Hof3a.kt") {
            height = "550px"
          }
        }
      }

      dslSlide {
        content {
          h3 { +"List of Functional Arguments" }
          playground("$lambdas/Hof3b.kt") {
            height = "550px"
          }
        }
      }

      dslSlide {
        content {
          h3 { +"Functional Return Value" }
          playground("$lambdas/Hof4.kt") {
            height = "400px"
            dataHighlightOnly = true
          }
        }
      }

      dslSlide {
        content {
          h3 { +"Functional Return Value" }
          playground("$lambdas/Hof5.kt") {
            height = "500px"
          }
        }
      }

      dslSlide {
        content {
          h3 { +"Conditional Functional Return Value" }
          playground("$lambdas/Hof6.kt") {
            height = "550px"
          }
        }
      }

      dslSlide {
        content {
          h3 { +"Functional Argument and Return Value" }
          playground("$lambdas/Hof7.kt") {
            height = "550px"
          }
        }
      }

      dslSlide {
        content {
          h2 { +"Break Through Moment #3 of 4" }
          img { width = "400px"; src = "images/breakthrough3.jpg" }
          h3 { style = "color: red;"; +"We can work with higher-order functions!!" }
        }
      }

      dslSlide {
        content {
          h2 { style = "color: red;"; +"Collection-based Operations" }
          div("cbo") {
            unorderedList(
              "Filtering",
              "Transformations",
              "Grouping",
              "Retrieving collection parts",
              "Retrieving single elements",
              "Ordering",
              "Aggregate",
            ) {
              style = "color: red; font-size:30px;"
            }
          }
        }
      }

      dslSlide {
        content {
          h3 { +"Filter Calls" }
          playground("$lambdas/Filter1a.kt") {
            height = "500px"
          }
        }
      }

      dslSlide {
        content {
          h3 { +"Filter Calls" }
          playground("$lambdas/Filter1b.kt") {
            height = "500px"
          }
        }
      }

      dslSlide {
        content {
          h3 { +"Filter Calls" }
          playground("$lambdas/Filter2.kt") {
            height = "500px"
          }
        }
      }

      dslSlide {
        content {
          h3 { +"Method Chaining" }
          playground("$lambdas/Filter3.kt") {
            height = "500px"
          }
        }
      }

      dslSlide {
        content {
          h3 { +"Negated Filter Calls" }
          playground("$lambdas/Filter4.kt") {
            height = "500px"
          }
        }
      }

      dslSlide {
        content {
          h3 { +"Filter with Index Calls" }
          playground("$lambdas/Filter5.kt") {
            height = "400px"
          }
        }
      }

      dslSlide {
        content {
          h3 { +"Filter by Type and Null" }
          playground("$lambdas/Filter6.kt") {
            height = "400px"
          }
        }
      }

      dslSlide {
        content {
          h3 { +"Transformation Calls" }
          playground("$lambdas/Map1.kt") {
            height = "500px"
          }
        }
      }

      dslSlide {
        content {
          h3 { +"Transformation Calls" }
          playground("$lambdas/Map2.kt") {
            height = "500px"
          }
        }
      }

      dslSlide {
        content {
          h3 { +"Transformation Calls" }
          playground("$lambdas/Map3.kt") {
            height = "500px"
          }
        }
      }

      dslSlide {
        content {
          h3 { +"onEach() Calls" }
          playground("$lambdas/Map4.kt") {
            height = "500px"
          }
        }
      }

      dslSlide {
        content {
          h3 { +"also() Calls" }
          playground("$lambdas/Map5.kt") {
            height = "500px"
          }
        }
      }

      dslSlide {
        content {
          h3 { +"Combining Calls" }
          playground("$lambdas/Combo1.kt") {
            height = "400px"
          }
        }
      }

      dslSlide {
        content {
          h3 { +"Combining Calls" }
          playground("$lambdas/Combo2.kt") {
            height = "400px"
          }
        }
      }

      dslSlide {
        content {
          h3 { +"Combining Calls" }
          playground("$lambdas/Combo3.kt") {
            height = "500px"
          }
        }
      }

      dslSlide {
        content {
          h3 { +"Working with Strings" }
          playground("$lambdas/Combo4.kt") {
            height = "500px"
            foldedButton = true
          }
        }
      }

      dslSlide {
        content {
          h3 { +"Working with Strings" }
          playground("$lambdas/Combo5.kt") {
            height = "500px"
          }
        }
      }

      dslSlide {
        content {
          h3 { +"Aggregates - sum() Call" }
          playground("$lambdas/Aggregate1.kt") {
            height = "400px"
          }
        }
      }

      dslSlide {
        content {
          h3 { +"Aggregates - max() Call" }
          playground("$lambdas/Aggregate2.kt") {
            height = "400px"
          }
        }
      }

      dslSlide {
        content {
          h2 { +"Break Through Moment #4 of 4" }
          img { width = "400px"; src = "images/breakthrough4.jpg" }
          h3 { style = "color: red;"; +"We can use lambdas in collection operations!!" }
        }
      }

      dslSlide {
        content {
          h2 { +"Benefits of Functional Programming" }
          div("benefits") {
            unorderedList(
              "Expressive and concise",
              "Side-effect-free executions",
              "Easier to understand discrete components",
              "Fewer bugs",
              "Easy iterations on existing functions",
              "Easier concurrency",
              "Increased testability",
            ) {
              style = "color: red; font-size:30px; padding-top:5px;"
            }
          }
        }
      }

      dslSlide {
        content {
          h2 { +"Problems with Functional Programming" }
          div("benefits") {
            unorderedList(
              "Speed and memory",
              "O(N+N+N…)",
              "Problems can begin to appear at scale",
              "Abstraction can lead to sloppiness",
            ) {
              style = "color: red; font-size:30px; padding-top:5px;"
            }
          }
        }
      }

      dslSlide {
        content {
          h2 { +"What Have We Learned?" }
          div("agenda") {
            orderedList(
              "Functions are 1st class types",
              "We can succinctly define functions with lambdas",
              "Behavior can be used as values",
              "We can avoid mutations, loops and conditionals with lambdas and collection operations",
            ) {
              style = "color: red; font-size:35px; padding-left: 90px; padding-top:5px;"
            }
          }
        }
      }

      dslSlide {
        content {
          h2 { +"Big Picture Takeaways" }
          div("agenda") {
            unorderedList(
              "Thinking functionally will fundamentally change the way you solve problems.",
              "Lambdas will make you a better programmer!",
              "FP makes for beautiful and elegant code.",
              "Dive in!",
            ) {
              style = "color: red; font-size:35px; padding-left: 90px; padding-top:5px;"
            }
          }
        }
      }

      dslSlide {
        content {
          h2 { +"Thank You" }
        }
      }
    }

    presentation {
      path = "unix.html"

      css += """
        .history li {
          padding-top: 0px;
        }
      """

      presentationConfig {
        topRightHref = "#/unix"
      }

      dslSlide {
        content {
          id = "unix"
          h2 { +"Unix Basics" }
          a {
            href = "http://oldcomputers.net/att-unix-pc.html"
            target = "_blank"
            img { width = "400px"; src = "images/att-unix-pc-3b1.jpg" }
          }
          a {
            href = "https://pambrose.github.io/myslides/unix.html"
            +"https://pambrose.github.io/myslides/unix.html"
          }
        }
      }

      dslSlide {
        content {
          h2 { +"History of Unix" }
          unorderedList(
            "1969 at AT&T Bell Labs -- Unix was a trademark of AT&T",
            "Dennis Ritchie, Ken Thompson, and Brian Kernighan",
            "Assembly Language -> C Programming Language",
            "UC Berkeley and GNU",
            "HP, IBM, DEC, Sun, and others",
            "Minix, Linux, BSD, and others",
            "Android, OSX, IOS, and WatchOS"
          ) {
            style = "font-size:30px; padding-left: 90px"
          }
          a {
            href = "images/Unix_history.png"
            target = "_blank"
            img { width = "300px"; src = "images/Unix_history.png" }
          }
        }
      }

      dslSlide {
        content {
          h2 { +"What is an Operating System?" }
          h4 {
            style = "color: red;"
            +"A set of programs that control on a computer"
          }
          unorderedList(
            "Input/Output",
            "Memory Management",
            "Disk Access and File Systems",
            "Network",
            "Process Control/Scheduling",
            "Security",
            "System Logging/Monitoring/Maintenance",
            "System Backup/Recovery/Upgrade",
            "User Program Execution",
          ) {
            style = "font-size:30px; padding-left: 80px"
          }
        }
      }

      dslSlide {
        content {
          h2 { +"What is a shell?" }
          unorderedList(
            "sh",
            "csh",
            "bash",
            "ksh",
            "fish",
            "zsh",
          ) {
            style = "font-size:30px"
          }
        }
      }

      dslSlide {
        content {
          h2 { +"Shell Environment Variables" }
          unorderedList(
            "env",
            "echo",
            "\$PATH, \$HOME, \$USER, \$SHELL, \$JAVA_HOME, \$CLASSPATH",
            ".zshrc, .cshrc, .bashrc",
          ) {
            style = "font-size:30px; padding-left: 20px"
          }
        }
      }

      dslSlide {
        content {
          h2 { +"General Command Structure" }
          unorderedList(
            "man page",
            "tldr",
            "single and double dash options",
            "args[] in programs",
          ) {
            style = "font-size:30px"
          }
        }
      }

      dslSlide {
        content {
          h2 { +"File System Commands" }
          unorderedList(
            "Files, Directories, and Paths ( / .. . ../ ~ *)",
            "ls",
            "touch",
            "cat/more/less/bat",
            "mv",
            "rm",
            "file",
            "strings",
            "pwd",
            "cd",
            "pushd/popd",
            "mkdir, rmdir",
          ) {
            style = "font-size:30px; padding-left: 20px"
          }
        }
      }

      dslSlide {
        content {
          h2 { +"Unix Editors" }
          div {
            unorderedList(
              "vi, vim, and neovim",
              "emacs",
              "pico",
              "ed",
              "nano",
            ) {
              style = "font-size:30px; padding-left: 20px"
            }
          }
        }
      }

      dslSlide {
        content {
          h2 { +"Unix Files" }
          div {
            unorderedList(
              "The Unix Philosophy",
              "Command Interoperability",
              "stdin, stdout, and stderr",
              "Redirecting input/output",
              "< n> > 2> 2>&1 >>",
              "/dev/null",
              "chmod",
              "chgrp",
              "chown",
            ) {
              style = "font-size:30px; padding-left: 20px"
            }
          }
          img { width = "800px"; src = "images/diagram-shell-keyboard-terminal.png" }
        }
      }

      dslSlide {
        content {
          h2 { +"Common Patterns - Standard Input" }
          img { width = "1000px"; src = "images/diagram-input-examples.png" }
        }
      }

      dslSlide {
        content {
          h2 { +"Common Patterns - Standard Output" }
          img { width = "1000px"; src = "images/diagram-output-examples.png" }
        }
      }

      dslSlide {
        content {
          h2 { +"Pipes" }
          img { width = "1000px"; src = "images/diagram-cat-sort-uniq-pipeline.png" }
        }
      }

      verticalSlides {
        dslSlide {
          content {
            h2 { +"Text Processing" }
            unorderedList(
              "wc",
              "grep",
              "head, tail",
              "nl",
              "fold",
              "sort",
              "uniq",
              "rev",
              "sed",
              "tr",
              "cut",
            ) {
              style = "font-size:30px; padding-left: 20px"
            }
          }
        }

        markdownSlide {
          content {
            """
            ## tr examples
            ```bash
            head -n 1 ~/samples/data/top100.csv | tr ',' '\n'
            
            head -n 1 ~/samples/data/top100.csv | tr ',' '\n' | tr -d '"'
            
            echo "Welcome to the shell" | tr 'shell' 'machine'
            ```
          """
          }
        }

        markdownSlide {
          content {
            """
            ## cut examples
            ```bash
            cut -d',' -f 3 ~/samples/data/top100.csv | head
            
            cut -d',' -f 1,3 ~/samples/data/top100.csv | head
            
            tail -n 5 ~/samples/logs/web-server-logs.txt | cut -c 12-19
            
            ps -e | cut -c 26-500
            ```
          """
          }
        }
      }

      dslSlide {
        content {
          h2 { +"Searching" }
          codeSnippet {
            code = """
              find
              find . -type f
              find . -type d
              find . -name Utils.java
              find . -name "*.java"
              find . -name "*.java" -exec wc {} \;
            """
          }
        }
      }

      dslSlide {
        content {
          h2 { +"Misc Commands" }
          unorderedList(
            "ssh",
            "date, cal",
            "which, where, whereis",
            "split",
            "diff",
            "compress/uncompress",
          ) {
            style = "font-size:30px; padding-left: 20px"
          }
        }
      }

      dslSlide {
        content {
          h2 { +"Job/Process Control" }
          codeSnippet {
            code = """
              python3 -m http.server 8080
            """
          }
          unorderedList(
            "control-z, jobs, fg, bg, &",
            "top/htop",
            "ps",
            "kill",
          ) {
            style = "font-size:30px; padding-left: 20px"
          }
        }
      }

      dslSlide {
        content {
          h2 { +"Scripts" }
          codeSnippet {
            code = """
              #!/bin/sh
              echo "🐶 woof 🐶"
            """
          }
        }
      }

      dslSlide {
        content {
          h2 { +"User Management" }
          unorderedList(
            "w, who",
            "su",
            "root",
            "sudo",
            "adduser, rmuser",
          ) {
            style = "font-size:30px; padding-left: 20px"
          }
        }
      }

      dslSlide {
        content {
          h2 { +"Fun Commands" }
          unorderedList(
            "lynx",
            "say",
            "fortune",
            "cowsay",
            "pbpaste",
            "banner/figlet",
            "sl",
            "cmatrix",
            "asciiquarium",
          ) {
            style = "font-size:30px; padding-left: 20px"
          }
        }
      }

      dslSlide {
        content {
          h2 { +"Practice on your own" }
          a {
            href = "https://effective-shell.com"
            target = "_blank"
            +"Effective Shell"
          }
        }
      }
    }
  }
}
