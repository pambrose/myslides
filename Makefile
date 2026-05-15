.PHONY: default help build-all stop clean build uberjar uber dist stage clean-docs sync-revealjs \
		    versioncheck upgrade-wrapper _require-gradle-version

GRADLE_VERSION := $(shell sed -n 's/^gradle = "\(.*\)"/\1/p' gradle/libs.versions.toml)

default: versioncheck

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

uberjar: ## Build the shadow (uber) jar
	./gradlew shadowJar

uber: stage ## Stage and run the uber jar (pass ARGS=...)
	java -jar build/libs/kslides.jar $(ARGS)

# Produces a runnable distribution under build/install/ via the application plugin.
dist: ## Produce a runnable distribution under build/install/
	./gradlew installDist

stage: ## Run the Gradle stage task
	./gradlew stage

clean-docs: ## Remove generated docs (playground, kroki)
	rm -rf docs/playground docs/kroki

sync-revealjs: ## Sync the reveal.js assets
	./gradlew syncRevealJs

versioncheck: ## Check for dependency updates (default target)
	./gradlew dependencyUpdates --no-configuration-cache --no-parallel

# Gradle's documented upgrade procedure: the first run rewrites
# gradle-wrapper.properties using the *old* wrapper jar; the second run
# regenerates the wrapper itself with the new version.
upgrade-wrapper: _require-gradle-version ## Upgrade the Gradle wrapper to GRADLE_VERSION from libs.versions.toml
	./gradlew wrapper --gradle-version=$(GRADLE_VERSION) --distribution-type=bin
	./gradlew wrapper --gradle-version=$(GRADLE_VERSION) --distribution-type=bin

_require-gradle-version:
	@[ -n "$(GRADLE_VERSION)" ] || { echo "ERROR: Could not determine gradle version from gradle/libs.versions.toml" >&2; exit 1; }
