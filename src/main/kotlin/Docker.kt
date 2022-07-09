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
            "Collision with Kubernetes",
          )
        }
      }

      dslSlide {
        content {
          h2 { +"What is Docker?" }
          unorderedList(
            "Platform to build, ship and run any app, anywhere",
            "Docker applications run in containers",
            "Containers are not new",
            "",
            "",
          )
        }
      }

      dslSlide {
        content {
          h2 { +"Advantages of Containerization" }
          unorderedList(
            "Consistent test environment for development and QA",
            "Cross-platform packages called images",
            "Isolation and encapsulation of application dependencies",
            "Ability to scale efficiently, easily, and in real time",
            "Enhances efficiency via easy reuse of images",
          )
        }
      }

      dslSlide {
        content {
          h2 { +"📜 Docker Containers" }
          codeSnippet {
            code = """
              docker info
              docker run hello-world
              docker run -it ubuntu bash
              docker ps
              docker ps -a
              docker run -it --name mycontainer ubuntu bash
              docker start -i mycontainer        
              docker stop mycontainer
              docker rm mycontainer
              docker images
              docker rmi
             """
          }
        }
      }

      dslSlide {
        content {
          h2 { +"📜 Docker Examples" }
          codeSnippet {
            code = """
              git clone 
              cd docker-demo
              cd docker-website
              
              docker build -t docker-website .
              docker run -d -p 9000:8888 --name mysite docker-website
              docker logs -tf mysite
                
                
              docker run -d -p 8082:80 docker/getting-started
              
              docker run vanessa/asciiquarium 
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