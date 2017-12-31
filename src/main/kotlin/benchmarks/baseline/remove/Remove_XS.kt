package benchmarks.baseline.remove

import benchmarks.BENCHMARK_SIZE_XS
import org.openjdk.jmh.annotations.*
import java.util.*
import java.util.concurrent.TimeUnit

@Fork(1)
@Warmup(iterations = 5, batchSize = BENCHMARK_SIZE_XS)
@Measurement(iterations = 5, batchSize = BENCHMARK_SIZE_XS)
@BenchmarkMode(Mode.SingleShotTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
open class Remove_XS {
    var list = LinkedList<String>()

    @Setup(Level.Iteration)
    fun prepare() {
        repeat(times = BENCHMARK_SIZE_XS) {
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
