# Release Notes

Human-friendly highlights for each release of `myslides`. For a structured
per-version diff, see [CHANGELOG.md](CHANGELOG.md).

---

## v1.4.1 — 2026-08-08

Three months of build and toolchain work with no change to how the decks are authored. The seven topic decks still live one-per-file under `src/main/kotlin/`, and running `fun main()` in `Slides.kt` from IntelliJ is still the way to generate them.

**Four kslides releases in one hop.** The library went 1.0.0 → **1.4.0** over this stretch, and `/docs` was regenerated against each bump. Three of those changes are visible in the published site:

- **The favicon link resolves now.** kslides 1.4.0 writes it relative (`favicon.ico`) instead of root-absolute (`/favicon.ico`). This site is published under a subpath — `https://pambrose.github.io/myslides` — so a leading `/` had been resolving against the *account* root and missing entirely. The duplicate `rel="shortcut icon"` link went away with it; every browser since IE 10 tokenizes `shortcut` to plain `icon`, so it was the same link emitted twice.
- **Viewers can pinch-zoom.** The viewport meta dropped `maximum-scale=1.0, user-scalable=no`, which blocked zoom and fails WCAG 2.1 SC 1.4.4. The two `apple-mobile-web-app-*` metas went with it — reveal.js 3-era carry-over, inert without a web-app manifest.
- **Deck CSS survives printing.** kslides 1.3.0 emits embedded `<style>` blocks without `media="screen"`, so custom styling no longer drops out of reveal.js's `?print-pdf` view. Before this, the corner links rendered full-width when printed and pushed each deck down a page.

**Code blocks fit the slide.** The slide-definition slides run 85–92 characters wide, which reveal.js's default 0.55em could not hold. `Slides.kt` now sets a global size and lets the rare outlier wrap rather than scroll:

```kotlin
css += """
  .reveal pre { font-size: 0.60em; }
  .reveal pre code { white-space: pre-wrap; word-break: break-word; }
"""
```

Two per-slide escape hatches come with it — `.smallcode` (0.40em) and `.largecode` (0.70em) — applied on an individual slide with `classes += "smallcode"`. A two-class selector like `.reveal .smallcode pre` outranks the global `.reveal pre` on specificity, so the override wins regardless of declaration order.

**`make help` lists itself.** `make` with no arguments now prints a colorized target list built by an `awk` pass over each target's trailing `## description` comment, so the listing cannot drift from the Makefile. `make upgrade-wrapper` joins it, re-pinning the Gradle wrapper to whatever `gradle/libs.versions.toml` says. That catalog is now the single source for the JVM toolchain and the wrapper version too — `build.gradle.kts` reads the `jvm` key, the `Makefile` reads `gradle-wrapper` with `sed`, and neither number is written twice. The old `versioncheck` target is now `versions`.

**The uberjar's resource merging was quietly broken.** Shadow applies `duplicatesStrategy` *before* its transformers run, and Shadow 9 defaults it to `EXCLUDE` — which reduced `mergeServiceFiles()` and the Kotlin metadata transformer to first-copy-wins and produced about eighty warnings per build. The fix is two lines:

```kotlin
duplicatesStrategy = DuplicatesStrategy.INCLUDE
filesNotMatching(listOf("META-INF/services/**", "META-INF/*.kotlin_module")) {
  duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
```

`INCLUDE` lets the transformers see every copy; the narrowing puts `EXCLUDE` back everywhere no transformer is listening, so ordinary duplicate resources still can't pile up in the jar. Nothing was actually being lost on today's classpath — there is exactly one Ktor engine and one SLF4J provider — but adding a second of either would have silently dropped a provider entry. Verified with `./gradlew shadowJar --rerun-tasks`: no warnings, and the jar's entry list is unchanged at 11,499 entries with no duplicated paths.

**Dependency bumps.** kslides **1.4.0**, Kotlin **2.4.10**, Shadow **9.6.1**, the Gradle wrapper **9.7.0**, and the ben-manes versions plugin **0.60.0** — whose plugin id also moved from `com.github.ben-manes.versions` to `io.github.ben-manes.versions`. Gradle's configuration cache is on; `dependencyUpdates` is the one task that can't handle it, and `make versions` opts out on its behalf.

**The favicon actually loads now.** Every page has always linked one, but the only committed copy lived under `src/main/resources/public/`, which serves the HTTP mode alone — so both published sites returned a 404 for it. It now lives at `docs/favicon.ico` and is copied into the resources output at build time, the same arrangement `docs/images/` has used all along. One committed copy, both delivery paths served.

**Housekeeping.** `docs/revealjs/` was re-synced from the kslides-core 1.2.0 JAR, which carries a newer reveal.js and adds `plugin/mermaid/mermaid.min.js`. Cores 1.3.0 and 1.4.0 ship that distribution unchanged, so `make sync-revealjs` is a no-op for both — run it after a bump and check `git status` rather than assuming. The HTTP deck's title image is a committed `docs/images/http/rest-http.png` instead of a hotlinked `img.ifunny.co` URL, and `LICENSE` is now `LICENSE.txt`. `gradle.properties` also lost the quotes around `group` and `version`, which Gradle had been taking literally — the plain `jar` task now writes `myslides-1.4.1.jar` rather than `myslides-"1.4.1".jar`. `app.json` finally describes this repo rather than `kslides-template`, and `make clean-docs` sweeps `docs/mermaid` along with `docs/playground` and `docs/kroki`.

> **Upgrading a checkout:** nothing to do beyond pulling. If you keep the three companion repos (`c-basics`, `dsl-demo`, `redis-demo`) alongside this one, they are unchanged — they are gitignored working copies, and the decks fall back to GitHub raw URLs whenever `PORT` is set.

---

## v1.4.0 and earlier

No release notes were kept. See `git log` for the history.
