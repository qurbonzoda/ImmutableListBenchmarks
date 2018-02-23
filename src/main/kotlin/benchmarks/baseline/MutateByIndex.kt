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
open class MutateByIndex {
    @Param(BM_1, BM_3, BM_6, BM_10, BM_15, BM_25, BM_50,
            BM_100, BM_1000, BM_10000, BM_100000, BM_1000000, BM_10000000)
    var listSize: Int = 0

    var listHalfSize: Int = 0

    var list = LinkedList<String>()

    @Setup(Level.Iteration)
    fun prepare() {
        list.clear()
        repeat(times = listSize) {
            list.addFirst("some element")
        }
        listHalfSize = listSize / 2
    }

    @Benchmark
    fun setAtIndex(): String {
        return list.set(listHalfSize, "another element")
    }

    @Benchmark
    fun addAtIndex(): LinkedList<String> {
        list.add(listHalfSize, "some element")
        list.removeFirst()
        return list
    }

    @Benchmark
    fun removeAtIndex(): LinkedList<String> {
        list.removeAt(listHalfSize)
        list.addFirst("another element")
        return list
    }
}
