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
open class AddRemove {
    @Param(BM_1, BM_3, BM_6, BM_10, BM_15, BM_25, BM_50,
            BM_100, BM_1000, BM_10000, BM_100000, BM_1000000, BM_10000000)
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
    fun addFirstRemoveFirst(): PersistentDeque<String> {
        repeat(times = listSize) {
            deque = deque.addFirst("some element")
            deque = deque.removeFirst()
        }
        return deque
    }

    @Benchmark
    fun addFirstRemoveLast(): PersistentDeque<String> {
        repeat(times = listSize) {
            deque = deque.addFirst("some element")
            deque = deque.removeLast()
        }
        return deque
    }

    @Benchmark
    fun addLastRemoveFirst(): PersistentDeque<String> {
        repeat(times = listSize) {
            deque = deque.addLast("some element")
            deque = deque.removeFirst()
        }
        return deque
    }

    @Benchmark
    fun addLastRemoveLast(): PersistentDeque<String> {
        repeat(times = listSize) {
            deque = deque.addLast("some element")
            deque = deque.removeLast()
        }
        return deque
    }
}