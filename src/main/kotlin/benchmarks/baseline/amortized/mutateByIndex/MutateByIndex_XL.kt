package benchmarks.baseline.amortized.mutateByIndex

import benchmarks.BENCHMARK_SIZE_XL
import org.openjdk.jmh.annotations.*
import java.util.*
import java.util.concurrent.TimeUnit

@OutputTimeUnit(TimeUnit.MICROSECONDS)
@BenchmarkMode(Mode.AverageTime)
@Fork(1)
@Warmup(iterations = 5)
@Measurement(iterations = 5)
@State(Scope.Thread)
open class MutateByIndex_XL {
    private val listSize = BENCHMARK_SIZE_XL
    private val listHalfSize = listSize / 2

    var list = LinkedList<String>()
    var size = 0

    @Setup(Level.Iteration)
    fun prepare() {
        repeat(times = listSize) {
            list.addFirst("some element")
        }
        size = listSize
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
