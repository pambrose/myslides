# Changelog

All notable changes to this project are documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

This file starts at 1.4.1. Earlier history lives in `git log`.

## [Unreleased]

## [1.4.2] - 2026-10-13

A small release: kslides 1.5.1 and a current toolchain, font sizing moved onto the kslides config API, two fixes to the functional-programming deck, and Dependabot. How the decks are authored is unchanged — run `fun main()` in `Slides.kt`.

### Added

- `.github/dependabot.yml`: weekly Gradle version-update PRs for `gradle/libs.versions.toml` and the Gradle wrapper, each release held back 7 days (`cooldown`). `kslides-core` gets a group of its own so it never arrives bundled with other bumps; Dependabot cannot run `make sync-revealjs` or regenerate `/docs`, so a kslides PR still needs both done by hand. Other minor and patch updates share one group. Nothing builds these PRs — the repo has no workflows.
- `make versions-all`, which prints the ben-manes plugin's full plain-text report — every up-to-date dependency, its URL, and the configurations that declare it — by passing `--output-formatter=plain`.

### Changed

- kslides 1.4.0 → **1.5.0**. 1.5.0 emits Markdown slide content as a text node instead of parsing it as XML, so character references now reach the page as written rather than being decoded at build time. Regenerating `/docs` after the bump touched only `docs/http.html`, where two slides' `<span>.</span>` and `<span>&amp;</span>` became the `&#46;` and `&#38;` that `Http.kt` actually contains; the browser decodes both, so the slides read the same. `docs/revealjs/` is unchanged — 1.5.0 ships the same reveal.js distribution as 1.4.0.
- kslides 1.5.0 → **1.5.1**, a maintenance release with no code changes beyond KDoc. It moves the library onto Ktor 3.6.0 and kotlin-css 2026.10.0 and builds it with Kotlin 2.4.21. Its reveal.js distribution is byte-identical to the committed `docs/revealjs/`, so `make sync-revealjs` has nothing to do.
- Kotlin 2.4.10 → **2.4.21** and the Gradle wrapper 9.7.0 → **9.8.1** (`gradle-wrapper.properties`, `gradle-wrapper.jar`, `gradlew.bat`). The build stays warning-free on both.
- Font sizes now use the kslides config API instead of hand-written CSS. `Slides.kt` sets `slideConfig { codeFontSize = "0.60em"; codeWrap = true }` in place of the global `.reveal pre` / `pre code` rules (available since kslides 1.2.0, which describes it as replacing the raw-CSS approach), and `playgroundConfig { fontSize = "20px"; lineHeight = "25px" }` in place of the three hand-written `.CodeMirror` / `.code-output` rules (kslides 1.4.0). Rendering is unchanged; regenerating `/docs` drops 922 lines.
- Four playgrounds in the functional-programming deck override the global size so their wider samples fit the iframe: _Functional Argument_ (`Hof2.kt`), the first _List of Functional Arguments_ (`Hof3a.kt`), and _Filter Calls 2_ (`Filter1b.kt`) at 18px, and the second _List of Functional Arguments_ (`Hof3b.kt`) at 15px, all with `lineHeight = "1.2"`.
- `make versions` now lists only what is behind, one line each (` - group:name current -> latest`), followed by any dependency that could not be checked (` ? …`) and a pending Gradle update. The formatter lives in `configureVersions()` in `build.gradle.kts`; the plugin's default report, which first lists everything already current, is what `make versions-all` prints.
- The Gradle version is now kept only in `gradle/wrapper/gradle-wrapper.properties`. The `gradle-wrapper` key is gone from `gradle/libs.versions.toml`: Dependabot's wrapper PRs cannot update it, and a stale value would have made the next `make upgrade-wrapper` downgrade the wrapper. `make upgrade-wrapper` reads the current version from the properties file and takes a new one as `make upgrade-wrapper GRADLE_VERSION=x.y.z`.
- The ben-manes versions plugin 0.60.0 → **0.66.0**. 0.60.0's `dependencyUpdates` has no `--output-formatter` option, so `make versions-all` needs the bump.

### Removed

