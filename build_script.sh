#!/bin/bash

java -Xmx8192m -jar target/benchmarks.jar Add.* AddRemove.* Remove.* Iterate.* \
    -wi 10 -i 10 -r 500ms -w 500ms \
    -p impl=INITIAL_IMPL,STACK_25_IMPL,ARRAY_25_IMPL,ARRAY_25S_IMPL \
    -prof gc \
     -rf csv -rff "results/add-remove-iterate-25-benchmarks.csv"