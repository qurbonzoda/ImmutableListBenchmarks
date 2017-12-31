package benchmarks.pcollections.treePVector

import benchmarks.*
import org.openjdk.jmh.annotations.*
import org.pcollections.PVector
import org.pcollections.TreePVector
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

    var pVector = TreePVector.empty<String>()

    @Setup(Level.Trial)
    fun prepare() {
        pVector = TreePVector.empty()
        repeat(times = listSize) {
            pVector = pVector.plus("some element")
        }
        listHalfSize = listSize / 2
    }

    @Benchmark
    fun setAtIndex(): PVector<String> {
        return pVector.with(listHalfSize, "another element")
    }

    @Benchmark
    fun addAtIndex(): TreePVector<String> {
        return pVector.plus(listHalfSize, "some element")
    }

    @Benchmark
    fun removeAtIndex(): TreePVector<String> {
        return pVector.minus(listHalfSize)
    }
}
