package benchmarks.baseline

import benchmarks.*
import org.openjdk.jmh.annotations.*
import java.util.*
import java.util.concurrent.TimeUnit

@OutputTimeUnit(TimeUnit.MICROSECONDS)
@BenchmarkMode(Mode.AverageTime)
@Fork(1)
@Warmup(iterations = 5)
@Measurement(iterations = 5)
@State(Scope.Thread)
open class MutateByIndex {

    @Param(BENCHMARK_SIZE_XS.toString(),
            BENCHMARK_SIZE_S.toString(),
            BENCHMARK_SIZE_M.toString(),
            BENCHMARK_SIZE_L.toString(),
            BENCHMARK_SIZE_XL.toString())
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
