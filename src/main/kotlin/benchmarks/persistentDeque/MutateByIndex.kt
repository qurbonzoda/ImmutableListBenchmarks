package benchmarks.persistentDeque

import benchmarks.*
import org.openjdk.jmh.annotations.*
import persistentDeque.PersistentDeque
import persistentDeque.emptyDeque
import java.util.*
import java.util.concurrent.TimeUnit

@OutputTimeUnit(TimeUnit.MICROSECONDS)
@BenchmarkMode(Mode.AverageTime)
@Fork(1)
@Warmup(iterations = 5)
@Measurement(iterations = 5)
@State(Scope.Thread)
open class MutateByIndex {

    @Param(BENCHMARK_SIZE_XS.toString(),
            BENCHMARK_SIZE_S.toString(),
            BENCHMARK_SIZE_M.toString(),
            BENCHMARK_SIZE_L.toString(),
            BENCHMARK_SIZE_XL.toString())
    var listSize: Int = 0

    var listHalfSize: Int = 0

    var deque  = emptyDeque<String>()

    @Setup(Level.Trial)
    fun prepare() {
        deque = emptyDeque()
        repeat(times = listSize) {
            deque = deque.addFirst("some element")
        }
        listHalfSize = listSize / 2
    }

    @Benchmark
    fun setAtIndex(): PersistentDeque<String> {
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
