#!/bin/sh
#
# Usage: build-JXX.sh [option(s)] [goal(s)]
# Example: build-JXX.sh clean install
#
# Profile Id: none - default, install common jars to local repository.
# Profile Id: samples - package sample plus default projects.
# Profile Id: all - package the above plus templates and tutorials.
#
# How to build and test:
#
#   1) build-JXX.sh -DskipTests=true clean install
#   2) build-JXX.sh -DskipTests=true -Pall clean package
#   3) build-JXX.sh -DskipTests=false -Pall test
#   Notes:
#     Step #1 installs the shared libraries to your local maven repository.
#     Step #2 packages the shared, test and sample projects.
#     Step #3 unit test the shared, test and sample projects.
#
if [ ! -d "${JAVA_HOME}" ]; then
	echo "Please configure Java home path."
	exit 1
fi
# DEBUG_OPTS="-X -Dorg.slf4j.simpleLogger.showLogName=true"
# BUILD_OPTS="--fail-at-end -DskipTests=true $@"
  BUILD_OPTS="--fail-at-end $@"
  mvn ${DEBUG_OPTS} ${BUILD_OPTS}
