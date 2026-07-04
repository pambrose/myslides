.PHONY: default help build-all stop clean build uberjar uber dist stage clean-docs sync-revealjs \
		    versions upgrade-wrapper _require-gradle-version

GRADLE_VERSION := $(shell sed -n 's/^gradle-wrapper = "\(.*\)"/\1/p' gradle/libs.versions.toml)

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

clean-docs: ## Remove generated docs (playground, kroki)
	rm -rf docs/playground docs/kroki

uberjar: ## Build the shaded uberjar (build/libs/kslides.jar)
	./gradlew shadowJar

# Matches Procfile (-DPORT=$PORT); falls back to 8080 for local runs.
uber: uberjar ## Build and run the uberjar (honours $PORT, defaults to 8080)
	java -DPORT=$${PORT:-8080} -jar build/libs/kslides.jar $(ARGS)

sync-revealjs: ## Sync the reveal.js assets
	./gradlew syncRevealJs

versions: ## Check for dependency updates
	./gradlew dependencyUpdates --no-configuration-cache --no-parallel

# Gradle's documented upgrade procedure: the first run rewrites
# gradle-wrapper.properties using the *old* wrapper jar; the second run
# regenerates the wrapper itself with the new version.
upgrade-wrapper: _require-gradle-version ## Upgrade the Gradle wrapper to GRADLE_VERSION from libs.versions.toml
	./gradlew wrapper --gradle-version=$(GRADLE_VERSION) --distribution-type=bin
	./gradlew wrapper --gradle-version=$(GRADLE_VERSION) --distribution-type=bin

_require-gradle-version:
	@[ -n "$(GRADLE_VERSION)" ] || { echo "ERROR: Could not determine gradle version from gradle/libs.versions.toml" >&2; exit 1; }
