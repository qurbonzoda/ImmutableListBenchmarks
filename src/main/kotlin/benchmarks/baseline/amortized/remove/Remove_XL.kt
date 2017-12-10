package benchmarks.baseline.amortized.remove

import benchmarks.BENCHMARK_SIZE_XL
import org.openjdk.jmh.annotations.*
import java.util.*
import java.util.concurrent.TimeUnit

@Fork(1)
@Warmup(iterations = 5, batchSize = BENCHMARK_SIZE_XL)
@Measurement(iterations = 5, batchSize = BENCHMARK_SIZE_XL)
@BenchmarkMode(Mode.SingleShotTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
open class Remove_XL {
    var list = LinkedList<String>()

    @Setup(Level.Iteration)
    fun prepare() {
        repeat(times = BENCHMARK_SIZE_XL) {
            list.addFirst("some element")
        }
    }

    @Benchmark
    fun removeFirst(): List<String> {
        list.removeFirst()
        return list
    }

    @Benchmark
    fun removeLast(): LinkedList<String> {
        list.removeLast()
        return list
    }
}
