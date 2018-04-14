package benchmarks.baseline

import benchmarks.*
import org.openjdk.jmh.annotations.*
import java.util.*
import java.util.concurrent.TimeUnit

@Fork(1)
@Warmup(iterations = 5)
@Measurement(iterations = 5)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
open class Remove {
    @Param(BM_1, BM_4, BM_10, BM_15, BM_20, BM_25, BM_35, BM_50, BM_75,
            BM_100, BM_1000, BM_10000, BM_100000, BM_1000000)
    var listSize: Int = 0

    private var filledList = LinkedList<String>()

    @Setup(Level.Invocation)
    fun prepare() {
        filledList = LinkedList(List(listSize, { "some element" }))
    }

    @Benchmark
    fun removeFirst(): List<String> {
        val list = filledList
        repeat(times = listSize) {
            list.removeFirst()
        }
        return list
    }

    @Benchmark
    fun removeLast(): LinkedList<String> {
        val list = filledList
        repeat(times = listSize) {
            list.removeLast()
        }
        return list
    }

    @Benchmark
    fun removeFirstRemoveLast(): LinkedList<String> {
        val list = filledList
        repeat(times = listSize shr 1) {
            list.removeFirst()
            list.removeLast()
        }
        return list
    }
}
