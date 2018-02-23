package benchmarks.pcollections.treePVector

import benchmarks.*
import org.openjdk.jmh.annotations.*
import org.pcollections.TreePVector
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

    var pVector = TreePVector.empty<String>()

    @Setup(Level.Trial)
    fun prepare() {
        pVector = TreePVector.empty()
        repeat(times = listSize) {
            pVector = pVector.plus("some element")
        }
    }

    @Benchmark
    fun addFirstRemoveFirst(): TreePVector<String> {
        repeat(times = listSize) {
            pVector = pVector.plus(0, "some element")
            pVector = pVector.minus(0)
        }
        return pVector
    }

    @Benchmark
    fun addFirstRemoveLast(): TreePVector<String> {
        repeat(times = listSize) {
            pVector = pVector.plus(0, "some element")
            pVector = pVector.minus(pVector.size - 1)
        }
        return pVector
    }

    @Benchmark
    fun addLastRemoveFirst(): TreePVector<String> {
        repeat(times = listSize) {
            pVector = pVector.plus("some element")
            pVector = pVector.minus(0)
        }
        return pVector
    }

    @Benchmark
    fun addLastRemoveLast(): TreePVector<String> {
        repeat(times = listSize) {
            pVector = pVector.plus("some element")
            pVector = pVector.minus(pVector.size - 1)
        }
        return pVector
    }
}