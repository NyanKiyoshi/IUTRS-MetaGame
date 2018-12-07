#!/bin/sh

export PROJECT_PATH="$(realpath $(dirname $0))"
. "${PROJECT_PATH}/build.env"

# Change CWD to the project directory
cd $PROJECT_PATH || { echo "Failed to change dir to ${PROJECT_PATH}" 1>&2; exit 1; }

# Clean up before building
[ -d build/ ] && rm -R build
[ -d "$RELEASE_PATH" ] && rm -R "$RELEASE_PATH"

# Compile the java doc to `./build` and change the CWD to that dir
mkdir -pv build \
    && mkdir -pv "$RELEASE_PATH" \
    && javac -d build $(find src -name '*.java') \
    && cd build

# Fail if something went wrong
return_code=$?
[ $return_code -ne 0 ] && { echo 'Failed to build.' 1>&2; exit $return_code; }

# Archive the compiled java to a jar files, for every manifest
(export IFS='
'
for manifest_entry_name in $(ls ../META-INF/); do
    manifest_path="../META-INF/${manifest_entry_name}/MANIFEST.MF"
    target_jar_path="${RELEASE_PATH}/${manifest_entry_name}-metagame.jar"
    echo "Building: ${target_jar_path} (${manifest_path})"
    jar cmvf "${manifest_path}" "${target_jar_path}" *
done)
