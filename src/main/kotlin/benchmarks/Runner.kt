package benchmarks

import org.openjdk.jmh.results.RunResult
import org.openjdk.jmh.runner.Runner
import org.openjdk.jmh.runner.options.OptionsBuilder
import org.openjdk.jmh.runner.options.TimeValue
import java.io.FileWriter

fun main(args: Array<String>) {

    for (implementation in args) {
        val outputFile = "teamcityArtifacts/$implementation.csv"
        val options = OptionsBuilder()
                .jvmArgs("-Xms3072m", "-Xmx3072m")
//                .include("$implementation.Add.*")
//                .include("$implementation.Add.addFirst$")
//                .include("$implementation.Add.addLast$")
//                .include("$implementation.Add.addFirstAddLast$")
//                .include("$implementation.Remove.*")
//                .include("$implementation.AddRemove.*")
                .include("$implementation.Iterate.*")
                .warmupIterations(10)
                .measurementIterations(10)
                .warmupTime(TimeValue.milliseconds(500))
                .measurementTime(TimeValue.milliseconds(500))
//                .param("childCount", CHILD_COUNT_8, CHILD_COUNT_16, CHILD_COUNT_32, CHILD_COUNT_64)
                .param("listSize", "10000000", "100000000")
                .addProfiler("gc")

        val runResults = Runner(options.build()).run()
        printResults(runResults, implementation, outputFile)
    }
}

fun printResults(runResults: Collection<RunResult>, implementation: String, outputFile: String) {
    val csvHeader = "Implementation,Method,listSize,childCount,bufferSize,Score,Score Error,Allocation Rate"

    val fileWriter = FileWriter(outputFile)

    fileWriter.appendln(csvHeader)

    runResults.forEach {
        fileWriter.appendln(csvRowFrom(it, implementation))
    }

    fileWriter.flush()
    fileWriter.close()
}

fun csvRowFrom(result: RunResult, implementation: String): String {
    val nanosInMicros = 1000
    val method = result.primaryResult.getLabel()
    val listSize = result.params.getParam("listSize").toInt()
    val childCount = result.params.getParam("childCount").toInt()
    val bufferSize = result.params.getParam("bufferSize").toInt()
    val score = result.primaryResult.getScore() * nanosInMicros / listSize
    val scoreError = result.primaryResult.getScoreError() * nanosInMicros / listSize
    val allocationRate = result.secondaryResults["·gc.alloc.rate.norm"]!!.getScore() / listSize

    return "$implementation,$method,$listSize,$childCount,$bufferSize,%.3f,%.3f,%.3f"
                .format(score, scoreError, allocationRate)
}