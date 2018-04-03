package benchmarks.persistentDeque

import benchmarks.*
import immutableDeque.ImmutableDeque
import immutableDeque.initial.persistentDeque.emptyDeque
import org.openjdk.jmh.annotations.*
import java.util.concurrent.TimeUnit

@Fork(1)
@Warmup(iterations = 5)
@Measurement(iterations = 5)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
open class MutateByIndex {
    @Param(BM_1, BM_3, BM_6, BM_10, BM_15, BM_25, BM_50,
            BM_100, BM_1000, BM_10000, BM_100000, BM_1000000)
    var listSize: Int = 0

    var listHalfSize: Int = 0

    @Param(INITIAL_IMPL,
            STACK_7_IMPL, STACK_8_IMPL, STACK_9_IMPL, STACK_12_IMPL, STACK_13_IMPL,
            STACK_19_IMPL, STACK_19B_IMPL, STACK_25_IMPL, STACK_25O_IMPL, STACK_31_IMPL, STACK_32_IMPL,
            ARRAY_7_IMPL, ARRAY_7S_IMPL, ARRAY_8_IMPL, ARRAY_8S_IMPL, ARRAY_9_IMPL, ARRAY_9S_IMPL,
            ARRAY_12_IMPL, ARRAY_12S_IMPL, ARRAY_13_IMPL, ARRAY_13S_IMPL, ARRAY_19_IMPL, ARRAY_19S_IMPL,
            ARRAY_19B_IMPL, ARRAY_19SB_IMPL, ARRAY_25_IMPL, ARRAY_25S_IMPL, ARRAY_31_IMPL, ARRAY_31S_IMPL,
            ARRAY_32_IMPL, ARRAY_32S_IMPL)
    var impl: String = ""

    var deque: ImmutableDeque<String> = emptyDeque()

    @Setup(Level.Trial)
    fun prepare() {
        deque = EMPTY_DEQUE.getValue(impl)
        repeat(times = listSize) {
            deque = deque.addFirst("some element")
        }
        listHalfSize = listSize / 2
    }

    @Benchmark
    fun setAtIndex(): ImmutableDeque<String> {
        return deque.set(listHalfSize, "another element")
    }

//    @Benchmark
//    fun addAtIndex(): LinkedList<String> {
//        list.add(listHalfSize, "some element")
//        list.removeFirst()
//        return list
//    }
//
//    @Benchmark
//    fun removeAtIndex(): LinkedList<String> {
//        list.removeAt(listHalfSize)
//        list.addFirst("another element")
//        return list
//    }
}
