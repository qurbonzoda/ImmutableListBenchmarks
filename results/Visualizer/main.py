# -*- coding: utf-8 -*-
import sys
import argparse
import matplotlib.pyplot as plt
import pandas as pd
import numpy as np

parser = argparse.ArgumentParser()
parser.add_argument("-name", nargs="+", required=True)
parser.add_argument("-input", nargs="+", required=True)
parser.add_argument("-listSize", nargs="+", required=False)
parser.add_argument("-method", nargs="+", required=False)

args = parser.parse_args()

benchmarkNames = args.name
inputFiles = args.input

print(benchmarkNames)
print(inputFiles)

benchmarkMethods = args.method
if benchmarkMethods is None:
    benchmarkMethods = [".addFirst",
                        ".addLast",
                        ".addFirstAddLast",
                        ".removeFirst",
                        ".removeLast",
                        ".removeFirstRemoveLast"]

assert(len(benchmarkNames) == len(inputFiles))

for i in range(len(inputFiles)):
    inputFile = inputFiles[i]
    benchmarkName = benchmarkNames[i]

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

        if args.listSize is None:
            listSize = list(perf["Param: listSize"].values)
        else:
            listSize = list(map(int, args.listSize))

        perfScore_norm = [s * 1000 / listSize[i] for i, s in enumerate(perfScore)]
        perfScoreError_norm = [se * 1000 / listSize[i] for i, se in enumerate(perfScoreError)]
        allocRateScore_norm = [a / listSize[i] for i, a in enumerate(allocRateScore)]
        allocRateScoreError_norm = [ae / listSize[i] for i, ae in enumerate(allocRateScoreError)]

        # print(perfScore_norm)
        # print(perfScoreError_norm)
        # print(allocRateScore_norm)
        # print(allocRateScoreError_norm)

        fig, ax = plt.subplots()
        DefaultSize = fig.get_size_inches()
        fig.set_size_inches( (DefaultSize[0], DefaultSize[1]-0.25) )
        fig.canvas.set_window_title(benchmarkName if benchmarkName is not None else "")

        index = np.arange(len(listSize))
        bar_width = 0.15

        opacity = 0.4
        error_config = {'ecolor': '0.3'}

        rects1 = ax.bar(index, perfScore_norm, bar_width,
                        alpha=opacity, color='b',
                        yerr=perfScoreError_norm, error_kw=error_config,
                        label='perfScore_norm(ns/op)')

        rects2 = ax.bar(index + bar_width, allocRateScore_norm, bar_width,
                        alpha=opacity, color='r',
                        yerr=allocRateScoreError_norm, error_kw=error_config,
                        label='allocRateScore_norm(B/op)')

        ax.set_xlabel('listSize')
        ax.set_title(perfSuffix)
        ax.set_xticks(index + bar_width)
        ax.set_xticklabels(listSize)
        ax.legend(loc='upper center', bbox_to_anchor=(0.5, 1.18), ncol=2)

plt.show()