- The `.smallcode` (0.40em) and `.largecode` (0.70em) rules. They were copied from `kslides-template` in `a9e31b2` along with a comment describing the `slideDefinition` slides that use them there, but this repo has no `slideDefinition` calls and never applied either class — they were shipped into all eight pages and matched nothing. A slide that needs a different size now sets `codeFontSize` in its own `slideConfig { }`.

### Fixed

- Two-digit line numbers in code blocks no longer wrap one digit per line. The hand-written `word-break: break-word` rule inherited into the highlight plugin's line-number gutter; `codeWrap` emits `.reveal pre code .hljs-ln-numbers { white-space: nowrap; word-break: normal; }` alongside its wrapping rule.
- The functional-programming deck's two consecutive _Filter Calls_ slides are now titled _Filter Calls 1_ and _Filter Calls 2_, so they can be told apart in the slide menu and overview.

### Documentation

- `README.md`, `CLAUDE.md`, and `llms.txt` describe Dependabot, `make versions-all`, where the Gradle version now lives, and the current toolchain versions.

## [1.4.1] - 2026-08-08

Three months of toolchain and build work, and four kslides releases' worth of regenerated output. Nothing about how the decks are authored changed — every deck still lives in its own file under `src/main/kotlin/` and is generated by running `fun main()` in `Slides.kt`.

### Added

