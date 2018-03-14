package benchmarks.cyclops.javaSlangPVector

import benchmarks.*
import com.aol.cyclops.javaslang.collections.JavaSlangPVector
import org.openjdk.jmh.annotations.*
import org.pcollections.PVector
import java.util.concurrent.TimeUnit

@Fork(1)
@Warmup(iterations = 5)
@Measurement(iterations = 5)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
open class Remove {
    @Param(BM_1, BM_3, BM_6, BM_10, BM_15, BM_25, BM_50, BM_100, BM_1000)
    var listSize: Int = 0

    private var filledPVector: PVector<String> = JavaSlangPVector.emptyPVector()

    @Setup(Level.Trial)
    fun prepare() {
        filledPVector = JavaSlangPVector.emptyPVector()
        repeat(times = listSize) {
            filledPVector = filledPVector.plus("some element")
        }
    }

    @Benchmark
    fun removeFirst(): PVector<String> {
        var pVector = filledPVector
        repeat(times = listSize) {
            pVector = pVector.minus(0)
        }
        return pVector
    }

    @Benchmark
    fun removeLast(): PVector<String> {
        var pVector = filledPVector
        repeat(times = listSize) {
            pVector = pVector.minus(pVector.size - 1)
        }
        return pVector
    }

    @Benchmark
    fun removeFirstRemoveLast(): PVector<String> {
        var pVector = filledPVector
        repeat(times = listSize shr 1) {
            pVector = pVector.minus(0)
            pVector = pVector.minus(pVector.size - 1)
        }
        return pVector
    }
}
