#!/bin/bash

java -Xmx8192m -jar target/benchmarks.jar Add.* AddRemove.* Remove.* Iterate.* -wi 10 -i 10 -r 500ms -w 500ms -prof gc -rf csv -rff "results/add-remove-iterate-benchmarks.csv"