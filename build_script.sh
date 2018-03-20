#!/bin/bash

java -Xmx8192m -jar target/benchmarks.jar baseline.Add.* baseline.AddRemove.* baseline.Remove.* baseline.Iterate.* \
    -wi 10 -i 10 -r 500ms -w 500ms \
    -prof gc \
     -rf csv -rff "results/add-remove-iterate-baseline-benchmarks.csv"