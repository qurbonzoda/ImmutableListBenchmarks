package benchmarks.persistentDeque

import benchmarks.*
import deque.ImmutableDeque
import deque.emptyDeque
import org.openjdk.jmh.annotations.*
import org.openjdk.jmh.infra.Blackhole
import java.util.concurrent.TimeUnit

@Fork(1)
@Warmup(iterations = 5)
@Measurement(iterations = 5)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
open class Add {
    @Param(BM_1, BM_3, BM_6, BM_10, BM_15, BM_25, BM_50,
            BM_100, BM_1000, BM_10000, BM_100000, BM_1000000)
    var listSize: Int = 0

    var deque = emptyDeque<String>()

    @Setup(Level.Invocation)
    fun prepare() {
        deque = emptyDeque()
    }

    @Benchmark
    fun addFirst(): ImmutableDeque<String> {
        repeat(times = listSize) {
            deque = deque.addFirst("some element")
        }
        return deque
    }

    @Benchmark
    fun addLast(): ImmutableDeque<String> {
        repeat(times = listSize) {
            deque = deque.addLast("some element")
        }
        return deque
    }

    @Benchmark
    fun addFirstAddLast(): ImmutableDeque<String> {
        repeat(times = listSize shr 1) {
            deque = deque.addFirst("some element")
            deque = deque.addLast("some element")
        }
        return deque
    }

    @Benchmark
    fun addLastAndIterate(bh: Blackhole): ImmutableDeque<String> {
        repeat(times = listSize) {
            deque = deque.addLast("some element")
        }
        val iterator = deque.listIterator()

        while (iterator.hasNext()) {
            bh.consume(iterator.next())
        }
        return deque
    }
}