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
                .include("$implementation.Add.*")
                .include("$implementation.Remove.*")
                .include("$implementation.AddRemove.*")
                .include("$implementation.Iterate.*")
                .warmupIterations(10)
                .measurementIterations(10)
                .warmupTime(TimeValue.milliseconds(500))
                .measurementTime(TimeValue.milliseconds(500))
                .addProfiler("gc")

        val runResults = Runner(options.build()).run()
        printResults(runResults, implementation, outputFile)
    }
}

fun printResults(runResults: Collection<RunResult>, implementation: String, outputFile: String) {
    val csvHeader = "Implementation,Method,listSize,bufferType,bufferSize,Score,Score Error,Allocation Rate"

    val fileWriter = FileWriter(outputFile)

    fileWriter.appendln(csvHeader)

    runResults.forEach {
        fileWriter.appendln(csvRowFrom(it, implementation))
    }

    fileWriter.flush()
    fileWriter.close()
}

fun csvRowFrom(result: RunResult, implementation: String): String {
    val nanosInMillis = 1000
    val method = result.primaryResult.getLabel()
    val listSize = result.params.getParam("listSize").toInt()
    val score = result.primaryResult.getScore() * nanosInMillis / listSize
    val scoreError = result.primaryResult.getScoreError() * nanosInMillis / listSize
    val allocationRate = result.secondaryResults["·gc.alloc.rate.norm"]!!.getScore() / listSize

    val impl = result.params.getParam("impl")
    val bufferType: String
    val bufferSize: String

    if (impl == null) {
        bufferType = ""
        bufferSize = ""
    } else {
        val firstDelimiter = impl.indexOfFirst { it == '_' }
        val lastDelimiter = impl.indexOfLast { it =='_' }
        val sizeEnd = firstDelimiter + 1 + impl.substring(firstDelimiter + 1).indexOfFirst { !it.isDigit() }
        bufferType = impl.substring(0, firstDelimiter) + impl.substring(sizeEnd, lastDelimiter)
        bufferSize = impl.substring(firstDelimiter + 1, sizeEnd)
    }

    return "$implementation,$method,$listSize,$bufferType,$bufferSize,%.3f,%.3f,%.3f"
                .format(score, scoreError, allocationRate)
}