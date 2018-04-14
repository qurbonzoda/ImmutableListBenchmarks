package benchmarks.pcollections.treePVector

import benchmarks.*
import org.openjdk.jmh.annotations.*
import org.openjdk.jmh.infra.Blackhole
import org.pcollections.TreePVector
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

    private val emptyPVector = TreePVector.empty<String>()

    @Benchmark
    fun addFirst(): TreePVector<String> {
        var pVector = emptyPVector
        repeat(times = listSize) {
            pVector = pVector.plus(0, "some element")
        }
        return pVector
    }

    @Benchmark
    fun addLast(): TreePVector<String> {
        var pVector = emptyPVector
        repeat(times = listSize) {
            pVector = pVector.plus("some element")
        }
        return pVector
    }

    @Benchmark
    fun addFirstAddLast(): TreePVector<String> {
        var pVector = emptyPVector
        repeat(times = listSize shr 1) {
            pVector = pVector.plus(0, "some element")
            pVector = pVector.plus("some element")
        }
        return pVector
    }

    @Benchmark
    fun addLastAndIterate(bh: Blackhole): TreePVector<String> {
        var pVector = emptyPVector
        repeat(times = listSize) {
            pVector = pVector.plus("some element")
        }

        for (e in pVector) {
            bh.consume(e)
        }
        return pVector
    }
}