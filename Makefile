.PHONY: default build-all stop clean build uberjar uber dist stage clean-docs sync-revealjs versioncheck upgrade-wrapper

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

uber: uberjar
	java -jar build/libs/kslides.jar

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
	./gradlew wrapper --gradle-version=9.5.0 --distribution-type=bin
