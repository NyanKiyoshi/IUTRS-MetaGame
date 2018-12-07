#!/bin/sh

PROJECT_PATH="$(realpath $(dirname $0))"

[ -z "$JAVA_HOME_OVERRIDE" ] && JAVA_HOME_OVERRIDE=/usr/lib/jvm/java-8-openjdk;

mkdir -pv "${PROJECT_PATH}/docs" \
    && "${JAVA_HOME_OVERRIDE}/bin/javadoc" \
        -locale en_US \
        -private \
        -splitindex \
        -nodeprecated \
        -link https://docs.oracle.com/javase/8/docs/api/ \
        -d "${PROJECT_PATH}/docs" \
        -classpath $(find "${PROJECT_PATH}/src" -name '*.java')

exit $?
