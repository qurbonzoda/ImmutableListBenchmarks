package benchmarks.cyclops.dexxPVector

import benchmarks.*
import com.aol.cyclops.dexx.collections.DexxPVector
import org.openjdk.jmh.annotations.*
import java.util.concurrent.TimeUnit

@Fork(1)
@Warmup(iterations = 5)
@Measurement(iterations = 5)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
open class Remove {
    @Param(BM_1, BM_4, BM_10, BM_15, BM_20, BM_25, BM_35, BM_50, BM_75,
            BM_100, BM_1000, BM_10000, BM_100000, BM_1000000)
    var listSize: Int = 0

    private var filledPVector = DexxPVector.emptyPVector<String>()

    @Setup(Level.Trial)
    fun prepare() {
        filledPVector = DexxPVector.emptyPVector()
        repeat(times = listSize) {
            filledPVector = filledPVector.plus("some element")
        }
    }

    @Benchmark
    fun removeFirst(): DexxPVector<String> {
        var pVector = filledPVector
        repeat(times = listSize) {
            pVector = pVector.minus(0)
        }
        return pVector
    }

    @Benchmark
    fun removeLast(): DexxPVector<String> {
        var pVector = filledPVector
        repeat(times = listSize) {
            pVector = pVector.minus(pVector.size - 1)
        }
        return pVector
    }

    @Benchmark
    fun removeFirstRemoveLast(): DexxPVector<String> {
        var pVector = filledPVector
        repeat(times = listSize shr 1) {
            pVector = pVector.minus(0)
            pVector = pVector.minus(pVector.size - 1)
        }
        return pVector
    }
}