- A colorized `make help`, generated by an `awk` pass over the trailing `## description` comments on each target, so the listing cannot drift from the Makefile. `make` with no arguments now defaults to `help`.
- `make upgrade-wrapper`, which re-pins the Gradle wrapper to the `gradle-wrapper` key in `gradle/libs.versions.toml` (running `./gradlew wrapper` twice, per Gradle's documented procedure).
- `jvm` and `gradle-wrapper` keys in the version catalog. `build.gradle.kts` reads `jvm` for `jvmToolchain(...)`; the `Makefile` reads `gradle-wrapper` via `sed`. Neither version is written in two places any more.
- A `kslides` bundle in the version catalog, so `dependencies { }` declares `implementation(libs.bundles.kslides)`.
- `-Xreturn-value-checker=check` on the production Kotlin compilation, flagging ignored return values.
- Code-block sizing CSS in `Slides.kt`: `.reveal pre { font-size: 0.60em }` with `white-space: pre-wrap` so an occasional over-long line wraps instead of overflowing the slide, plus per-slide `.smallcode` (0.40em) and `.largecode` (0.70em) opt-ins applied with `classes += "..."`.
- Copy-code button configuration — `copyCodeConfig { }` in the global `presentationConfig`, and the `ALWAYS` button / `ICONS` display variants on the functional-programming deck.
- `docs/revealjs/plugin/mermaid/mermaid.min.js`, picked up from the kslides-core 1.2.0 JAR, which added a mermaid diagram DSL and ships the plugin with its reveal.js distribution.
- Gradle's configuration cache (`org.gradle.configuration-cache=true`). `dependencyUpdates` is the one incompatible task, and `make versions` opts out for it.

### Changed

- Dependency arc across the release: kslides 1.0.0 → **1.4.0**, Kotlin 2.3.21 → **2.4.10**, the Shadow plugin 9.4.1 → **9.6.1**, the Gradle wrapper 9.5.0 → **9.7.0**, and the ben-manes versions plugin 0.54.0 → **0.60.0**. The ben-manes plugin id also moved from `com.github.ben-manes.versions` to `io.github.ben-manes.versions`.
- Regenerated `/docs` against each kslides bump — 1.2.0, 1.3.0, and 1.4.0. See _Fixed_ for what visibly changed.
- Re-synced `docs/revealjs/` from the kslides-core 1.2.0 JAR, which carries a newer reveal.js distribution (`dist/`, `js/controllers/`). kslides-core 1.3.0 and 1.4.0 ship that distribution unchanged, so `make sync-revealjs` is a no-op for both — run it after a core bump and check `git status` rather than assuming either way.
- Pruned the committed reveal.js tree of unused theme sources (`css/theme/source/*.scss`) and plugins. `syncRevealJs` is a `Sync` task, so the tree is now exactly what the JAR ships and hand-pruning it would not survive the next sync — kslides-core no longer ships those sources, which is what keeps them gone.
- The HTTP deck's title-slide image is a committed `docs/images/http/rest-http.png` instead of a hotlinked `img.ifunny.co` URL.
- `build.gradle.kts`: merged the two `shadowJar` blocks into one, collapsed the per-compilation Kotlin config, and extracted the repeated path/task-name literals into named values.
- `make uber` passes `$ARGS` through to the jar and honours `$PORT` (defaulting to 8080) to match the `Procfile`. The `versioncheck` target is now `versions`.
- `LICENSE` renamed to `LICENSE.txt`.

### Fixed

- **Shadow's resource transformers actually merge now.** `shadowJar` sets `duplicatesStrategy = DuplicatesStrategy.INCLUDE`; Gradle applies the strategy *before* the transformers run, so Shadow 9's `EXCLUDE` default had been reducing `mergeServiceFiles()` and the built-in `KotlinModuleMetadataTransformer` to first-copy-wins. Shadow 9.6.1 surfaced this as roughly eighty warnings during a build. Nothing was actually being lost on the current classpath — there is one `HttpClientEngineContainer` and one SLF4J provider — but a second Ktor engine or logging backend would have silently lost a provider entry.
- Narrowed that strategy back to `EXCLUDE` for every path no transformer claims, via `filesNotMatching(listOf("META-INF/services/**", "META-INF/*.kotlin_module"))`, so `INCLUDE` cannot leave genuinely duplicated entries in the uberjar. Verified with `./gradlew shadowJar --rerun-tasks`: no warnings, and the jar's entry list is unchanged at 11,499 entries with no duplicated paths.
- **Deck CSS applies when printing.** kslides 1.3.0 emits embedded `<style>` blocks without the `media="screen"` attribute, so custom styling no longer drops out of reveal.js's `?print-pdf` view. Previously the corner links rendered full-width there and pushed every deck down a page.
- **The favicon link resolves under the GitHub Pages subpath.** kslides 1.4.0 emits it relative (`favicon.ico`) rather than root-absolute (`/favicon.ico`), which on `https://pambrose.github.io/myslides` had resolved against the account root. The duplicate `rel="shortcut icon"` companion link — an IE ≤10 alias — is gone too.
- **The favicon resolves on the published site.** Every generated page links `favicon.ico`, but the only committed copy was `src/main/resources/public/favicon.ico`, which serves the HTTP mode only — GitHub Pages and Netlify both 404'd on it. The file moved to `docs/favicon.ico`, and `processResources` now copies it to `public/` in the resources output, matching how `docs/images/` is handled. Still exactly one committed copy.
- `app.json` describes this repo instead of carrying `kslides-template`'s boilerplate `name`, `description`, and `website`.
- `make clean-docs` removes `docs/mermaid` along with `docs/playground` and `docs/kroki`. Mermaid slides have generated into `docs/mermaid/` since kslides 1.2.0, and the target's hard-coded path list had not kept up.
- Unquoted `group` and `version` in `gradle.properties`. Gradle had been treating the quotes as part of the string, so the plain `jar` task emitted `build/libs/myslides-"1.4.0".jar` and the manifest read `Implementation-Version: "1.4.0"`. Deployment was never affected — the `Procfile` runs `kslides.jar`, whose name is pinned by `archiveFileName`.
- **Viewers can pinch-zoom.** The generated viewport meta is now `width=device-width, initial-scale=1.0`, dropping the `maximum-scale=1.0, user-scalable=no` that blocked zoom and fails WCAG 2.1 SC 1.4.4, along with the two inert `apple-mobile-web-app-*` metas.

### Documentation

- Added this changelog, plus `RELEASE_NOTES.md`, `CLAUDE.md`, and `llms.txt`.
- `README.md`: refreshed the stale toolchain versions and `make` targets, and documented the Makefile, the uberjar's duplicate handling, the companion demo repos and the `PORT`-driven local/GitHub source switch, and publishing under a subpath.

[Unreleased]: https://github.com/pambrose/myslides/compare/1.4.2...HEAD
[1.4.2]: https://github.com/pambrose/myslides/compare/1.4.1...1.4.2
[1.4.1]: https://github.com/pambrose/myslides/compare/ae811a5...1.4.1
