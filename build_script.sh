#!/bin/bash

IMPL="persistentDeque"
COMMIT_MESSAGE="initial-size-15-benchmarks"
RESULT_FILE_PATH="results/small-deque-optimization/$(date +%Y.%m.%d-%H:%M:%S)-$IMPL-add-remove-$COMMIT_MESSAGE.csv"
java -Xmx8192m -jar target/benchmarks.jar $IMPL.Add $IMPL.Remove $IMPL.AddRemove -wi 10 -i 20 -r 1 -w 1 -prof gc -rf csv -rff $RESULT_FILE_PATH