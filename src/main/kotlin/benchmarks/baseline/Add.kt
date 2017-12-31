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
open class Add {
    @Param(BENCHMARK_SIZE_XS.toString(),
            BENCHMARK_SIZE_S.toString(),
            BENCHMARK_SIZE_M.toString(),
            BENCHMARK_SIZE_L.toString(),
            BENCHMARK_SIZE_XL.toString())
    var listSize: Int = 0

    var list = LinkedList<String>()

    @Setup(Level.Invocation)
    fun prepare() {
        list.clear()
    }

    @Benchmark
    fun addFirst(): LinkedList<String> {
        repeat(times = listSize) {
            list.addFirst("some element")
        }
        return list
    }

    @Benchmark
    fun addLast(): LinkedList<String> {
        repeat(times = listSize) {
            list.addLast("some element")
        }
        return list
    }
}