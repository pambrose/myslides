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
          h2 { +"📚 Docker Basics" }
          img { width = "300px"; src = "images/docker.png" }
          a {
            href = "https://pambrose.github.io/myslides/docker.html"
            +"https://pambrose.github.io/myslides/docker.html"
          }
        }
      }

      dslSlide {
        content {
          h2 { +"🍴 Taste of Docker" }
          codeSnippet {
            trim = false
            copyButton = false
            code = """
              
              docker pull postgres
              docker pull bitnami/redis
              docker pull quay.io/coreos/etcd
              
              docker run -t -p 8080:80 docker/getting-started
              
              docker run -d -p 8082:80 wordpress:latest


            """.trimIndent()
          }
        }
      }

      dslSlide {
        content {
          h2 { +"History of Docker" }
          unorderedList(
            "dotcloud.com and Solomon Hykes",
            "Made Linux LXC accessible",
            "Y Combinator 2010, Debuted in 2013",
            "MSFT, IBM, Google, and Amazon Support",
          )
        }
      }

      dslSlide {
        content {
          h2 { +"❓ What is Docker?" }
          unorderedList(
            "Docker is a containerization technology",
            "Platform to build, ship and run any server apps",
            "Supports any language",
            "Supports many OSes",
            "One command per container",
          )
        }
      }

      dslSlide {
        content {
          h2 { +"🔬 Images and Containers" }
          h5 {
            +"""
            A Docker image is a lightweight, standalone, executable package of software 
            that includes everything needed to run an application: code, runtime, system tools, 
            system libraries and settings. 
           """
          }
          h5 { +"Images become containers when they are run." }
        }
      }
      dslSlide {
        content {
          h2 { +"🥊 Containers vs VMs" }
          +"Containers are isolated, but share OS and bins/libraries (where appropriate)"
          img { width = "1000px"; src = "images/docker-containers-and-vm.png" }
        }
      }

      dslSlide {
        content {
          h2 { +"👍 Advantages of Containerization" }
          unorderedList(
            "Consistent test environment for dev and QA",
            "Isolation and encapsulation of app dependencies",
            "Ability to scale efficiently, easily, and in real-time",
            "Enhances efficiency via easy reuse of images",
            "Great for getting apps to customers",
          )
        }
      }

      dslSlide {
        content {
          h2 { +"🚆 Getting Started with Docker" }
          unorderedList(
            "Download from https://docker.com and install",
            "Run Docker",
            "CLI or Desktop Interface",
            "Run a container",
            "Will automatically download a Docker image",
            "Can control volumes, network, DNS, etc.",
          )
        }
      }

      dslSlide {
        content {
          h2 { +"🏃‍ Running a Container" }
          codeSnippet {
            copyButton = false
            trim = false
            code = """
              
              docker run hello-world
              
              docker run -it ubuntu bash
              docker ps
              docker images
              
              docker run -it --rm debian bash
              
              
             """.trimIndent()
          }
        }
      }

      dslSlide {
        content {
          h2 { +"🧬 Container Lifecycle" }
          img { width = "1000px"; src = "images/container_life_cycle.png" }
        }
      }

      dslSlide {
        content {
          h2 { +"📜 Docker Examples" }
          br()
          codeSnippet {
            trim = false
            copyButton = false
            code = """
              
              git clone https://github.com/pambrose/docker-demo.git
              
              docker run -it --rm pambrose/asciiquarium
              
              docker run -p 8080:8888 -it --rm pambrose/nginx-demo
              
              docker run --rm -p 8080:8080 pambrose/ktor-demo    
              
              
            """.trimIndent()
          }
          a {
            href = "https://github.com/pambrose/docker-demo"
            +"https://github.com/pambrose/docker-demo"
          }
        }
      }

      dslSlide {
        content {
          h2 { +"🎆 Pushing to Docker Hub" }
          unorderedList(
            "Docker Registries",
            "https://hub.docker.com",
            "Naming conventions",
            "Versioning",
          )
        }
      }

      dslSlide {
        content {
          h2 { +"🏄 Deploying to Digital Ocean" }
          unorderedList(
            "https://www.digitalocean.com",
            "Choose resources",
            "Specify environment variables",
            "Choose region",
            "Scaling",
            "Integrate with other services",
          )
        }
      }

      dslSlide {
        content {
          h2 { +"🎛 Container Orchestration" }
          unorderedList(
            "Microservices",
            "Kubernetes",
            "Load balancing and traffic routing",
            "Monitoring and security",
            "Istio",
            "Hosting on AWS, Azure, and GCP",
          )
        }
      }

      dslSlide {
        content {
          h2 { +"🕴 Docker as a Business" }
          unorderedList(
            "K8s vs. Swarm",
            "Hykes' Departure",
            "K8s and CRI",
            "Sold off enterprise business to Mirantis",
            "Dual Licensing",
          )
        }
      }
    }
  }
}