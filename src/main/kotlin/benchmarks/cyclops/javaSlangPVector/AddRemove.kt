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
open class AddRemove {
    @Param(BM_1, BM_3, BM_6, BM_10, BM_15, BM_25, BM_50, BM_100, BM_1000)
    var listSize: Int = 0

    var pVector: PVector<String> = JavaSlangPVector.emptyPVector()

    @Setup(Level.Trial)
    fun prepare() {
        pVector = JavaSlangPVector.emptyPVector()
        repeat(times = listSize) {
            pVector = pVector.plus("some element")
        }
    }

    @Benchmark
    fun addFirstRemoveLast(): PVector<String> {
        repeat(times = listSize shr 1) {
            pVector = pVector.plus(0, "some element")
            pVector = pVector.minus(pVector.size - 1)
        }
        return pVector
    }

    @Benchmark
    fun addLastRemoveFirst(): PVector<String> {
        repeat(times = listSize shr 1) {
            pVector = pVector.plus("some element")
            pVector = pVector.minus(0)
        }
        return pVector
    }
}