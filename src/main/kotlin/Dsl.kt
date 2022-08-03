import com.kslides.KSlides
import com.kslides.PlaygroundMode
import com.kslides.githubRawUrl
import com.kslides.playground
import com.kslides.slide.DslSlide
import com.kslides.unorderedList
import kotlinx.html.*

object Dsl {

  fun KSlides.dslPresentation() {
    presentation {
      path = "dsl.html"

      css += """
        .smallText {
          padding-bottom: 20px;
        }
        .smallText li {
          font-size: 0.8em;
        }
      """

      val owner = "pambrose"
      val repoName = "dsl-demo"
      presentationConfig {
        topRightHref = "#/dsl"
      }

      fun DslSlide.javaSlide(fileName: String, heightPx: Int = 500) {
        val useLocal = true
        playground(
          if (useLocal) "dsl-demo/src/main/java/org/athenian/$fileName" else githubRawUrl(
            owner,
            repoName,
            fileName
          )
        ) {
          mode = PlaygroundMode.JAVA
          height = "${heightPx}px"
          width = "100%"
        }
      }

      fun DslSlide.kotlinSlide(fileName: String, heightPx: Int = 500) {
        val useLocal = true
        playground(
          if (useLocal) "dsl-demo/src/main/kotlin/org/athenian/$fileName" else githubRawUrl(
            owner,
            repoName,
            fileName
          )
        ) {
          height = "${heightPx}px"
          width = "100%"
          foldedButton = true
        }
      }

      fun FlowOrInteractiveOrPhrasingContent.image(fname: String, widthPx: Int = 500) {
        img { src = "images/c/$fname"; width = "${widthPx}px" }
      }

      dslSlide {
        content {
          id = "dsl"
          h2 { +"Kotlin DSL Basics" }
          a {
            href =
              "https://www.amazon.com/Programming-Language-PROGRAMMING-LANG-p2-ebook-dp-B009ZUZ9FW/dp/B009ZUZ9FW/ref=mt_other"
            target = "_blank"
            img { width = "250px"; src = "images/c/CProgrammingLanguage.jpg" }
          }
          a {
            href = "https://pambrose.github.io/myslides/dsl.html"
            +"https://pambrose.github.io/myslides/dsl.html"
          }
        }
      }

      dslSlide {
        content {
          h3 { +"🕯 Overview of Slides" }
          unorderedList(
            { +"What is a DSL?" },
            { +"Java's Approach" },
            { +"Kotlin's non-DSL Approach" },
            { +"Lambdas" },
            { +"Lambdas with Receivers" },
            { +"Lambda Function Arguments" },
            { +"Extension Functions" },
            { a { +"Code Sources"; href = "https://github.com/pambrose/dsl-demo" } }
          ) {
            style = "font-size:30px; padding-left: 80px"
          }
        }
      }

      dslSlide {
        content {
          h2 { +"🔦 What is a DSL?" }
          +"""
            Domain specific language (DSL) is a computer language that’s targeted to a 
            particular kind of problem, rather than a general purpose language that’s 
            aimed at any kind of software problem. 
          """
          a {
            +" (source: Martin Fowler)"
            href = "https://martinfowler.com/bliki/DomainSpecificLanguage.html"
          }
        }
      }

      dslSlide {
        content {
          h3 { +"🕯 Where do we use DSLs" }
          unorderedList(
            { +"Configurations Files" },
            { +"Library Interface" },
            { +"For casual programmers" },
            { +"Code-generated Load Scripts" },
          ) {
            style = "font-size:30px; padding-left: 80px"
          }
        }
      }

      dslSlide {
        content {
          h1 { +"Java's Approach" }
          h2 { +"To Object Creation" }
        }
      }

      dslSlide {
        content {
          h2 { +"Java's Approach" }
          javaSlide("java/Main.java")
        }
      }

      dslSlide {
        content {
          h1 { +"Kotlin's non-DSL Approach" }
          h2 { +"To Object Creation" }
        }
      }

      dslSlide {
        content {
          h2 { +"Kotlin's Approach (1)" }
          kotlinSlide("nonlambda/Person1.kt")
        }
      }

      dslSlide {
        content {
          h2 { +"Kotlin's Approach (2)" }
          kotlinSlide("nonlambda/Person2.kt")
        }
      }

      dslSlide {
        content {
          h2 { +"Kotlin's Approach (3)" }
          kotlinSlide("nonlambda/Person3.kt")
        }
      }

      dslSlide {
        content {
          h2 { +"Kotlin's Approach (4)" }
          kotlinSlide("nonlambda/Person4.kt")
        }
      }

      dslSlide {
        content {
          h1 { +"Lambdas" }
        }
      }

      dslSlide {
        content {
          h2 { +"Lambdas (1)" }
          kotlinSlide("lambdas/BasicLambdas1.kt")
        }
      }

      dslSlide {
        content {
          h2 { +"Lambdas (2)" }
          kotlinSlide("lambdas/BasicLambdas2.kt")
        }
      }

      dslSlide {
        content {
          h1 { +"Lambdas with Receivers" }
        }
      }

      dslSlide {
        content {
          h2 { +"Lambdas with Receivers" }
          kotlinSlide("lambdas/BasicLambdas3.kt")
        }
      }

      dslSlide {
        content {
          h1 { +"Lambda Function Arguments" }
        }
      }

      dslSlide {
        content {
          h2 { +"Lambda Function Arguments (1)" }
          kotlinSlide("lambdas/BasicLambdas4.kt")
        }
      }

      dslSlide {
        content {
          h2 { +"Lambda Function Arguments (2)" }
          kotlinSlide("lambdas/BasicLambdas5.kt")
        }
      }

      dslSlide {
        content {
          h1 { +"Extension Functions" }
        }
      }

      dslSlide {
        content {
          h2 { +"Extension Functions (1)" }
          kotlinSlide("lambdas/ExtensionFunc1.kt")
        }
      }

      dslSlide {
        content {
          h2 { +"Extension Functions (2)" }
          kotlinSlide("lambdas/ExtensionFunc2.kt")
        }
      }

      dslSlide {
        content {
          h1 { +"Person DSL" }
        }
      }

      dslSlide {
        content {
          h2 { +"Person DSL without Reciever" }
          kotlinSlide("dsl/ArgPerson.kt")
        }
      }

      dslSlide {
        content {
          h2 { +"Person DSL with Reciever" }
          kotlinSlide("dsl/ReceiverPerson.kt")
        }
      }

      dslSlide {
        content {
          h2 { +"Address DSL with Reciever" }
          kotlinSlide("dsl/ReceiverAddress.kt")
        }
      }

    }
  }
}