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
open class Remove {
    @Param(BM_1, BM_3, BM_6, BM_10, BM_15, BM_25, BM_50,
            BM_100, BM_1000, BM_10000, BM_100000, BM_1000000, BM_10000000)
    var listSize: Int = 0

    var deque = emptyDeque<String>()

    private var preparedDeque = emptyDeque<String>()

    @Setup(Level.Invocation)
    fun prepare() {
        if (preparedDeque.size != listSize) {
            preparedDeque = emptyDeque()
            repeat(times = listSize) {
                preparedDeque = preparedDeque.addFirst("some element")
            }
        }
        deque = preparedDeque
    }

    @Benchmark
    fun removeFirst(): PersistentDeque<String> {
        repeat(times = listSize) {
            deque = deque.removeFirst()
        }
        return deque
    }

    @Benchmark
    fun removeLast(): PersistentDeque<String> {
        repeat(times = listSize) {
            deque = deque.removeLast()
        }
        return deque
    }

    @Benchmark
    fun removeFirstRemoveLast(): PersistentDeque<String> {
        repeat(times = listSize shr 1) {
            deque = deque.removeFirst()
            deque = deque.removeLast()
        }
        return deque
    }
}
