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

      fun DslSlide.kotlinSlide(
        fileName: String,
        heightPx: Int = 500,
        fold: Boolean = true,
        highlightOnly: Boolean = false
      ) {
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
          foldedButton = fold
          if (highlightOnly)
            dataHighlightOnly = highlightOnly
        }
      }

      dslSlide {
        content {
          id = "dsl"
          h2 { +"Kotlin DSL Basics" }
          img { width = "250px"; src = "images/dsl/dslimage.png" }
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
            { +"Java Object Creation" },
            { +"Kotlin non-DSL Object Creation" },
            { +"Review Lambdas" },
            { +"Extension Functions" },
            { +"Person DSL" },
            { +"DSL Examples" },
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
            { +"Code-generated Load Scripts" },
            { +"Manual Load Scripts" },
            { +"Cleaning Up Other People's APIs" },
          ) {
            style = "font-size:30px; padding-left: 80px"
          }
        }
      }

      dslSlide {
        content {
          h1 { +"Java Object Creation" }
        }
      }

      dslSlide {
        content {
          h2 { +"Java Person Object" }
          javaSlide("java/Person.java")
        }
      }

      dslSlide {
        content {
          h2 { +"Java Address Object" }
          javaSlide("java/Address.java")
        }
      }

      dslSlide {
        content {
          h2 { +"Java Object Creation" }
          javaSlide("java/Main.java")
        }
      }

      dslSlide {
        content {
          h1 { +"Kotlin non-DSL Object Creation" }
        }
      }

      dslSlide {
        content {
          h2 { +"Kotlin Data Objects" }
          kotlinSlide("slides/nonlambda/DataClasses.kt", fold = false, highlightOnly = true)
        }
      }

      dslSlide {
        content {
          h2 { +"Kotlin Approach (1)" }
          kotlinSlide("slides/nonlambda/Person1.kt")
        }
      }

      dslSlide {
        content {
          h2 { +"Kotlin Approach (2)" }
          kotlinSlide("slides/nonlambda/Person2.kt")
        }
      }

      dslSlide {
        content {
          h2 { +"Kotlin Approach (3)" }
          kotlinSlide("slides/nonlambda/Person3.kt")
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
          kotlinSlide("slides/lambdas/BasicLambdas1.kt")
        }
      }

      dslSlide {
        content {
          h2 { +"Lambdas (2)" }
          kotlinSlide("slides/lambdas/BasicLambdas2.kt")
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
          kotlinSlide("slides/lambdas/BasicLambdas3.kt")
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
          kotlinSlide("slides/lambdas/BasicLambdas4.kt")
        }
      }

      dslSlide {
        content {
          h2 { +"Lambda Function Arguments (2)" }
          kotlinSlide("slides/lambdas/BasicLambdas5.kt")
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
          kotlinSlide("slides/lambdas/ExtensionFunc1.kt")
        }
      }

      dslSlide {
        content {
          h2 { +"Extension Functions (2)" }
          kotlinSlide("slides/lambdas/ExtensionFunc2.kt")
        }
      }

      dslSlide {
        content {
          h1 { +"Person DSL" }
        }
      }

      dslSlide {
        content {
          h2 { +"Person DSL without Receiver" }
          kotlinSlide("slides/receiver/ArgPerson.kt")
        }
      }

      dslSlide {
        content {
          h2 { +"Person DSL with Receiver" }
          kotlinSlide("slides/receiver/ReceiverPerson.kt")
        }
      }

      dslSlide {
        content {
          h2 { +"Address DSL with Receiver" }
          kotlinSlide("slides/receiver/ReceiverAddress.kt")
        }
      }

      dslSlide {
        content {
          h2 { +"Final Person DSL" }
          kotlinSlide("dsl/PersonDsl.kt")
        }
      }

      dslSlide {
        content {
          h3 { +"🕯 Kotlin DSL Examples" }
          unorderedList(
            {
              a {
                +"Gradle build.gradle.kts DSL"
                href = "https://docs.gradle.org/current/userguide/kotlin_dsl.html"
                target = "_blank"
              }
            },
            {
              a {
                +"Kotlin HTML DSL"
                href = "https://kotlinlang.org/docs/typesafe-html-dsl.html"
                target = "_blank"
              }
            },
            {
              a {
                +"Kotlin CSS DSL"
                href = "https://ktor.io/docs/css-dsl.html#use_css"
                target = "_blank"
              }
            },
            {
              a {
                +"Ktor Embedded Server DSL"
                href = "https://ktor.io/docs/create-server.html#embedded"
                target = "_blank"
              }
            },
            {
              a {
                +"ReadingBat DSL"
                href = "https://github.com/readingbat/readingbat-python-content/blob/master/src/Content.kt"
                target = "_blank"
              }
            },
            {
              a {
                +"KSlides DSL"
                href = "https://github.com/pambrose/myslides/blob/master/src/main/kotlin/Dsl.kt"
                target = "_blank"
              }
            },
          ) {
            style = "font-size:30px; padding-left: 80px"
          }
        }
      }

    }
  }
}