package benchmarks.persistentDeque.remove

import benchmarks.BENCHMARK_SIZE_XS
import org.openjdk.jmh.annotations.*
import persistentDeque.PersistentDeque
import persistentDeque.emptyDeque
import java.util.concurrent.TimeUnit

@Fork(1)
@Warmup(iterations = 5, batchSize = BENCHMARK_SIZE_XS)
@Measurement(iterations = 5, batchSize = BENCHMARK_SIZE_XS)
@BenchmarkMode(Mode.SingleShotTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
open class Remove_XS {
    var deque = emptyDeque<String>()

    @Setup(Level.Iteration)
    fun prepare() {
        repeat(times = BENCHMARK_SIZE_XS) {
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
