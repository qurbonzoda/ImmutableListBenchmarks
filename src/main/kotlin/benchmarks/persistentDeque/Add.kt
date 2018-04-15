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
@State(Scope.Thread)
open class Add {
    @Param(BM_1, BM_4, BM_10, BM_15, BM_20, BM_25, BM_35, BM_50, BM_75,
            BM_100, BM_1000, BM_10000, BM_100000, BM_1000000)
    var listSize: Int = 0

    @Param(INITIAL_IMPL,
            STACK_7_IMPL, STACK_8_IMPL, STACK_9_IMPL, STACK_12_IMPL, STACK_13_IMPL, STACK_19_IMPL, STACK_19B_IMPL,
            STACK_25_IMPL, STACK_25O_IMPL, STACK_31_IMPL, STACK_32_IMPL, STACK_48_IMPL, STACK_61_IMPL, STACK_64_IMPL,
            ARRAY_7_IMPL, ARRAY_7S_IMPL, ARRAY_8_IMPL, ARRAY_8S_IMPL, ARRAY_9_IMPL, ARRAY_9S_IMPL, ARRAY_12_IMPL,
            ARRAY_12S_IMPL, ARRAY_13_IMPL, ARRAY_13S_IMPL, ARRAY_19_IMPL, ARRAY_19S_IMPL, ARRAY_19B_IMPL,
            ARRAY_19SB_IMPL, ARRAY_25_IMPL, ARRAY_25S_IMPL, ARRAY_31_IMPL, ARRAY_31S_IMPL, ARRAY_32_IMPL,
            ARRAY_32S_IMPL, ARRAY_48_IMPL, ARRAY_48S_IMPL, ARRAY_61_IMPL, ARRAY_61S_IMPL, ARRAY_64_IMPL, ARRAY_64S_IMPL)
    var impl: String = ""

    private var emptyDeque: ImmutableDeque<String> = emptyDeque()

    @Setup(Level.Trial)
    fun prepare() {
        emptyDeque = EMPTY_DEQUE.getValue(impl)
    }

    @Benchmark
    fun addFirst(): ImmutableDeque<String> {
        var deque = emptyDeque
        repeat(times = listSize) {
            deque = deque.addFirst("some element")
        }
        return deque
    }

    @Benchmark
    fun addLast(): ImmutableDeque<String> {
        var deque = emptyDeque
        repeat(times = listSize) {
            deque = deque.addLast("some element")
        }
        return deque
    }

    @Benchmark
    fun addFirstAddLast(): ImmutableDeque<String> {
        var deque = emptyDeque
        repeat(times = listSize shr 1) {
            deque = deque.addFirst("some element")
            deque = deque.addLast("some element")
        }
        return deque
    }

    @Benchmark
    fun addLastAndIterate(bh: Blackhole): ImmutableDeque<String> {
        var deque = emptyDeque
        repeat(times = listSize) {
            deque = deque.addLast("some element")
        }

        for (e in deque) {
            bh.consume(e)
        }
        return deque
    }
}