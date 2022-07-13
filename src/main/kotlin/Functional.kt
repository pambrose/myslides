import com.kslides.KSlides
import com.kslides.codeSnippet
import com.kslides.include
import com.kslides.orderedList
import com.kslides.permuteBy
import com.kslides.playground
import com.kslides.unorderedList
import kotlinx.html.*

object Functional {

  fun KSlides.functionalPresentation() {
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
              href = "https://twitter.com/unclebobmartin/status/1522904306948657160"
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
  }
}