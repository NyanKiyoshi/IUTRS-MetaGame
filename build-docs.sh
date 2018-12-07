#!/bin/sh

export PROJECT_PATH="$(realpath $(dirname $0))"
. "${PROJECT_PATH}/build.env"

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
