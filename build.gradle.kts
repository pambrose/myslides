import java.util.*

plugins {
  application
  alias(libs.plugins.kotlin.jvm)
  alias(libs.plugins.ben.manes.versions)
  alias(libs.plugins.shadow)
}

// mainName matters only if you are hosting your slides via HTTP
// Change mainName to the name of Kotlin file that has the presentation you want to serve
val mainName = "SlidesKt"

application {
  mainClass.set(mainName)
}

dependencies {
  implementation(libs.bundles.kslides)
}

tasks.shadowJar {
  isZip64 = true
  archiveFileName.set("kslides.jar")
  mergeServiceFiles()
  exclude("META-INF/*.SF", "META-INF/*.DSA", "META-INF/*.RSA", "LICENSE*")
  mustRunAfter("clean")
  manifest {
    attributes(
      "Implementation-Title" to "kslides",
      "Implementation-Version" to version,
      "Built-Date" to Date(),
      "Built-JDK" to System.getProperty("java.version"),
      "Main-Class" to mainName,
    )
  }
}

tasks.register("stage") { dependsOn("clean", "shadowJar") }

kotlin {
  jvmToolchain(17)
}

// Unpack reveal.js assets from the kslides-core JAR into docs/revealjs/.
// Single source of truth lives in the kslides-core JAR (it ships them at
// classpath path revealjs/**); this task mirrors them onto disk so the
// generated docs/*.html have working JS/CSS references when published to
// Netlify / GitHub Pages.
tasks.register<Sync>("syncRevealJs") {
  group = "kslides"
  description = "Unpacks reveal.js assets from the kslides-core JAR into docs/revealjs/."

  val coreJar = configurations.runtimeClasspath.map { rc ->
    rc.files.single { it.name.startsWith("kslides-core-") }
  }

  from(coreJar.map { zipTree(it) }) {
    include("revealjs/**")
    eachFile { relativePath = RelativePath(true, *relativePath.segments.drop(1).toTypedArray()) }
    includeEmptyDirs = false
  }
  into(layout.projectDirectory.dir("docs/revealjs"))
}

// Single source of truth for images assets: docs/images/ (committed for GitHub Pages).
tasks.processResources {
  from(rootProject.layout.projectDirectory.dir("docs/images")) {
    into("public/images")
  }
}
