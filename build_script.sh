#!/bin/bash

IMPL="persistentDeque"
COMMIT_MESSAGE="$IMPL-add-remove-initial-size-9-benchmarks"
RESULT_FILE_PATH="results/small-deque-optimization/$(date +%Y.%m.%d-%H:%M:%S)-$COMMIT_MESSAGE.csv"
java -jar target/benchmarks.jar $IMPL.Add $IMPL.Remove $IMPL.AddRemove -wi 10 -i 20 -r 1 -w 1 -prof gc -rf csv -rff $RESULT_FILE_PATH
git add results/
git -c user.name='qurbonzoda' -c user.email='a.qurbonzoda@gmail.com' commit -m $COMMIT_MESSAGE