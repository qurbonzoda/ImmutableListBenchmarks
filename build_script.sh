#!/bin/bash

IMPL="persistentDeque"
COMMIT_MESSAGE="cleanup-size-25-benchmarks"
RESULT_FILE_PATH="results/small-deque-optimization/$(date +%Y.%m.%d-%H:%M:%S)-$IMPL-add-remove-$COMMIT_MESSAGE.csv"
java -Xmx8192m -jar target/benchmarks.jar $IMPL.Add -wi 10 -i 20 -r 1 -w 1 -prof gc -rf csv -rff $RESULT_FILE_PATH