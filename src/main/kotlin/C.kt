import com.kslides.KSlides
import com.kslides.PlaygroundMode
import com.kslides.githubRawUrl
import com.kslides.playground
import com.kslides.slide.DslSlide
import com.kslides.unorderedList
import kotlinx.html.*

object C {

  fun KSlides.cPresentation() {
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

      fun DslSlide.cSlide(fileName: String, heightPx: Int = -1) {
        playground(githubRawUrl(owner, repoName, fileName)) {
          mode = PlaygroundMode.C
          if (heightPx > 0)
            height = "${heightPx}px"
        }
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
            { +"What is C?" },
            { +"Syntax" },
            { +"Type System" },
            { +"Number Systems Review" },
            { +"Variables in Memory" },
            { +"Pointers and Arrays" },
            { +"Strings" },
            { +"Stack and Heap" },
            { a { +"Code Sources"; href = "https://github.com/pambrose/c-demo" } }
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
          h2 { +"🚌 Basic Types in C" }
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
          img { src = "images/c/decocthex.png"; width = "900px" }
        }
      }

      dslSlide {
        content {
          h2 { +"📮 Hex Addresses" }
          img { src = "images/c/hex_values.png"; width = "700px" }
        }
      }

      dslSlide {
        content {
          h2 { +"🔬 Variables in Memory" }
          img { src = "images/c/basic_types.png"; width = "900px" }
        }
      }

      dslSlide {
        content {
          h2 { +"🚌 Arrays in C (1)" }
          cSlide("arrays1.c", 500)
        }
      }

      dslSlide {
        content {
          h2 { +"🔬 Arrays in Memory" }
          img { src = "images/c/arrays1.png"; width = "500px" }
        }
      }

      dslSlide {
        content {
          h2 { +"🚌 Arrays in C (2)" }
          cSlide("arrays2.c", 500)
        }
      }

      dslSlide {
        content {
          h2 { +"🔬 Arrays in Memory" }
          img { src = "images/c/arrays2.png"; width = "500px" }
        }
      }

      dslSlide {
        content {
          h2 { +"☝ Pointers in C" }
          cSlide("pointers1.c", 500)
        }
      }

      dslSlide {
        content {
          h2 { +"🔬 Arrays in Memory" }
          img { src = "images/c/pointers1.png"; width = "500px" }
        }
      }

      dslSlide {
        content {
          h2 { +"📱 Call by Value and Reference" }
          cSlide("copy_by.c", 500)
        }
      }

      dslSlide {
        content {
          h2 { +"☝ Pointer Arithmetic" }
          cSlide("pointers2.c", 500)
        }
      }

      dslSlide {
        content {
          h2 { +"🔬 Arrays in Memory" }
          img { src = "images/c/pointers2.png"; width = "500px" }
        }
      }

      dslSlide {
        content {
          h2 { +"🧶 Strings in C (1)" }
          cSlide("strings1.c", 500)
        }
      }

      dslSlide {
        content {
          h2 { +"🔬 Strings in Memory" }
          img { src = "images/c/strings1.png"; width = "500px" }
        }
      }

      dslSlide {
        content {
          h2 { +"🧶 Strings in C (2)" }
          cSlide("strings2.c", 500)
        }
      }

      dslSlide {
        content {
          h2 { +"🔬 Strings in Memory" }
          img { src = "images/c/strings2.png"; width = "500px" }
        }
      }

      dslSlide {
        content {
          h2 { +"🧶 Strings in C (3)" }
          cSlide("strings3.c", 500)
        }
      }

      dslSlide {
        content {
          h2 { +"🔬 Strings in Memory" }
          img { src = "images/c/strings3.png"; width = "500px" }
        }
      }

      dslSlide {
        content {
          h2 { +"Stack and Heap" }
          img { src = "images/c/Memory_model.png"; width = "400px" }
        }
      }

      // https://courses.engr.illinois.edu/cs225/sp2022/resources/stack-heap/

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
            img {
              width = "800px"
              src = "images/illinois.edu/stack_demo_$i.png"
            }
            h6 { +stackCaptions[i - 1] }
          }
        }

      dslSlide {
        content {
          h2 { +"⛰ Heap Array (1)" }
          cSlide("malloc1.c", 500)
        }
      }

      dslSlide {
        content {
          h2 { +"🔬 malloc() Memory" }
          img { src = "images/c/malloc1.png"; width = "900px" }
        }
      }

      dslSlide {
        content {
          h2 { +"⛰ Heap Array (2)" }
          cSlide("malloc2.c", 500)
        }
      }

      dslSlide {
        content {
          h2 { +"⛰ Initialized Heap Memory" }
          cSlide("calloc1.c", 500)
        }
      }

      dslSlide {
        content {
          h2 { +"🔁 Reallocating Heap Memory" }
          cSlide("realloc1.c", 500)
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
            img {
              width = "800px"
              src = "images/illinois.edu/heap_demo_$i.png"
            }
            h6 { +heapCaptions[i - 1] }
          }
        }

      dslSlide {
        content {
          h2 { +"🧶 String Functions" }
          cSlide("string_funcs1.c", 500)
        }
      }

      dslSlide {
        content {
          h2 { +"⚠ Return an Array" }
          cSlide("return_array1.c", 500)
        }
      }

      dslSlide {
        content {
          h2 { +"🦺 Return an Array" }
          cSlide("return_array2.c", 500)
        }
      }
    }
  }
}