package benchmarks.persistentDeque

import benchmarks.*
import org.openjdk.jmh.annotations.*
import persistentDeque.PersistentDeque
import persistentDeque.emptyDeque
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

    var deque = emptyDeque<String>()

    @Setup(Level.Invocation)
    fun prepare() {
        deque = emptyDeque()
    }

    @Benchmark
    fun addFirst(): PersistentDeque<String> {
        repeat(times = listSize) {
            deque = deque.addFirst("some element")
        }
        return deque
    }

    @Benchmark
    fun addLast(): PersistentDeque<String> {
        repeat(times = listSize) {
            deque = deque.addLast("some element")
        }
        return deque
    }

    @Benchmark
    fun addFirstAddLast(): PersistentDeque<String> {
        repeat(times = listSize shr 1) {
            deque = deque.addFirst("some element")
            deque = deque.addLast("some element")
        }
        return deque
    }
}