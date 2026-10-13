.PHONY: default help build-all stop clean build uberjar uber dist stage clean-docs sync-revealjs \
		    versions versions-all upgrade-wrapper _require-gradle-version

# The wrapper's current Gradle version; `make upgrade-wrapper GRADLE_VERSION=x.y.z` moves to another one.
# gradle-wrapper.properties is the only place it is kept, so Dependabot's wrapper PRs leave nothing stale.
GRADLE_VERSION ?= $(shell sed -n 's/^distributionUrl=.*gradle-\(.*\)-bin\.zip$$/\1/p' gradle/wrapper/gradle-wrapper.properties)

default: help

help:  ## Show this help (list of targets)
	@awk 'BEGIN {FS = ":.*?## "; printf "Usage: make <target>\n\nTargets:\n"} \
		/^[a-zA-Z0-9_-]+:.*?## / {printf "  \033[36m%-22s\033[0m %s\n", $$1, $$2}' $(MAKEFILE_LIST)

build-all: stage ## Build everything (alias for stage)

stop: ## Stop the Gradle daemon
	./gradlew --stop

clean: ## Remove Gradle build outputs
	./gradlew clean

build: ## Build the project (skipping tests)
	./gradlew build -xtest

# Produces a runnable distribution under build/install/ via the application plugin.
dist: ## Produce a runnable distribution under build/install/
	./gradlew installDist

stage: ## Heroku stage build (clean + shadowJar)
	./gradlew stage

clean-docs: ## Remove generated docs (playground, kroki, mermaid)
	rm -rf docs/playground docs/kroki docs/mermaid

uberjar: ## Build the shaded uberjar (build/libs/kslides.jar)
	./gradlew shadowJar

# Matches Procfile (-DPORT=$PORT); falls back to 8080 for local runs.
uber: uberjar ## Build and run the uberjar (honours $PORT, defaults to 8080)
	java -DPORT=$${PORT:-8080} -jar build/libs/kslides.jar $(ARGS)

sync-revealjs: ## Sync the reveal.js assets
	./gradlew syncRevealJs

# dependencyUpdates is marked incompatible with the configuration cache
# (build.gradle.kts), so a run with the cache on builds an entry only to
# discard it, and says so. --no-configuration-cache skips the cache for this
# run instead. build.gradle.kts gives the task a formatter that lists only
# what is behind; versions-all names the plugin's own plain report, which
# also lists everything up to date.
versions: ## List the dependencies with newer versions, and any that could not be checked
	./gradlew dependencyUpdates --no-configuration-cache --no-parallel

versions-all: ## The plugin's full dependency report, up-to-date dependencies included
	./gradlew dependencyUpdates --no-configuration-cache --no-parallel --output-formatter=plain

# Gradle's documented upgrade procedure: the first run rewrites
# gradle-wrapper.properties using the *old* wrapper jar; the second run
# regenerates the wrapper itself with the new version.
# Example: make upgrade-wrapper GRADLE_VERSION=9.8.1
upgrade-wrapper: _require-gradle-version ## Upgrade the Gradle wrapper (GRADLE_VERSION=x.y.z; default: the current one)
	./gradlew wrapper --gradle-version=$(GRADLE_VERSION) --distribution-type=bin
	./gradlew wrapper --gradle-version=$(GRADLE_VERSION) --distribution-type=bin

_require-gradle-version:
	@[ -n "$(GRADLE_VERSION)" ] || { echo "ERROR: Could not determine the Gradle version from gradle/wrapper/gradle-wrapper.properties" >&2; exit 1; }
