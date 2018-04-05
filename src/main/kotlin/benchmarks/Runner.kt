package benchmarks

import benchmarks.persistentDeque.STACK_25_IMPL
import org.openjdk.jmh.results.RunResult
import org.openjdk.jmh.runner.Runner
import org.openjdk.jmh.runner.options.OptionsBuilder
import org.openjdk.jmh.runner.options.TimeValue
import java.io.FileWriter

fun main(args: Array<String>) {

//    val impls = listOf(
//            "arrayList",
//            "baseline"
//            "clojurePVector",
//            "dexxPVector",
//            "javaSlangPVector",
//            "scalaPVector",
//            "rrbTree",
//            "treePVector",
//            "persistentDeque"
//    )

    val outputFile = "teamcityArtifacts/impls.csv"
    val fileWriter = FileWriter(outputFile)
    fileWriter.appendln(args.reduce { first, second -> first + "\n" + second } )
    fileWriter.flush()
    fileWriter.close()

//    for (impl in impls) {
//
//        val options = OptionsBuilder()
//                .jvmArgs("-Xms3072m", "-Xmx3072m")
//                .include("$impl.Add.*")
//                .include("$impl.Remove.*")
//                .include("$impl.AddRemove.*")
//                .include("$impl.Iterate.*")
//                .warmupIterations(10)
//                .measurementIterations(10)
//                .warmupTime(TimeValue.milliseconds(500))
//                .measurementTime(TimeValue.milliseconds(500))
//                .param("impl", STACK_25_IMPL)
//                .param("listSize", "10000000")
//                .addProfiler("gc")
//
//        val runResults = Runner(options.build()).run()
//        printResults(runResults, impl, outputFile)
//    }
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