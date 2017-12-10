package benchmarks.baseline.amortized.add

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
open class Add_XL {
    var list = LinkedList<String>()

    @Setup(Level.Iteration)
    fun prepare() {
        list.clear()
    }

    @Benchmark
    fun addFirst(): LinkedList<String> {
        list.addFirst("some element")
        return list
    }

    @Benchmark
    fun addLast(): LinkedList<String> {
        list.addLast("some element")
        return list
    }
}