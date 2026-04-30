import java.util.Date

plugins {
  application
  alias(libs.plugins.kotlin.jvm)
  alias(libs.plugins.ben.manes.versions)
  alias(libs.plugins.shadow)
//  id("org.jmailen.kotlinter") version "4.5.0"
}

// mainName matters only if you are hosting your slides via HTTP
// Change mainName to the name of Kotlin file that has the presentation you want to serve
val mainName = "SlidesKt"

application {
  mainClass.set(mainName)
}

group = "com.github.pambrose"
version = "1.3.0"

dependencies {
  implementation(libs.kslides.core)
}

tasks.shadowJar {
  isZip64 = true
  archiveFileName.set("kslides.jar")
  mergeServiceFiles()
  exclude("META-INF/*.SF", "META-INF/*.DSA", "META-INF/*.RSA", "LICENSE*")
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
tasks.shadowJar { mustRunAfter("clean") }

kotlin {
  jvmToolchain(17)
}

tasks.compileKotlin {
  compilerOptions {
    freeCompilerArgs.add("-Xbackend-threads=8")
  }
}

tasks.compileTestKotlin {
  compilerOptions {
    freeCompilerArgs.add("-Xbackend-threads=8")
  }
}

//kotlinter {
//  ignoreFailures = false
//  reporters = arrayOf("checkstyle", "plain")
//}
