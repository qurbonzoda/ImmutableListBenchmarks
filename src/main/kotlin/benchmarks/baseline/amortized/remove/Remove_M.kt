package benchmarks.baseline.amortized.remove

import benchmarks.BENCHMARK_SIZE_M
import org.openjdk.jmh.annotations.*
import java.util.*
import java.util.concurrent.TimeUnit

@Fork(1)
@Warmup(iterations = 5, batchSize = BENCHMARK_SIZE_M)
@Measurement(iterations = 5, batchSize = BENCHMARK_SIZE_M)
@BenchmarkMode(Mode.SingleShotTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
open class Remove_M {
    var list = LinkedList<String>()

    @Setup(Level.Iteration)
    fun prepare() {
        repeat(times = BENCHMARK_SIZE_M) {
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
