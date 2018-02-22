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
    @Param(BENCHMARK_SIZE_XS.toString(),
            BENCHMARK_SIZE_S.toString(),
            BENCHMARK_SIZE_M.toString(),
            BENCHMARK_SIZE_L.toString(),
            BENCHMARK_SIZE_XL.toString())
    var listSize: Int = 0

    var list = LinkedList<String>()

    @Setup(Level.Invocation)
    fun prepare() {
        repeat(times = listSize) {
            list.addFirst("some element")
        }
    }

    @Benchmark
    fun removeFirst(): List<String> {
        repeat(times = listSize) {
            list.removeFirst()
        }
        return list
    }

    @Benchmark
    fun removeLast(): LinkedList<String> {
        repeat(times = listSize) {
            list.removeLast()
        }
        return list
    }

    @Benchmark
    fun removeFirstRemoveLast(): LinkedList<String> {
        repeat(times = listSize shr 1) {
            list.removeFirst()
            list.removeLast()
        }
        return list
    }
}
