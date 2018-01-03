package benchmarks.cyclops.clojurePVector

import benchmarks.*
import com.aol.cyclops.clojure.collections.ClojurePVector
import org.openjdk.jmh.annotations.*
import org.pcollections.PVector
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
