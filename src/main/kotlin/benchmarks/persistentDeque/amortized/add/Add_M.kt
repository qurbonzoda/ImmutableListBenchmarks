package benchmarks.persistentDeque.amortized.add

import benchmarks.BENCHMARK_SIZE_M
import org.openjdk.jmh.annotations.*
import persistentDeque.PersistentDeque
import persistentDeque.emptyDeque
import java.util.concurrent.TimeUnit

@Fork(1)
@Warmup(iterations = 5, batchSize = BENCHMARK_SIZE_M)
@Measurement(iterations = 5, batchSize = BENCHMARK_SIZE_M)
@BenchmarkMode(Mode.SingleShotTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
open class Add_M {
    var deque = emptyDeque<String>()

    @Setup(Level.Iteration)
    fun prepare() {
        deque = emptyDeque()
    }

    @Benchmark
    fun addFirst(): PersistentDeque<String> {
        deque = deque.addFirst("some element")
        return deque
    }

    @Benchmark
    fun addLast(): PersistentDeque<String> {
        deque = deque.addLast("some element")
        return deque
    }
}