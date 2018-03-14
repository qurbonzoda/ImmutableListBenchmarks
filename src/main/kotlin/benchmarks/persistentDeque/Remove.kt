package benchmarks.persistentDeque

import benchmarks.*
import deque.ImmutableDeque
import deque.emptyDeque
import org.openjdk.jmh.annotations.*
import java.util.concurrent.TimeUnit

@Fork(1)
@Warmup(iterations = 5)
@Measurement(iterations = 5)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
open class Remove {
    @Param(BM_1, BM_3, BM_6, BM_10, BM_15, BM_25, BM_50,
            BM_100, BM_1000, BM_10000, BM_100000, BM_1000000)
    var listSize: Int = 0

    private var filledDeque = emptyDeque<String>()

    @Setup(Level.Trial)
    fun prepare() {
        filledDeque = emptyDeque()
        repeat(times = listSize) {
            filledDeque = filledDeque.addFirst("some element")
        }
    }

    @Benchmark
    fun removeFirst(): ImmutableDeque<String> {
        var deque = filledDeque
        repeat(times = listSize) {
            deque = deque.removeFirst()
        }
        return deque
    }

    @Benchmark
    fun removeLast(): ImmutableDeque<String> {
        var deque = filledDeque
        repeat(times = listSize) {
            deque = deque.removeLast()
        }
        return deque
    }

    @Benchmark
    fun removeFirstRemoveLast(): ImmutableDeque<String> {
        var deque = filledDeque
        repeat(times = listSize shr 1) {
            deque = deque.removeFirst()
            deque = deque.removeLast()
        }
        return deque
    }
}
