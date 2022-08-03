import C.cPresentation
import Docker.dockerPresentation
import Dsl.dslPresentation
import Functional.functionalPresentation
import Http.httpPresentation
import Unix.unixPresentation
import com.kslides.PlaygroundTheme
import com.kslides.PresentationTheme
import com.kslides.Speed
import com.kslides.Transition
import com.kslides.kslides
import kotlinx.css.*
import kotlinx.css.properties.*

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
          * [Docker Basics](docker.html)
          * [C Basics](c.html)
          * [Kotlin DSL Basics](dsl.html)
          """
        }
      }
    }

    httpPresentation()
    functionalPresentation()
    unixPresentation()
    dockerPresentation()
    cPresentation()
    dslPresentation()
  }
}
