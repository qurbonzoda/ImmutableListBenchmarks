package benchmarks.persistentDeque.amortized.remove

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
open class Remove_M {
    var deque = emptyDeque<String>()

    @Setup(Level.Iteration)
    fun prepare() {
        repeat(times = BENCHMARK_SIZE_M) {
            deque = deque.addFirst("some element")
        }
    }

    @Benchmark
    fun removeFirst(): PersistentDeque<String> {
        deque = deque.removeFirst()
        return deque
    }

    @Benchmark
    fun removeLast(): PersistentDeque<String> {
        deque = deque.removeLast()
        return deque
    }
}
