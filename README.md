# Paul's Slides

A collection of presentations authored with [kslides](https://github.com/kslides/kslides).
The decks cover C, Docker, Kotlin DSLs, functional programming, HTTP, Redis,
and Unix.

## Hosted

- GitHub Pages: <https://pambrose.github.io/myslides>
- Netlify: <https://pambrose-slides.netlify.app>

## Layout

- `src/main/kotlin/` — presentation sources. `Slides.kt` is the entry point;
  each topic deck lives in its own file (`C.kt`, `Docker.kt`, `Dsl.kt`,
  `Functional.kt`, `Http.kt`, `Redis.kt`, `Unix.kt`).
- `docs/` — generated static site published to GitHub Pages / Netlify.
- `c-basics/` — companion C source files for the C-fundamentals deck
  (independent git repo, see its own README).
- `dsl-demo/` — companion Kotlin sources for the DSL deck (independent git
  repo, see its own README).
- `redis-demo/` — companion Kotlin sources for the Redis deck.

The three companion directories are listed in `.gitignore` and are **not**
obtained by cloning this repo — clone them separately if you want to run
locally with local playground sources (see [Playground
sources](#playground-sources-local-vs-github)).

## Build & run

Toolchain: JDK 17, resolved via the foojay convention plugin.

The usual way to regenerate the decks is to run `fun main()` in `Slides.kt`
straight from IntelliJ. One run writes the static HTML to `docs/`
(`enableFileSystem = true`) **and** serves it over HTTP
(`enableHttp = true`).

```sh
make                         # list every target (the default)
make build                   # ./gradlew build -xtest — there are no tests
make uberjar                 # build build/libs/kslides.jar
make uber                    # build the uberjar and run it (honours $PORT, else 8080)
make stage                   # clean + shadowJar, as Heroku invokes it
make sync-revealjs           # re-unpack the reveal.js assets (see below)
make versions                # report dependency updates
make upgrade-wrapper         # re-pin the Gradle wrapper from libs.versions.toml
make clean-docs              # remove docs/playground, docs/kroki and docs/mermaid
```

Versions live in `gradle/libs.versions.toml` — kslides, Kotlin, Shadow, the
JVM toolchain (`jvm`), and the Gradle wrapper (`gradle-wrapper`). Bump them
there rather than in `build.gradle.kts` or the `Makefile`, both of which read
the catalog. This repo's own version is `version` in `gradle.properties`, and
is unrelated to the kslides library version.

For local kroki diagram rendering, run a kroki container at
`http://localhost:8000` (the URL is wired into `Slides.kt`). Rendered output
is committed under `docs/kroki/`; mermaid output lands in `docs/mermaid/`.

## Playground sources (local vs GitHub)

`Slides.kt` computes:

```kotlin
val useLocal = System.getenv("PORT").orEmpty().isEmpty()
```

and passes it to the C, DSL, and Redis decks. With no `PORT` set — a local
run — their playground slides read the companion sources from `c-basics/`,
`dsl-demo/`, and `redis-demo/` on disk. When `PORT` is set, as on Heroku, they
fall back to GitHub raw URLs, so a deployed deck needs none of those
directories present.

## Publishing under a subpath

The site is served from `https://pambrose.github.io/myslides`, not from a
domain root, so **no path may start with `/`** — a leading slash resolves
against the GitHub account root and misses. Every deck sits flat at the output
root (`c.html`, `docker.html`, …), so no deck needs a `../` prefix.

As of kslides 1.4.0 the library resolves *asset* paths against the output root
for you — `logo(...)`, `topLeftSvgSrc` / `topRightSvgSrc`, slide background
images, `favicon`, and the playground / diagram iframes. Corner *links*
(`topLeftHref`, `topRightHref`) and any path written inside slide content are
emitted verbatim, so those are yours to keep relative.

## reveal.js assets (`docs/revealjs/`)

The reveal.js JS/CSS bundle lives inside the `kslides-core` JAR at the
classpath path `revealjs/**`. The generated `docs/*.html` files reference
those assets at `revealjs/...`, so for the static site (GitHub Pages /
Netlify) to load them they have to exist on disk under `docs/revealjs/`.

The `syncRevealJs` Gradle task (defined in `build.gradle.kts`) unpacks them
out of the JAR into `docs/revealjs/`:

```sh
./gradlew syncRevealJs
# or:
make sync-revealjs
```

Run it after upgrading `kslides-core` and check `git status` — whether it
changes anything depends on the release. Core 1.2.0 brought a newer reveal.js
and added `plugin/mermaid/`; 1.3.0 and 1.4.0 ship the distribution unchanged.

It is a `Sync` task, so `docs/revealjs/` is made to match the JAR *exactly* —
anything hand-added there is deleted on the next run. The contents are
committed so GitHub Pages can serve them without a build step.

## Static assets (`docs/` is the single source of truth)

Both the published static site and the HTTP-served presentations need the same
images and favicon. Rather than maintain two copies, `docs/` is the single
source of truth — it is committed and served directly by GitHub Pages /
Netlify.

When the project is built, `tasks.processResources` (in `build.gradle.kts`)
copies `docs/images/` to `public/images/` and `docs/favicon.ico` to `public/`
in the runtime classpath, so the embedded HTTP server (which reads from
`src/main/resources/public/`) sees the exact same assets the static site does.
Those copies land in the build output under
`build/resources/main/public/`; there is deliberately no
`src/main/resources/public/images/` directory and no second committed
`favicon.ico`.

To add or update an image: drop it under `docs/images/<topic>/` and commit.
Both delivery paths pick it up on the next build. Anything else both modes
must serve belongs in `docs/` with a matching `from(...)` line in
`processResources`.

## The uberjar

`make uberjar` runs `shadowJar`, which writes `build/libs/kslides.jar` with
`SlidesKt` as its `Main-Class` — that is what the `Procfile` runs on Heroku.

Two lines in the `shadowJar` block are load-bearing and easy to drop by
accident:

```kotlin
duplicatesStrategy = DuplicatesStrategy.INCLUDE
filesNotMatching(listOf("META-INF/services/**", "META-INF/*.kotlin_module")) {
  duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
```

Gradle applies `duplicatesStrategy` *before* Shadow's transformers run, so
Shadow's `EXCLUDE` default silently reduces `mergeServiceFiles()` and the
Kotlin metadata transformer to first-copy-wins — and emits roughly eighty
warnings saying so. `INCLUDE` fixes the merge; the narrowing puts `EXCLUDE`
back everywhere no transformer is listening, so ordinary duplicate resources
can't pile up in the jar. When changing this block, verify with
`./gradlew shadowJar --rerun-tasks` — `filesNotMatching` actions are not
tracked as task inputs, so a plain build can hand back a cached jar.

## Tooling

- Gradle 9.7.0 (Kotlin DSL), configuration cache enabled.
- Kotlin 2.4.10, kslides 1.4.0.
- Versions and plugin coordinates live in `gradle/libs.versions.toml`.
- Shaded fat-jar produced by the Shadow plugin.

## History

- [CHANGELOG.md](CHANGELOG.md) — structured per-version diff.
- [RELEASE_NOTES.md](RELEASE_NOTES.md) — narrative highlights per release.
- [CLAUDE.md](CLAUDE.md) — repository guidance, including known rough edges.
