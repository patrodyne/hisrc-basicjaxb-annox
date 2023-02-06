#!/bin/bash
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
	echo "Debian:"
	echo "  apt-cache search openjdk"
	echo "  sudo apt-get install openjdk-XX-jdk"
	echo "  sudo apt-get install openjdk-XX-source"
	echo "  sudo update-java-alternatives --set java-1.XX.0-openjdk-amd64"
	exit 1
fi

BASEDIR=$( cd -- "$( dirname -- "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )
source ${BASEDIR}/build-INC.sh

if [ $# -eq 0 ]; then
  ${BASEDIR}/build.sh
else
  source ${BASEDIR}/build-CFG.sh
  mvn ${JVM_SYS_PROPS} "$@"
fi

# mvn -DskipTests=true clean install
# mvn -DskipTests=true -Pnexus-deploy clean deploy
# mvn -DskipTests=true -DdryRun=false release:clean
# mvn -DskipTests=true -DdryRun=true release:prepare
# mvn -DskipTests=true -DdryRun=true release:perform
