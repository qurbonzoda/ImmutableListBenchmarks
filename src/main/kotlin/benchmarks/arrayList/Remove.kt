package benchmarks.arrayList

import benchmarks.*
import org.openjdk.jmh.annotations.*
import java.util.concurrent.TimeUnit

@Fork(1)
@Warmup(iterations = 5)
@Measurement(iterations = 5)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
open class Remove {
    @Param(BM_1, BM_3, BM_6, BM_10, BM_15, BM_25, BM_50, BM_100, BM_1000)
    var listSize: Int = 0

    var list = listOf<String>()

    @Setup(Level.Invocation)
    fun prepare() {
        val listPrepare = mutableListOf<String>()
        repeat(times = listSize) {
            listPrepare.add("some element")
        }
        list = listPrepare.toList()
    }

    @Benchmark
    fun removeFirst(): List<String> {
        repeat(times = listSize) {
            list = list.drop(1)
        }
        return list
    }

    @Benchmark
    fun removeLast(): List<String> {
        repeat(times = listSize) {
            list = list.dropLast(1)
        }
        return list
    }

    @Benchmark
    fun removeFirstRemoveLast(): List<String> {
        repeat(times = listSize shr 1) {
            list = list.drop(1)
            list = list.dropLast(1)
        }
        return list
    }
}
