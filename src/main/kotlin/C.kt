import Utils.linkItem
import Utils.textItem
import com.kslides.KSlides
import com.kslides.PlaygroundMode
import com.kslides.githubRawUrl
import com.kslides.playground
import com.kslides.slide.DslSlide
import com.kslides.unorderedList
import kotlinx.html.*

object C {

  fun KSlides.cPresentation(useLocal: Boolean) {
    presentation {
      path = "c.html"

      css += """
        .smallText {
          padding-bottom: 20px;
        }
        .smallText li {
          font-size: 0.8em;
        }
      """

      val owner = "pambrose"
      val repoName = "c-demo"
      presentationConfig {
        topRightHref = "#/c"
      }

      fun DslSlide.cSlide(fileName: String, heightPx: Int = 500) {
        playground(if (useLocal) "c-basics/$fileName" else githubRawUrl(owner, repoName, fileName)) {
          mode = PlaygroundMode.C
          height = "${heightPx}px"
          width = "100%"
        }
      }

      fun FlowOrInteractiveOrPhrasingContent.image(fname: String, widthPx: Int = 500) {
        img { src = "images/c/$fname"; width = "${widthPx}px" }
      }

      dslSlide {
        content {
          id = "c"
          h2 { +"C Basics" }
          a {
            href =
              "https://www.amazon.com/Programming-Language-PROGRAMMING-LANG-p2-ebook-dp-B009ZUZ9FW/dp/B009ZUZ9FW/ref=mt_other"
            target = "_blank"
            img { width = "250px"; src = "images/c/CProgrammingLanguage.jpg" }
          }
          a {
            href = "https://pambrose.github.io/myslides/c.html"
            +"https://pambrose.github.io/myslides/c.html"
          }
        }
      }

      dslSlide {
        content {
          h3 { +"🕯 Overview of Slides" }
          unorderedList(
            textItem("What is C?"),
            textItem("Syntax"),
            textItem("Type System"),
            textItem("Number Systems Review"),
            textItem("Variables in Memory"),
            textItem("Arrays and Pointers"),
            textItem("Strings"),
            textItem("Stack and Heap"),
            textItem("Avoiding Trouble with Memory"),
            textItem("Structs"),
            textItem("Function Pointers and Object-Oriented C"),
            linkItem("Code Sources", "https://github.com/pambrose/c-demo")
          ) {
            style = "font-size:30px; padding-left: 80px"
          }
        }
      }

      dslSlide {
        content {
          h3 { +"🔦 What is C?" }
          unorderedList(
            "Dennis Ritchie at AT&T Bell Labs in 1972",
            "Speedcode -> FORTRAN -> ALGOL 58 -> ALGOL 60 -> CPL -> BCPL -> B -> C",
            "Influenced countless languages, including: C++, C#, Java, Python, Go, and Obj-C",
            "Low-level systems language",
            "Static type system with weak enforcement",
            "Architecture-specific language -- not WORA",
            "C source files are compiled to object files",
            "Programs and libraries",
          ) {
            style = "font-size:30px; padding-left: 80px"
          }
        }
      }

      dslSlide {
        content {
          h2 { +"🌅 Hello World" }
          div("smallText") {
            unorderedList(
              "#include preprocessing directives",
              ".c and .h files",
              "for, if, {}, quotes, and ;",
              "<stdio.h>",
              "Return success value"
            ) {
              style = "list-style-type:square;"
            }
          }
          cSlide("helloworld.c", 400)
        }
      }

      dslSlide {
        content {
          h2 { +"🚌 Basic Types" }
          div("smallText") {
            unorderedList(
              { +"char, int, float and double" },
              { +"Modifiers: signed, unsigned, short, and long" },
              { a { +"Summary of types"; href = "https://en.wikipedia.org/wiki/C_data_types#Main_types" } }
            ) {
              style = "list-style-type:square;"
            }
          }
          cSlide("basic_types.c", 400)
        }
      }

      dslSlide {
        content {
          h2 { +"🧮 Decimal, Octal, Hex, and Binary" }
          image("decocthex.png", 900)
        }
      }

      dslSlide {
        content {
          h2 { +"📬 Hex Addresses" }
          image("hex_values.png", 700)
        }
      }

      dslSlide {
        content {
          h2 { +"🔬 Variables in Memory" }
          image("basic_types.png", 900)
        }
      }

      dslSlide {
        content {
          h1 { +"🚌 Arrays" }
        }
      }

      verticalSlides {
        dslSlide {
          content {
            h2 { +"🚌 Arrays (1)" }
            cSlide("arrays1.c")
          }
        }

        dslSlide {
          content {
            h2 { +"🔬 Arrays in Memory" }
            image("arrays1.png")
          }
        }
      }

      verticalSlides {
        dslSlide {
          content {
            h2 { +"🚌 Arrays (2)" }
            cSlide("arrays2.c")
          }
        }

        dslSlide {
          content {
            h2 { +"🔬 Arrays in Memory" }
            image("arrays2.png")
          }
        }
      }

      dslSlide {
        content {
          h1 { +"☝ Pointers" }
        }
      }

      verticalSlides {
        dslSlide {
          content {
            h2 { +"☝ Pointers (1)" }
            cSlide("pointers1.c")
          }
        }

        dslSlide {
          content {
            h2 { +"🔬 Pointers in Memory" }
            image("pointers1.png")
          }
        }
      }

      verticalSlides {
        dslSlide {
          content {
            h2 { +"☝ Pointers (2)" }
            cSlide("pointers2.c", 550)
          }
        }

        dslSlide {
          content {
            h2 { +"🔬 Pointers in Memory" }
            image("pointers2.png")
          }
        }
      }

      dslSlide {
        content {
          h2 { +"📱 Call by Value and Reference" }
          cSlide("call_by.c")
        }
      }

      verticalSlides {
        dslSlide {
          content {
            h2 { +"☝ Pointers to Arrays" }
            cSlide("pointers3.c", 550)
          }
        }

        dslSlide {
          content {
            h2 { +"🔬 Pointers to Arrays in Memory" }
            image("pointers3.png")
          }
        }
      }

      dslSlide {
        content {
          h2 { +"☝ Pointers and sizeof()" }
          cSlide("pointers4.c")
        }
      }

      dslSlide {
        content {
          h1 { +"🧶 Strings" }
        }
      }

      verticalSlides {
        dslSlide {
          content {
            h2 { +"🧶 Strings (1)" }
            cSlide("strings1.c")
          }
        }

        dslSlide {
          content {
            h2 { +"🔬 Strings in Memory" }
            image("strings1.png")
          }
        }
      }

      verticalSlides {
        dslSlide {
          content {
            h2 { +"🧶 Strings (2)" }
            cSlide("strings2.c")
          }
        }

        dslSlide {
          content {
            h2 { +"🔬 Strings in Memory" }
            image("strings2.png")
          }
        }
      }

      dslSlide {
        content {
          h2 { +"🧶 Strings (3)" }
          cSlide("strings3.c", 600)
        }
      }

      verticalSlides {
        dslSlide {
          content {
            h2 { +"🧶 Strings (4)" }
            cSlide("strings4.c")
          }
        }

        dslSlide {
          content {
            h2 { +"🔬 Strings in Memory" }
            image("strings4.png")
          }
        }
      }

      dslSlide {
        content {
          h2 { +"🧶 Strings (5)" }
          cSlide("strings5.c")
        }
      }

      dslSlide {
        content {
          h1 { +"The Stack and Heap" }
          image("Memory_model.png", 350)
          h5 {
            a {
              +"More info on Stack and Heap Memory";
              href = "https://courses.engr.illinois.edu/cs225/sp2022/resources/stack-heap/"
            }
          }
        }
      }

      dslSlide {
        id = "callstacks1"
        content {
          h2 { +"📚 Call Stacks (1)" }
          iframe {
            width = "560"
            height = "315"
            src = "https://www.youtube.com/embed/Q2sFmqvpBe0"
            title = "YouTube video player"
            attributes["frameborder"] = "0"
            attributes["allow"] = "accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope"
            attributes["allowfullscreen"] = "true"
          }
        }
      }

      dslSlide {
        id = "callstacks2"
        content {
          h2 { +"📚 Call Stacks (2)" }
          iframe {
            width = "560"
            height = "315"
            src = "https://www.youtube.com/embed/aCPkszeKRa4"
            title = "YouTube video player"
            attributes["frameborder"] = "0"
            attributes["allow"] = "accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope"
            attributes["allowfullscreen"] = "true"
          }
        }
      }

      dslSlide {
        id = "callstacks3"
        content {
          h2 { +"📚 Call Stacks (3)" }
          iframe {
            width = "560"
            height = "315"
            src = "https://www.youtube.com/embed/jVzSBkbfdiw"
            title = "YouTube video player"
            attributes["frameborder"] = "0"
            attributes["allow"] = "accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope"
            attributes["allowfullscreen"] = "true"
          }
        }
      }

      // https://courses.engr.illinois.edu/cs225/sp2022/resources/stack-heap/

      dslSlide {
        content {
          h2 { +"📚 The Stack" }
        }
      }

      val stackCaptions =
        listOf(
          "Allocate variable a for main",
          "Allocate b for main and store -3",
          "Allocate c for main and store 12345",
          "Allocate p for main and store address of b",
          "Allocate variable a for hello and store 100",
          "Deallocate the stack memory of hello and return 100 to main",
          "Allocate d for main and store 100",
          "Deallocate the stack memory of main and return 0",
        )
      for (i in (1..8))
        dslSlide {
          content {
            img { src = "images/illinois.edu/stack_demo_$i.png"; width = "800px" }
            h6 { +stackCaptions[i - 1] }
          }
        }

      dslSlide {
        content {
          h2 { +"⛰ The Heap" }
        }
      }

      val heapCaptions =
        listOf(
          "Allocate an integer with default value 0 on the heap, allocate p on main's stack to store the address of the integer",
          "Allocate a Cube with default width 20 on the heap, allocate c1 on main's stack to store the address of the Cube",
          "Allocate c2 on main's stack and store a copy of c1",
          "Call method setLength on c2, changes the width of the Cube pointed by both c1 and c2",
          "Deallocate stack memory of main and return 0",
        )
      for (i in (1..5))
        dslSlide {
          content {
            img { src = "images/illinois.edu/heap_demo_$i.png"; width = "800px" }
            h6 { +heapCaptions[i - 1] }
          }
        }

      dslSlide {
        content {
          h1 { +"⛰ Heap Calls" }
        }
      }

      verticalSlides {
        dslSlide {
          content {
            h2 { +"⛰ Heap Calls (1)" }
            cSlide("malloc1.c")
          }
        }

        dslSlide {
          content {
            h2 { +"🔬 malloc() Memory" }
            image("malloc1.png", 900)
          }
        }
      }

      dslSlide {
        content {
          h2 { +"⛰ Heap Calls (2)" }
          cSlide("malloc2.c")
        }
      }

      dslSlide {
        content {
          h2 { +"⛰ Initialized Heap Memory" }
          cSlide("calloc1.c", 550)
        }
      }

      dslSlide {
        content {
          h2 { +"🔁 Reallocating Heap Memory" }
          cSlide("realloc1.c", 550)
        }
      }

      dslSlide {
        content {
          h2 { +"⚠ Avoiding Trouble with Memory" }
        }
      }

      dslSlide {
        content {
          h2 { +"⚠ Calls to free() (1)" }
          cSlide("free1.c")
        }
      }

      dslSlide {
        content {
          h2 { +"⚠ Calls to free() (2)" }
          cSlide("free2.c", 600)
        }
      }

      dslSlide {
        content {
          h2 { +"⚠ Return an Array" }
          cSlide("return_array1.c", 600)
        }
      }

      dslSlide {
        content {
          h2 { +"🦺 Return an Array" }
          cSlide("return_array2.c", 600)
        }
      }

      dslSlide {
        content {
          h2 { +"⚠ Memory Leak" }
          cSlide("return_array3.c", 600)
        }
      }

      dslSlide {
        content {
          h2 { +"🧶 String Functions" }
          cSlide("string_funcs1.c", 550)
        }
      }

      dslSlide {
        content {
          h1 { +"🧶 Arrays of Strings" }
        }
      }

      verticalSlides {
        dslSlide {
          content {
            h3 { +"🧶 Arrays of Strings (1)" }
            cSlide("cli_args1.c", 550)
          }
        }

        dslSlide {
          content {
            h3 { +"🔬 String Array in Memory" }
            img {
              style = "margin: 0px;"
              src = "images/c/cli_args1.png"; width = "770px"
            }
            img {
              style = "margin: 0px;"
              src = "images/c/cli_args2.png"; width = "370px"
            }
          }
        }
      }

      dslSlide {
        content {
          h2 { +"🧶 Arrays of Strings (2)" }
          cSlide("cli_args2.c")
        }
      }

      dslSlide {
        content {
          h1 { +"🎒 C structs" }
        }
      }

      dslSlide {
        content {
          h2 { +"🎒 C structs" }
          cSlide("structs1.c", 600)
        }
      }

      dslSlide {
        content {
          h3 { +"🎒 C struct Arrays" }
          cSlide("structs2.c", 600)
        }
      }

      dslSlide {
        content {
          h3 { +"🎒 C struct Parameters" }
          cSlide("structs3.c", 600)
        }
      }

      dslSlide {
        content {
          h1 { +"👉 Function Pointers" }
        }
      }

      dslSlide {
        content {
          h2 { +"👉 Function Pointers" }
          cSlide("func_pointer1.c", 600)
        }
      }

      dslSlide {
        content {
          h2 { +"👉 Higher Order Functions (1)" }
          cSlide("func_pointer2.c", 600)
        }
      }

      dslSlide {
        content {
          h3 { +"👉 Higher Order Functions (2)" }
          cSlide("func_pointer3.c", 600)
        }
      }

      dslSlide {
        id = "objC"
        content {
          h3 { +"🌵Object-Oriented C" }
          cSlide("objects1.c", 600)
        }
      }
    }
  }
}