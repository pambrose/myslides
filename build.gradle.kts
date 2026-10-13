import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar.Companion.shadowJar
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
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

val projectName = "kslides"
val cleanTask = "clean"
val revealJsDir = "docs/revealjs"
val shadowJarTask = "shadowJar"
val jarName = "kslides.jar"
val revealJsPath = "revealjs"
val docsRevealJsDir = "docs/$revealJsPath"

application {
  mainClass.set(mainName)
}

dependencies {
  implementation(libs.bundles.kslides)
}

tasks.register<DefaultTask>("stage") {
  group = "build"
  description = "Clean and build $jarName — invoked by Heroku via Procfile."
  dependsOn(cleanTask, shadowJarTask)
}

configureKotlin()
configureShadowJar()
configureRevealSync()
configureVersions()

fun Project.configureKotlin() {
  extensions.configure<KotlinJvmProjectExtension> {
    jvmToolchain(libs.versions.jvm.get().toInt())

    // Run the unused-return-value checker over production code only. Kotest's
    // assertion DSL (e.g. shouldBe) returns its receiver, and tests intentionally
    // discard that result, so applying the checker to the test source set would
    // emit only false-positive warnings.
    tasks.named<KotlinCompile>("compileKotlin") {
      compilerOptions {
        freeCompilerArgs.add("-Xreturn-value-checker=check")
      }
    }
  }
}

fun Project.configureShadowJar() {
  tasks.shadowJar {
    isZip64 = true
    archiveFileName.set("$projectName.jar")

    // duplicatesStrategy takes precedence over transformers, so the default EXCLUDE would
    // drop duplicate entries before mergeServiceFiles()/KotlinModuleMetadataTransformer could
    // merge them. Let duplicates through globally, then restore EXCLUDE for every path no
    // transformer claims, so only the merged files are allowed to appear more than once.
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
    mergeServiceFiles()
    filesNotMatching(listOf("META-INF/services/**", "META-INF/*.kotlin_module")) {
      duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }

    exclude("META-INF/*.SF", "META-INF/*.DSA", "META-INF/*.RSA", "LICENSE*")
    mustRunAfter(cleanTask)
    manifest {
      attributes(
        "Implementation-Title" to projectName,
        "Implementation-Version" to version,
        "Built-Date" to Date(),
        "Built-JDK" to System.getProperty("java.version"),
        "Main-Class" to mainName,
      )
    }
  }
}

fun Project.configureRevealSync() {
// Unpack reveal.js assets from the kslides-core JAR into docs/revealjs/.
// Single source of truth lives in the kslides-core JAR (it ships them at
// classpath path revealjs/**); this task mirrors them onto disk so the
// generated docs/*.html have working JS/CSS references when published to
// Netlify / GitHub Pages.
  tasks.register<Sync>("syncRevealJs") {
    group = "kslides"
    description = "Unpacks reveal.js assets from the kslides-core JAR into $docsRevealJsDir/."

    val coreJar = configurations.runtimeClasspath.map { rc ->
      rc.files.single { it.name.startsWith("kslides-core-") }
    }

    from(coreJar.map { zipTree(it) }) {
      include("$revealJsPath/**")
      eachFile { relativePath = RelativePath(true, *relativePath.segments.drop(1).toTypedArray()) }
      includeEmptyDirs = false
    }
    into(layout.projectDirectory.dir(docsRevealJsDir))
  }

// Single source of truth for static assets: docs/ (committed for GitHub Pages). The generated
// pages reference them relative to the output root, so the HTTP server needs the same files
// under public/; copy rather than commit a second set.
  tasks.processResources {
    from(rootProject.layout.projectDirectory.dir("docs/images")) {
      into("public/images")
    }
    from(rootProject.layout.projectDirectory.file("docs/favicon.ico")) {
      into("public")
    }
  }
}

fun Project.configureVersions() {
  // A pre-release qualifier is a `.` or `-` delimiter followed by a known unstable
  // keyword. `m\d` matches milestones (`-M1`/`.M2`) without catching stable classifiers
  // like `-macos`/`-MR1`, and the `[.-]` delimiter catches both dash-style (`-alpha`)
  // and dot-style (Netty's `.Beta1`) qualifiers while leaving `-jre`/`.Final` stable.
  val preReleaseQualifier =
    Regex("""[.-](rc|beta|alpha|m\d|cr|snapshot|eap|dev|milestone|pre)""", RegexOption.IGNORE_CASE)

  fun isNonStable(version: String): Boolean = preReleaseQualifier.containsMatchIn(version)

  tasks.withType<DependencyUpdatesTask>().configureEach {
    notCompatibleWithConfigurationCache("the dependency updates plugin is not compatible with the configuration cache")
    // Reject a pre-release candidate only when the current version is stable. For
    // dependencies we intentionally track on a pre-release line (e.g. a detekt
    // alpha), newer pre-releases are still surfaced as available updates.
    rejectVersionIf {
      isNonStable(candidate.version) && !isNonStable(currentVersion)
    }

    // Prints only what is behind, and what could not be checked. The plugin's own report,
    // which `make versions-all` asks for with --output-formatter=plain, first lists every
    // dependency already up to date, with URLs and configuration names for each.
    outputFormatter {
      val behind = outdated.dependencies.sortedBy { "${it.group}:${it.name}" }
      if (behind.isEmpty()) println("All dependencies are up to date")
      behind.forEach { d ->
        val latest = with(d.available) { release ?: milestone ?: integration }
        println(" - ${d.group}:${d.name} ${d.version} -> $latest")
      }
      unresolved.dependencies.sortedBy { "${it.group}:${it.name}" }.forEach {
        println(" ? ${it.group}:${it.name}:${it.version} could not be checked")
      }
      if (gradle.current.isUpdateAvailable) {
        println(" - Gradle ${gradle.running.version} -> ${gradle.current.version}")
      }
    }
  }
}
