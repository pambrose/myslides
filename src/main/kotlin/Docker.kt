import com.kslides.KSlides
import com.kslides.codeSnippet
import com.kslides.unorderedList
import kotlinx.html.*

object Docker {

  fun KSlides.dockerPresentation() {
    presentation {
      path = "docker.html"

      css += """
        .history li {
          padding-top: 0px;
        }
      """

      presentationConfig {
        topRightHref = "#/docker"
      }

      dslSlide {
        content {
          id = "docker"
          h2 { +"Docker Basics" }
          img { width = "300px"; src = "images/docker.png" }
          a {
            href = "https://pambrose.github.io/myslides/docker.html"
            +"https://pambrose.github.io/myslides/docker.html"
          }
        }
      }

      dslSlide {
        content {
          h2 { +"History of Docker" }
          unorderedList(
            "dotcloud.com and Solomon Hykes",
            "Y Combinator 2010, Debuted in 2013",
            "MSFT, IBM, Google, and Amazon Support",
          )
        }
      }

      dslSlide {
        content {
          h2 { +"What is Docker?" }
          unorderedList(
            "Platform to build, ship and run any app, anywhere",
            "Docker is open source",
            "Docker applications run in containers",
            "Built on top of Linux LXC",
          )
        }
      }

      dslSlide {
        content {
          h2 { +"What is a Container?" }
          +"""
            A Docker container image is a lightweight, standalone, executable package of software 
            that includes everything needed to run an application: code, runtime, system tools, 
            system libraries and settings. Container images become containers at runtime and 
            in the case of Docker containers - images become containers when they run on Docker Engine.
           """
        }
      }
      dslSlide {
        content {
          h2 { +"Containers vs VMs" }
          +"Containers are isolated, but share OS and bins/libraries (where appropriate)"
          img { width = "1000px"; src = "images/docker-containers-and-vm.png" }
        }
      }

      dslSlide {
        content {
          h2 { +"Advantages of Containerization" }
          unorderedList(
            "Consistent test environment for dev and QA",
            "Cross-platform packages called images",
            "Isolation and encapsulation of app dependencies",
            "Ability to scale efficiently, easily, and in real time",
            "Enhances efficiency via easy reuse of images",
          )
        }
      }

      dslSlide {
        content {
          h2 { +"Getting Started with Docker" }
          unorderedList(
            "Download/install from https://docker.com",
            "Run Docker",
            "CLI or Desktop Interface",
            "Run a container",
            "Will automatically download a Docker image",
            "Volumes, network, hostname, DNS, etc.",
          )
        }
      }

      dslSlide {
        content {
          h2 { +"🏃‍ Running a Container" }
          codeSnippet {
            copyButton = false
            code = """
              docker run hello-world
              docker run -it ubuntu bash
              docker ps
              docker ps -a
              docker rm 
              docker images
              docker rmi
              docker run -it --rm debian bash
             """
          }
        }
      }

      dslSlide {
        content {
          h2 { +"Container Lifecycle" }
          img { width = "1000px"; src = "images/container_life_cycle.png" }
        }
      }

      dslSlide {
        content {
          h2 { +"📜 Docker Examples" }
          codeSnippet {
            code = """
              docker run -d -p 80:80 wordpress:latest


              git clone 
              cd docker-demo
              cd docker-website
              
              docker build -t docker-website .
              docker run -d -p 9000:8888 --name mysite docker-website
              docker logs -tf mysite
                
                
              docker run -d -p 8082:80 docker/getting-started
              
              
              docker run danielkraic/asciiquarium 
            """
          }
        }
      }

      dslSlide {
        content {
          h2 { +"Docker Commands" }
          unorderedList(
            "docker ps -a",
            "Cross-platform packages called images",
            "Isolation and encapsulation of application dependencies",
            "Ability to scale efficiently, easily, and in real time",
            "Enhances efficiency via easy reuse of images",
          )
        }
      }
    }
  }
}