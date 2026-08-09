import C.cPresentation
import Docker.dockerPresentation
import Dsl.dslPresentation
import Functional.functionalPresentation
import Http.httpPresentation
import Redis.redisPresentation
import Unix.unixPresentation
import com.kslides.PlaygroundTheme
import com.kslides.PresentationTheme
import com.kslides.Speed
import com.kslides.Transition
import com.kslides.kslides

fun main() {
  kslides {
    kslidesConfig {
      // kslides configuration options
      // Use the local kroki server running with docker-compose
      krokiUrl = "http://localhost:8000"
    }

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
      // Shrink code blocks so long lines fit the slide window (reveal's default is 0.55em), and
      // wrap the rare extra-long line (e.g. a full URL) instead of overflowing horizontally.
      // Both cascade to every slide; an individual slide can override either in its own
      // slideConfig { }.
      slideConfig {
        codeFontSize = "0.60em"
        codeWrap = true
      }

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

      copyCodeConfig {
        timeout = 2000
        copy = "Copy"
        copied = "Copied!"
      }

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

        // Sizes the editor and the run-output pane together. Absolute units on purpose: the
        // Playground renders in its own iframe document, so em would resolve against that
        // document's root rather than the slide. lineHeight only pins the spacing these decks
        // have always used — CodeMirror's own ratio already scales with fontSize, so dropping
        // it just loosens the lines slightly inside the fixed-height iframe.
        fontSize = "20px"
        lineHeight = "25px"
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
          * [Docker Basics](docker.html)
          * [C Basics](c.html)
          * [Kotlin DSL Basics](dsl.html)
          * [Redis Basics](redis.html)
          """
        }
      }
    }

    val useLocal = System.getenv("PORT").orEmpty().isEmpty()

    httpPresentation()
    functionalPresentation()
    unixPresentation()
    dockerPresentation()
    cPresentation(useLocal)
    dslPresentation(useLocal)
    redisPresentation(useLocal)
  }
}
