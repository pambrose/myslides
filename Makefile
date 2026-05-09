.PHONY: default build-all stop clean build uberjar uber dist stage clean-docs sync-revealjs versioncheck upgrade-wrapper

GRADLE_VERSION := $(shell sed -n 's/^gradle *= *"\(.*\)"/\1/p' gradle/libs.versions.toml)

default: versioncheck

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

upgrade-wrapper:
	./gradlew wrapper --gradle-version=$(GRADLE_VERSION) --distribution-type=bin
