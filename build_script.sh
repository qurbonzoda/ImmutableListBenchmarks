#!/bin/bash

COMMIT_MESSAGE="commit-message"
IMPL="persistentDeque"
RESULT_FILE_PATH="results/$(date +%Y.%m.%d-%H:%M:%S)-$IMPL-add-remove-$COMMIT_MESSAGE.csv"
java -jar target/benchmarks.jar $IMPL.Add $IMPL.Remove -wi 10 -i 10 -prof gc -rf csv -rff $RESULT_FILE_PATH
git add results/
git -c user.name='qurbonzoda' -c user.email='a.qurbonzoda@gmail.com' commit -m $COMMIT_MESSAGE