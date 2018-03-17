# -*- coding: utf-8 -*-
import sys
import argparse
import pandas as pd
import numpy as np
import csv

outputFile = open("all-implementations-normalized-benchmarks.csv", "w")
outputBenchmarks = [["Implementation", "Method", "Score", "Score Error", "Allocation Rate", "listSize"]]

benchmarks = {
    "baseline": "/Users/bigdreamer/Programming/ImmutableListBenchmarks/results/tc-ec2-34-242-6-107/2018.02.24-09-27-56-baseline-add-remove-benchmarks.csv",
    "arrayList": "/Users/bigdreamer/Programming/ImmutableListBenchmarks/results/tc-ec2-34-242-6-107/2018.02.24-11:09:49-arrayList-add-remove-benchmarks.csv",
    "rrbTree": "/Users/bigdreamer/Programming/ImmutableListBenchmarks/results/tc-ec2-34-242-6-107/2018.02.24-15:03:28-rrbTree-add-remove-benchmarks.csv",
    "treePVector": "/Users/bigdreamer/Programming/ImmutableListBenchmarks/results/tc-ec2-34-242-6-107/2018.02.24-17:34:17-treePVector-add-remove-benchmarks.csv",
    "persistentDeque_original": "/Users/bigdreamer/Programming/ImmutableListBenchmarks/results/tc-ec2-34-242-6-107/2018.02.24-21:19:54-persistentDeque-add-remove-a-small-cleanup-benchmarks.csv",
    "persistentDeque_fixed_size_buffers": "/Users/bigdreamer/Programming/ImmutableListBenchmarks/results/tc-ec2-34-242-6-107/2018.02.25-00-12-49-persistentDeque-add-remove-fixed-size-buffers-benchmarks.csv",
    "persistentDeque_store_elements_in_array": "/Users/bigdreamer/Programming/ImmutableListBenchmarks/results/tc-ec2-34-242-6-107/2018.02.25-05:24:55-persistentDeque-add-remove-store-elements-in-array-benchmarks.csv",
    "persistentDeque_stack_based_buffers_initial": "/Users/bigdreamer/Programming/ImmutableListBenchmarks/results/tc-ec2-34-242-6-107/2018.02.25-09-56-04-persistentDeque-add-remove-stack-based-buffers-initial-benchmarks.csv",
    "persistentDeque_stack_based_buffers_size_25": "/Users/bigdreamer/Programming/ImmutableListBenchmarks/results/tc-ec2-34-242-6-107/2018.02.25-11:39:47-persistentDeque-add-remove-stack-based-buffers-size-25-benchmarks.csv",
    "persistentDeque_stack_based_buffers_size_12": "/Users/bigdreamer/Programming/ImmutableListBenchmarks/results/tc-ec2-34-242-6-107/2018.02.25-13-45-10-persistentDeque-add-remove-stack-based-buffers-size-12-benchmarks.csv",
    "persistentDeque_stack_based_buffers_size_15": "/Users/bigdreamer/Programming/ImmutableListBenchmarks/results/tc-ec2-34-242-6-107/2018.02.25-15-31-05-persistentDeque-add-remove-stack-based-buffers-size-15-benchmarks.csv",
    "persistentDeque_stack_based_buffers_size_9": "/Users/bigdreamer/Programming/ImmutableListBenchmarks/results/tc-ec2-34-242-6-107/2018.02.25-21:06:12-persistentDeque-add-remove-stack-based-buffers-size-9-benchmarks.csv",
    "persistentDeque_stack_based_buffers_size_5": "/Users/bigdreamer/Programming/ImmutableListBenchmarks/results/tc-ec2-34-242-6-107/2018.02.26-00:55:31-persistentDeque-add-remove-stack-based-buffers-size-5-benchmarks.csv",
    "persistentDeque_SDO_size_25": "/Users/bigdreamer/Programming/ImmutableListBenchmarks/results/small-deque-optimization/2018.03.14-19-43-57-persistentDeque-add-remove-cleanup-size-25-benchmarks.csv",
}

benchmarkMethods = [".addFirst",
                    ".addLast",
                    ".addFirstAddLast",
                    ".removeFirst",
                    ".removeLast",
                    ".removeFirstRemoveLast",
                    ".addFirstRemoveFirst",
                    ".addFirstRemoveLast",
                    ".addLastRemoveFirst",
                    ".addLastRemoveLast",
                    ".addLastAndIterate"]

for benchmarkName, inputFile in benchmarks.items():
    df = pd.read_csv(inputFile, header=0)

    for method in benchmarkMethods:
        perfSuffix = [bench for bench in df["Benchmark"].values if bench.endswith(method)]
        if len(perfSuffix) == 0:
            continue

        perfSuffix = perfSuffix[0]
        allocRateSuffix = [bench for bench in df["Benchmark"].values if bench.endswith(method + ':·gc.alloc.rate.norm')][0]

        perf = df.loc[df["Benchmark"] == perfSuffix]
        perfScore = list(perf["Score"].values)
        perfScoreError = list(perf["Score Error (99.9%)"].values)

        allocRate = df.loc[df["Benchmark"] == allocRateSuffix]
        allocRateScore = list(allocRate["Score"].values)
        allocRateScoreError = list(allocRate["Score Error (99.9%)"].values)

        listSize = list(perf["Param: listSize"].values)

        perfScore_norm = [s * 1000 / listSize[i] for i, s in enumerate(perfScore)]
        perfScoreError_norm = [se * 1000 / listSize[i] for i, se in enumerate(perfScoreError)]
        allocRateScore_norm = [a / listSize[i] for i, a in enumerate(allocRateScore)]
        allocRateScoreError_norm = [ae / listSize[i] for i, ae in enumerate(allocRateScoreError)]

        for i in range(len(listSize)):
            bench = [benchmarkName, method, perfScore_norm[i], perfScoreError_norm[i], allocRateScore_norm[i], listSize[i]]
            outputBenchmarks.append(bench)

        # print(perfScore_norm)
        # print(perfScoreError_norm)
        # print(allocRateScore_norm)
        # print(allocRateScoreError_norm)

with outputFile:
    writer = csv.writer(outputFile)
    writer.writerows(outputBenchmarks)
