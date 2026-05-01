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

## Tooling

- Gradle 9.5.0 (Kotlin DSL).
- Kotlin 2.3.21.
- Versions and plugin coordinates live in `gradle/libs.versions.toml`.
- Shaded fat-jar produced by the Shadow plugin.
