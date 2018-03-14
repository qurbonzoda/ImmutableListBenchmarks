package benchmarks.cyclops.clojurePVector

import benchmarks.*
import com.aol.cyclops.clojure.collections.ClojurePVector
import org.openjdk.jmh.annotations.*
import org.pcollections.PVector
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

    var pVector = ClojurePVector.emptyPVector<String>()

    @Setup(Level.Trial)
    fun prepare() {
        pVector = ClojurePVector.emptyPVector()
        repeat(times = listSize) {
            pVector = pVector.plus("some element")
        }
        listHalfSize = listSize / 2
    }

    @Benchmark
    fun setAtIndex(): ClojurePVector<String> {
        return pVector.with(listHalfSize, "another element")
    }

    @Benchmark
    fun addAtIndex(): PVector<String> {
        return pVector.plus(listHalfSize, "some element")
    }

    @Benchmark
    fun removeAtIndex(): PVector<String> {
        return pVector.minus(listHalfSize)
    }
}
