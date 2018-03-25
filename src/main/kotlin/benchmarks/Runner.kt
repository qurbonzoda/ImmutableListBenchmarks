package benchmarks

import org.openjdk.jmh.results.RunResult
import org.openjdk.jmh.runner.Runner
import org.openjdk.jmh.runner.options.OptionsBuilder
import org.openjdk.jmh.runner.options.TimeValue
import java.io.FileWriter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun main(args: Array<String>) {
    val impl = "persistentDeque"
    val benchTitle = ""
    val outputFile = "teamcityArtifacts/${currentDateTime()}-$impl-$benchTitle.csv"

    val fileWriter = FileWriter(outputFile)

    for (arg in args) {
        fileWriter.appendln(arg)
    }

    fileWriter.flush()
    fileWriter.close()

//    val options = OptionsBuilder()
////            .include("$impl.Add.addLast$")
//            .include("$impl.Add.*")
//            .include("$impl.Remove.*")
//            .include("$impl.AddRemove.*")
//            .warmupIterations(10)
//            .measurementIterations(10)
//            .warmupTime(TimeValue.seconds(1))
//            .measurementTime(TimeValue.seconds(1))
////            .param("listSize", BM_1000000)
//            .addProfiler("gc")
//
//    val runResults = Runner(options.build()).run()
//    printResults(runResults, impl, outputFile)
}

fun currentDateTime(): String {
    val dateTime = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd-HH:mm")
    return dateTime.format(formatter)
}

fun printResults(runResults: Collection<RunResult>, implementation: String, outputFile: String) {
    val csvHeader = "Implementation,Method,listSize,Score,Score Error,Allocation Rate"

    val fileWriter = FileWriter(outputFile)

    fileWriter.appendln(csvHeader)

    for (result in runResults) {
        val row = csvRowFrom(result, implementation)
        fileWriter.appendln(row)
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

    return "$implementation,$method,$listSize,%.3f,%.3f,%.3f".format(score, scoreError, allocationRate)
}