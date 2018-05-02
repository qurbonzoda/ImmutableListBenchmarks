package benchmarks.persistentDeque

import benchmarks.*
import immutableDeque.ImmutableDeque
import immutableDeque.initial.persistentDeque.emptyDeque
import org.openjdk.jmh.annotations.*
import org.openjdk.jmh.infra.Blackhole
import java.util.concurrent.TimeUnit

@Fork(1)
@Warmup(iterations = 5)
@Measurement(iterations = 5)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
open class Iterate {
    @Param(BM_1, BM_4, BM_10, BM_15, BM_20, BM_25, BM_35, BM_50, BM_75,
            BM_100, BM_1000, BM_10000, BM_100000, BM_1000000)
    var listSize: Int = 0

    @Param(CHILD_COUNT_2, CHILD_COUNT_3, CHILD_COUNT_4, CHILD_COUNT_8, CHILD_COUNT_16, CHILD_COUNT_32, CHILD_COUNT_64)
    var childCount: Int = 0

    @Param(BUFFER_SIZE_8, BUFFER_SIZE_16, BUFFER_SIZE_32, BUFFER_SIZE_64, BUFFER_SIZE_128, BUFFER_SIZE_256,
            BUFFER_SIZE_512, BUFFER_SIZE_1024)
    var bufferSize: Int = 0

    private var deque: ImmutableDeque<String> = emptyDeque()

    @Setup(Level.Trial)
    fun prepare() {
        deque = emptyDeque(childCount, bufferSize)
        repeat(times = listSize) {
            deque = deque.addFirst("some element")
        }
    }

    @Benchmark
    fun firstToLast(bh: Blackhole) {
        val iterator = deque.listIterator()

        while (iterator.hasNext()) {
            bh.consume(iterator.next())
        }
    }

    @Benchmark
    fun lastToFirst(bh: Blackhole) {
        val iterator = deque.listIterator(listSize)

        while (iterator.hasPrevious()) {
            bh.consume(iterator.previous())
        }
    }
}
