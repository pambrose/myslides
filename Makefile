.PHONY: default help build-all stop clean build uberjar uber dist stage clean-docs sync-revealjs \
		    versioncheck upgrade-wrapper _require-gradle-version

GRADLE_VERSION := $(shell sed -n 's/^gradle *= *"\(.*\)"/\1/p' gradle/libs.versions.toml)

default: versioncheck

help:
	@echo "Available targets:"
	@echo "  help             Show this help message"
	@echo "  build-all        Build everything (alias for stage)"
	@echo "  stop             Stop the Gradle daemon"
	@echo "  clean            Remove Gradle build outputs"
	@echo "  build            Build the project (skipping tests)"
	@echo "  uberjar          Build the shadow (uber) jar"
	@echo "  uber             Stage and run the uber jar (pass ARGS=...)"
	@echo "  dist             Produce a runnable distribution under build/install/"
	@echo "  stage            Run the Gradle stage task"
	@echo "  clean-docs       Remove generated docs (playground, kroki)"
	@echo "  sync-revealjs    Sync the reveal.js assets"
	@echo "  versioncheck     Check for dependency updates (default target)"
	@echo "  upgrade-wrapper  Upgrade the Gradle wrapper to GRADLE_VERSION from libs.versions.toml"

build-all: stage

stop:
	./gradlew --stop

clean:
	./gradlew clean

build:
	./gradlew build -xtest

uberjar:
	./gradlew shadowJar

uber: stage
	java -jar build/libs/kslides.jar $(ARGS)

# Produces a runnable distribution under build/install/ via the application plugin.
dist:
	./gradlew installDist

stage:
	./gradlew stage

clean-docs:
	rm -rf docs/playground docs/kroki

sync-revealjs:
	./gradlew syncRevealJs

versioncheck:
	./gradlew dependencyUpdates --no-configuration-cache --no-parallel

# Gradle's documented upgrade procedure: the first run rewrites
# gradle-wrapper.properties using the *old* wrapper jar; the second run
# regenerates the wrapper itself with the new version.
upgrade-wrapper: _require-gradle-version
	./gradlew wrapper --gradle-version=$(GRADLE_VERSION) --distribution-type=bin
	./gradlew wrapper --gradle-version=$(GRADLE_VERSION) --distribution-type=bin

_require-gradle-version:
	@[ -n "$(GRADLE_VERSION)" ] || { echo "ERROR: Could not determine gradle version from gradle/libs.versions.toml" >&2; exit 1; }
