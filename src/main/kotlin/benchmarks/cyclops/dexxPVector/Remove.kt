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
    @Param(BM_1, BM_3, BM_6, BM_10, BM_15, BM_25, BM_50,
            BM_100, BM_1000, BM_10000, BM_100000, BM_1000000, BM_10000000)
    var listSize: Int = 0

    var pVector = DexxPVector.emptyPVector<String>()

    @Setup(Level.Invocation)
    fun prepare() {
        repeat(times = listSize) {
            pVector = pVector.plus("some element")
        }
    }

    @Benchmark
    fun removeFirst(): DexxPVector<String> {
        repeat(times = listSize) {
            pVector = pVector.minus(0)
        }
        return pVector
    }

    @Benchmark
    fun removeLast(): DexxPVector<String> {
        repeat(times = listSize) {
            pVector = pVector.minus(pVector.size - 1)
        }
        return pVector
    }
}
