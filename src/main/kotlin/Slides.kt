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
import kotlinx.css.LinearDimension
import kotlinx.css.fontSize
import kotlinx.css.lineHeight
import kotlinx.css.properties.LineHeight

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

    // Shrink code blocks so long lines fit the slide window (reveal's default is 0.55em). This
    // fits the ~85-92 char lines of the slideDefinition slides; the rare extra-long line (e.g. a
    // full URL) wraps instead of overflowing horizontally rather than forcing an unreadable size.
    css += """
      .reveal pre { font-size: 0.60em; }
      .reveal pre code { white-space: pre-wrap; word-break: break-word; }
    """

    // Per-slide override: the "highlighted code" slideDefinitions (classes = "smallcode") render their
    // code smaller than the global 0.60em. ".reveal .smallcode pre" (two classes) outranks ".reveal pre"
    // on specificity, so it wins regardless of order; long lines still wrap via the global pre-wrap rule.
    css += """
      .reveal .smallcode pre { font-size: 0.40em; }
      .reveal .largecode pre { font-size: 0.70em; }
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
