import com.kslides.KSlides
import com.kslides.PlaygroundMode
import com.kslides.codeSnippet
import com.kslides.githubRawUrl
import com.kslides.playground
import com.kslides.unorderedList
import kotlinx.html.*

object C {

  fun KSlides.cPresentation() {
    presentation {
      path = "c.html"

      css += """
        .history li {
          padding-top: 0px;
        }
      """

      val owner = "pambrose"
      val repoName = "c-demo"
      presentationConfig {
        topRightHref = "#/c"
      }

      dslSlide {
        content {
          id = "c"
          h2 { +"C Basics" }
          a {
            href =
              "https://www.amazon.com/Programming-Language-PROGRAMMING-LANG-p2-ebook-dp-B009ZUZ9FW/dp/B009ZUZ9FW/ref=mt_other"
            target = "_blank"
            img { width = "250px"; src = "images/CProgrammingLanguage.jpg" }
          }
          a {
            href = "https://pambrose.github.io/myslides/c.html"
            +"https://pambrose.github.io/myslides/c.html"
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
          ) {
            style = "font-size:30px; padding-left: 80px"
          }
        }
      }

      dslSlide {
        content {
          h2 { +"🌅 Hello World" }
          unorderedList(
            "#include preprocessing directives",
            ".c and .h files",
            "stdio.h",
          ) {
            style = "list-style-type:square;"
          }
          playground(githubRawUrl(owner, repoName, "helloworld.c")) {
            mode = PlaygroundMode.C
          }
        }
      }

      dslSlide {
        content {
          h2 { +"🚌 Basic Types in C" }
          unorderedList(
            { +"char, int, float and double" },
            { +"Modifiers: signed, unsigned, short, and long" },
            {
              a {
                href = "https://en.wikipedia.org/wiki/C_data_types#Main_types"
                +"Summary"
              }
            }
          ) {
            style = "list-style-type:square;"
          }
          playground(githubRawUrl(owner, repoName, "basic_types.c")) {
            mode = PlaygroundMode.C
            height = "400px"
          }
        }
      }

      dslSlide {
        content {
          h2 { +"🧶 Strings in C" }
          playground(githubRawUrl(owner, repoName, "strings.c")) {
            mode = PlaygroundMode.C
          }
        }
      }

      // https://courses.engr.illinois.edu/cs225/sp2022/resources/stack-heap/

      dslSlide {
        content {
          h2 { +"Stack and Heap" }
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
            img {
              width = "800px"
              src = "images/stack_demo_$i.png"
            }
            h6 { +stackCaptions[i - 1] }
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
              src = "images/heap_demo_$i.png"
            }
            h6 { +heapCaptions[i - 1] }
          }
        }
      dslSlide {
        content {
          h2 { +"🚨 Why do we care about Unix?" }
          unorderedList(
            "AWS, Google, Azure, Digital Ocean, and more",
            "Docker and Kubernetes",
            "Raspberry Pi",
            "Enterprise Computing",
            "DevOps",
            "Data Science",
          )
        }
      }

      dslSlide {
        content {
          h2 { +"💪 The Unix Philosophy" }
          unorderedList(
            "Write programs that do one thing and do it well -- DOTADIW",
            "Write programs to work together",
            "Write programs to handle text streams, because that is a universal interface",
          ) {
            style = "font-size:30px; padding-left: 20px"
          }
        }
      }

      dslSlide {
        content {
          h2 { +"🐚 What is a Unix Shell?" }
          div {
            unorderedList(
              "sh",
              "csh",
              "bash",
              "ksh",
              "fish",
              "zsh",
              "tcsh",
            ) {
              style = "font-size:30px"
            }
          }
          br {}
          a {
            href = "https://hyperpolyglot.org/unix-shells"
            target = "_blank"
            +"👉 Summary of shells"
          }
        }
      }

      dslSlide {
        content {
          h2 { +"✉ Unix Shell Environment Variables" }
          unorderedList(
            "env",
            "echo",
            "\$PATH / \$HOME / \$USER / \$SHELL",
            "\$JAVA_HOME / \$CLASSPATH",
            ".zshrc / .cshrc / .bashrc",
          ) {
            style = "font-size:30px; padding-left: 20px"
          }
        }
      }

      dslSlide {
        content {
          h2 { +"🦺 General Command Structure" }
          h3 { style = "color: red;"; +"command options args" }
          unorderedList(
            "man page",
            "tldr",
            "single and double dash options",
            "which/where/whereis",
            "args[] in programs",
          ) {
            style = "font-size:30px"
          }
        }
      }

      dslSlide {
        content {
          h2 { +"📂 File System Commands" }
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
            "mkdir/rmdir",
          ) {
            style = "font-size:30px; padding-left: 20px"
          }
        }
      }

