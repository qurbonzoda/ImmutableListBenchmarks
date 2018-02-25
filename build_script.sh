#!/bin/bash

IMPL="persistentDeque"
COMMIT_MESSAGE="$IMPL-add-remove-stack-based-buffers-size-15-benchmarks"
RESULT_FILE_PATH="results/tc-ec2-34-242-6-107/$(date +%Y.%m.%d-%H:%M:%S)-$COMMIT_MESSAGE.csv"
java -jar target/benchmarks.jar $IMPL.Add $IMPL.Remove $IMPL.AddRemove -wi 10 -i 20 -r 1 -w 1 -prof gc -rf csv -rff $RESULT_FILE_PATH
git add results/
git -c user.name='qurbonzoda' -c user.email='a.qurbonzoda@gmail.com' commit -m $COMMIT_MESSAGE