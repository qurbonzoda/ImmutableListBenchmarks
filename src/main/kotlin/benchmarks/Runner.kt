package benchmarks

import benchmarks.persistentDeque.*
import org.openjdk.jmh.results.RunResult
import org.openjdk.jmh.runner.Runner
import org.openjdk.jmh.runner.options.OptionsBuilder
import org.openjdk.jmh.runner.options.TimeValue
import java.io.FileWriter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun main(args: Array<String>) {

    val impls = listOf(
//            "arrayList",
//            "baseline",
//            "clojurePVector",
//            "dexxPVector",
//            "javaSlangPVector",
//            "scalaPVector",
//            "rrbTree",
//            "treePVector",
            "persistentDeque"
    )

    for (impl in impls) {
        val outputFile = "teamcityArtifacts/${currentDateTime()}-$impl.csv"

        val options = OptionsBuilder()
                .include("$impl.Add.*")
                .include("$impl.Remove.*")
                .include("$impl.AddRemove.*")
                .include("$impl.Iterate.*")
                .warmupIterations(10)
                .measurementIterations(10)
                .warmupTime(TimeValue.milliseconds(500))
                .measurementTime(TimeValue.milliseconds(500))
                .param("impl", STACK_25_IMPL, STACK_25O_IMPL)
                .addProfiler("gc")

        val runResults = Runner(options.build()).run()
        printResults(runResults, impl, outputFile)
    }
}

fun currentDateTime(): String {
    val dateTime = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd-HH:mm")
    return dateTime.format(formatter)
}

fun printResults(runResults: Collection<RunResult>, implementation: String, outputFile: String) {
    val csvHeader = "Implementation,Method,listSize,bufferImpl,Score,Score Error,Allocation Rate"

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
    val impl = result.params.getParam("impl")
    val score = result.primaryResult.getScore() * nanosInMillis / listSize
    val scoreError = result.primaryResult.getScoreError() * nanosInMillis / listSize
    val allocationRate = result.secondaryResults["·gc.alloc.rate.norm"]!!.getScore() / listSize

    return "$implementation,$method,$listSize,$impl,%.3f,%.3f,%.3f".format(score, scoreError, allocationRate)
}