      dslSlide {
        content {
          h2 { +"🖊 Unix Editors" }
          div {
            unorderedList(
              "vi/vim/neovim",
              "emacs",
              "pico",
              "nano",
            ) {
              style = "font-size:30px; padding-left: 20px"
            }
            br {}
            br {}
            h3 { style = "color: red;"; +"Choose one (preferably vim) and learn it!!" }
          }
        }
      }

      dslSlide {
        content {
          h2 { +"🛫 I/O Control" }
          div {
            unorderedList(
              "stdin, stdout, and stderr",
              "Redirecting input/output",
              "< n> > 2> 2>&1 >>",
              "/dev/stdin /dev/stdout /dev/stderr",
            ) {
              style = "font-size:30px; padding-left: 20px"
            }
          }
          img { width = "800px"; src = "images/diagram-shell-keyboard-terminal.png" }
        }
      }

      dslSlide {
        content {
          h2 { +"📥 Common Patterns - Standard Input" }
          img { width = "1000px"; src = "images/diagram-input-examples.png" }
        }
      }

      dslSlide {
        content {
          h2 { +"📤 Common Patterns - Standard Output" }
          img { width = "1000px"; src = "images/diagram-output-examples.png" }
        }
      }

      dslSlide {
        content {
          h2 { +"🚿 Pipes" }
          img { width = "1000px"; src = "images/diagram-cat-sort-uniq-pipeline.png" }
        }
      }

      verticalSlides {
        dslSlide {
          content {
            h2 { +"📜 Text Processing" }
            unorderedList(
              "wc",
              "grep",
              "head/tail",
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

      markdownSlide {
        content {
          """
          ## 🔎 Searching with find
          ```bash
            find
            
            find . -type f
            
            find . -type d
            
            find . -name Utils.java
            
            find . -name "*.java"
            
            find . -name "*.java" -exec wc {} \;
          ```  
          """
        }
      }

      dslSlide {
        content {
          h2 { +"🎈 Misc Commands" }
          unorderedList(
            "ssh",
            "sftp",
            "date/cal",
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
          h2 { +"📦 Job/Process Control" }
          codeSnippet {
            code = """
              python3 -m http.server 8080
            """
          }
          unorderedList(
            "control-z/jobs/fg/bg/&",
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
          h2 { +"📜 Scripts" }
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
          h2 { +"🧑 User Management" }
          unorderedList(
            "w/who",
            "su",
            "root",
            "sudo",
            "adduser/rmuser",
            "chmod",
            "chgrp",
            "chown",
          ) {
            style = "font-size:30px; padding-left: 20px"
          }
        }
      }

      dslSlide {
        content {
          h2 { +"🎉 Fun Commands" }
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
          h2 { +"💁 Helpful Links" }
          a {
            href = "https://effective-shell.com"
            target = "_blank"
            +"👉 Effective Shell"
          }
          br {}
          a {
            href = "https://www.makeuseof.com/linux-commands-and-alternatives/"
            target = "_blank"
            +"👉 8 Classic Commands and Their Modern Alternatives"
          }
          br {}
          a {
            href = "https://jvns.ca/blog/2022/04/12/a-list-of-new-ish--command-line-tools/"
            target = "_blank"
            +"👉 A list of new(ish) command line tools"
          }
          br {}
          a {
            href = "https://www.tomshardware.com/how-to/getting-to-know-the-linux-filesystem"
            target = "_blank"
            +"👉 Getting To Know the Linux Filesystem"
          }
        }
      }
    }
  }
}