package benchmarks

import benchmarks.persistentDeque.*
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
                .param("impl", STACK_7_IMPL, STACK_8_IMPL, STACK_9_IMPL, STACK_12_IMPL, STACK_13_IMPL,
                        STACK_19_IMPL, STACK_19B_IMPL, STACK_25_IMPL, STACK_25O_IMPL, STACK_31_IMPL,
                        STACK_32_IMPL, STACK_48_IMPL, STACK_61_IMPL, STACK_64_IMPL, INITIAL_IMPL)
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
    val (bufferType, bufferSize) = buffer(impl)

    return "$implementation,$method,$listSize,$bufferType,$bufferSize,%.3f,%.3f,%.3f"
                .format(score, scoreError, allocationRate)
}

fun buffer(impl: String?): Pair<String, String> {
    val bufferType: String
    val bufferSize: String

    if (impl == null) {
        bufferType = ""
        bufferSize = ""
    } else if (impl.startsWith("STACK") || impl.startsWith("ARRAY")) {
        val firstDelimiter = impl.indexOfFirst { it == '_' }
        val lastDelimiter = impl.indexOfLast { it =='_' }
        val sizeEnd = firstDelimiter + 1 + impl.substring(firstDelimiter + 1).indexOfFirst { !it.isDigit() }
        bufferType = impl.substring(0, firstDelimiter) + impl.substring(sizeEnd, lastDelimiter)
        bufferSize = impl.substring(firstDelimiter + 1, sizeEnd)
    } else {
        bufferType = impl
        bufferSize = ""
    }

    return Pair(bufferType, bufferSize)
}