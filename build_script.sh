#!/bin/bash

COMMIT_MESSAGE="run-all-benchmarks"
RESULT_FILE_PATH="results/$(date +%Y.%m.%d-%H:%M:%S)-add-remove-$COMMIT_MESSAGE.csv"
java -Xmx8192m -jar target/benchmarks.jar Add.* AddRemove.*, Remove.*, Iterate.* -wi 10 -i 10 -r 500ms -w 500ms -prof gc -rf csv -rff $RESULT_FILE_PATH