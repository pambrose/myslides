import com.kslides.*
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

    presentationDefault {
      history = true
      transition = Transition.SLIDE
      transitionSpeed = Speed.SLOW
      githubCornerHref = "https://github.com/pambrose/myslides"
      githubCornerTitle = "View presentation source on Github"
      enableMenu = true
      theme = Theme.SOLARIZED
    }

    presentation {
      markdownSlide {
        content {
          """
          # Paul's Slides
          
          * [HTTP and REST Basics](rest.html)
          """
        }
      }
    }
    presentation {
      path = "rest.html"

      css += """
        .reveal h2 {
          color: red;
        }
        
        img[alt=upload-image] { 
          width: 400px; 
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
        .row2:after {
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
        .row3:after {
          content: "";
          display: table;
          clear: both;
        }
      """

      markdownSlide {
        classes = "title-slide"
        content {
          """
          ## HTTP and REST Basics  

          ![upload-image](https://img.ifunny.co/images/0b6a5f5a9dbc19563eb13dcd7ae3e984547fe2a80fd9ce736119fc21f95d96d3_1.jpg)
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
          h2 { +"HTTP Progression" }

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
          ## Cloud Endpoints
          
          * [Google Vision API](https://cloud.google.com/vision)
          * [Google Natural Language](https://cloud.google.com/natural-language)
          * [Google Translation API](https://cloud.google.com/translate)
          * [Google Speech to Text API](https://cloud.google.com/speech-to-text)
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
          ## HTTP In Action
          
          ### HTTP Toolkit Demo
          """
        }
      }

      markdownSlide {
        content {
          """
          ## Connecting to a Network   
          
          * IP Address: 230.168.123.12 
          
          * Domain name: www<span>&#46;</span>example<span>&#46;</span>com
          
          * Port: 80, 22, 8080 
          
          * Subnets
                   
          * Network packets: TCP, UDP   
          """
        }
      }

      dslSlide {
        content {
          h2 { style = "margin-bottom:20px;"; +"Network Protocols" }
          div("row2") {
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
          ## HTTP Clients
                    
          * Browser: Chrome, Firefox, Safari, IE
          
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
          div("row3") {
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
          div("row3") {
            val fmt = "font-size:30px;"
            id = "status-codes"
            div("column3") {
              unorderedList(
                "200 OK",
                "201 Created",
                "202 Accepted",
                "204 No Content",
                "301 Moved Permanently",
                "302 Found",
              ) {
                style = fmt
              }
            }
            div("column3") {
              unorderedList(
                "304 Not Modified",
                "400 Bad Request",
                "401 Unauthorized",
                "403 Forbidden",
                "404 Not Found",
                "405 Method Not Allowed",
              ) {
                style = fmt
              }
            }
            div("column3") {
              unorderedList(
                "409 Conflict\n",
                "500 Internal Server Error",
                "501 Not Implemented",
                "502 Bad Gateway",
                "503 Service Unavailable",
                "504 Gateway Timeout"
              ) {
                style = fmt
              }
            }
          }
        }
      }

      markdownSlide {
        content {
          """
          ## HTML Response
          
          ```html [1-10 | 1,10 | 2-5 | 7-9]
          ${includeFile("src/main/resources/html-example.html")}
          ```
          """
        }
      }

      markdownSlide {
        content {
          """
          ## JSON Response
          
          ```json [1-14 | 1,14 | 2,13 | 3-7 | 8-12]
          ${includeFile("src/main/resources/json-example.json")}
          ```
          """
        }
      }

      markdownSlide {
        content {
          """
          ## Playing with HTTP
          
          ### https://github.com/athenian-programming/web-services-demo
          """
        }
      }
    }
  }
}
