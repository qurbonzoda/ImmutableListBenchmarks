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
open class AddRemove {
    @Param(BM_1, BM_3, BM_6, BM_10, BM_15, BM_25, BM_50,
            BM_100, BM_1000, BM_10000, BM_100000, BM_1000000)
    var listSize: Int = 0

    var deque = emptyDeque<String>()

    @Setup(Level.Trial)
    fun prepare() {
        deque = emptyDeque()
        repeat(times = listSize) {
            deque = deque.addFirst("some element")
        }
    }

    @Benchmark
    fun addFirstRemoveLast(): ImmutableDeque<String> {
        repeat(times = listSize shr 1) {
            deque = deque.addFirst("some element")
            deque = deque.removeLast()
        }
        return deque
    }

    @Benchmark
    fun addLastRemoveFirst(): ImmutableDeque<String> {
        repeat(times = listSize shr 1) {
            deque = deque.addLast("some element")
            deque = deque.removeFirst()
        }
        return deque
    }
}