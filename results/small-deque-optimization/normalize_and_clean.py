# -*- coding: utf-8 -*-
import sys
import argparse
import pandas as pd
import numpy as np
import csv

outputFile = open("normalized-benchmarks.csv", "w")
outputBenchmarks = [["Implementation", "Method", "Score", "Score Error", "Allocation Rate", "listSize"]]

benchmarks = {
    "baseline": "/Users/bigdreamer/Programming/ImmutableListBenchmarks/results/2018.02.07-09:16:46-baseline-add-remove.csv",
    "rrbTree": "/Users/bigdreamer/Programming/ImmutableListBenchmarks/results/2018.02.07-09:37:15-rrbTree-add-remove.csv",
    "persistentDeque_original": "/Users/bigdreamer/Programming/ImmutableListBenchmarks/results/2018.02.07-08:17:44-persistentDeque-add-remove-a-small-cleanup.csv",
    "persistentDeque_optimize": "/Users/bigdreamer/Programming/ImmutableListBenchmarks/results/small-deque-optimization/addLast-optimize-addLast.csv",
    "persistentDeque_more_optimize": "/Users/bigdreamer/Programming/ImmutableListBenchmarks/results/small-deque-optimization/addLast-more-addLast-optimization.csv",
    "persistentDeque_set_min_move_8": "/Users/bigdreamer/Programming/ImmutableListBenchmarks/results/small-deque-optimization/addLast-set-min-move-next-level-count-8.csv",
    "persistentDeque_set_min_move_12": "/Users/bigdreamer/Programming/ImmutableListBenchmarks/results/small-deque-optimization/addLast-set-min-move-next-level-count-12.csv",
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
                    ".addLastRemoveLast"]

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
        perfScoreError = list(perf["Score Error (99,9%)"].values)

        allocRate = df.loc[df["Benchmark"] == allocRateSuffix]
        allocRateScore = list(allocRate["Score"].values)
        allocRateScoreError = list(allocRate["Score Error (99,9%)"].values)

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
