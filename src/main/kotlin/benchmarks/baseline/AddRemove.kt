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
open class AddRemove {
    @Param(BM_1, BM_3, BM_6, BM_10, BM_15, BM_25, BM_50,
            BM_100, BM_1000, BM_10000, BM_100000, BM_1000000)
    var listSize: Int = 0

    var list = LinkedList<String>()

    @Setup(Level.Trial)
    fun prepare() {
        list = LinkedList(List(listSize, { "some element" }))
    }

    @Benchmark
    fun addFirstRemoveFirst(): LinkedList<String> {
        repeat(times = listSize) {
            list.addFirst("some element")
            list.removeFirst()
        }
        return list
    }

    @Benchmark
    fun addFirstRemoveLast(): LinkedList<String> {
        repeat(times = listSize) {
            list.addFirst("some element")
            list.removeLast()
        }
        return list
    }

    @Benchmark
    fun addLastRemoveFirst(): LinkedList<String> {
        repeat(times = listSize) {
            list.addLast("some element")
            list.removeFirst()
        }
        return list
    }

    @Benchmark
    fun addLastRemoveLast(): LinkedList<String> {
        repeat(times = listSize) {
            list.addLast("some element")
            list.removeLast()
        }
        return list
    }
}