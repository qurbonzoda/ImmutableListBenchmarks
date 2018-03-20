#!/bin/bash

java -Xmx8192m -jar target/benchmarks.jar persistentDeque.Add.* persistentDeque.AddRemove.* persistentDeque.Remove.* persistentDeque.Iterate.* \
    -wi 10 -i 10 -r 500ms -w 500ms \
    -p impl=STACK_7_IMPL,STACK_8_IMPL,STACK_9_IMPL,STACK_12_IMPL,STACK_13_IMPL,STACK_19_IMPL,STACK_19B_IMPL \
    -prof gc \
     -rf csv -rff "results/add-remove-iterate-7-8-9-12-13-19-benchmarks.csv"