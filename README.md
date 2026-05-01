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

## Build & run

Toolchain: JDK 17 (resolved via the foojay convention plugin).

```sh
./gradlew build              # compile + test
./gradlew shadowJar          # build build/libs/kslides.jar
java -jar build/libs/kslides.jar   # serve presentations over HTTP

make uber                    # clean + shadowJar + run, in one step
make stage                   # clean + shadowJar (used for deploys)
make versioncheck            # report dependency updates
make sync-revealjs           # refresh bundled reveal.js assets
```

`Slides.kt` writes the static HTML to `docs/` (`enableFileSystem = true`) and
also serves it over HTTP (`enableHttp = true`). For local kroki diagram
rendering, run a kroki container at `http://localhost:8000` (the URL is wired
in `Slides.kt`).

## reveal.js assets (`docs/revealjs/`)

The reveal.js JS/CSS bundle lives inside the `kslides-core` JAR at the
classpath path `revealjs/**`. The generated `docs/*.html` files reference
those assets at `revealjs/...`, so for the static site (GitHub Pages /
Netlify) to load them they have to exist on disk under `docs/revealjs/`.

The `syncRevealJs` Gradle task (defined in `build.gradle.kts`) unpacks them
out of the JAR into `docs/revealjs/`. Run it after upgrading `kslides-core`
or any time the `docs/revealjs/` tree gets out of sync:

```sh
./gradlew syncRevealJs
# or:
make sync-revealjs
```

The contents of `docs/revealjs/` are committed so GitHub Pages can serve them
without a build step.

## Images (`docs/images/` is the single source of truth)

Both the published static site and the HTTP-served presentations need access
to the same image assets. Rather than maintain two copies, `docs/images/` is
the single source of truth — it is committed and served directly by GitHub
Pages / Netlify.

When the project is built, `tasks.processResources` (in `build.gradle.kts`)
copies `docs/images/` into the runtime classpath at `public/images/`, so the
embedded HTTP server (which reads from `src/main/resources/public/`) sees the
exact same assets the static site does. The `src/main/resources/public/images/`
directory is **not** committed and exists only as a build artifact.

To add or update an image: drop it under `docs/images/<topic>/` and commit.
Both delivery paths pick it up on the next build.

## Tooling

- Gradle 9.5.0 (Kotlin DSL).
- Kotlin 2.3.21.
- Versions and plugin coordinates live in `gradle/libs.versions.toml`.
- Shaded fat-jar produced by the Shadow plugin.
