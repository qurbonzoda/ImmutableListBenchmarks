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
open class Remove {
    @Param(BM_1, BM_3, BM_6, BM_10, BM_15, BM_25, BM_50,
            BM_100, BM_1000, BM_10000, BM_100000, BM_1000000)
    var listSize: Int = 0

    var pVector = TreePVector.empty<String>()

    private var preparedPVector = TreePVector.empty<String>()

    @Setup(Level.Invocation)
    fun prepare() {
        if (preparedPVector.size != listSize) {
            preparedPVector = TreePVector.empty()
            repeat(times = listSize) {
                preparedPVector = preparedPVector.plus("some element")
            }
        }
        pVector = preparedPVector
    }

    @Benchmark
    fun removeFirst(): TreePVector<String> {
        repeat(times = listSize) {
            pVector = pVector.minus(0)
        }
        return pVector
    }

    @Benchmark
    fun removeLast(): TreePVector<String> {
        repeat(times = listSize) {
            pVector = pVector.minus(pVector.size - 1)
        }
        return pVector
    }

    @Benchmark
    fun removeFirstRemoveLast(): TreePVector<String> {
        repeat(times = listSize shr 1) {
            pVector = pVector.minus(0)
            pVector = pVector.minus(pVector.size - 1)
        }
        return pVector
    }
}
