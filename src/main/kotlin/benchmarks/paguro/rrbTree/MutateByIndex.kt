package benchmarks.paguro.rrbTree

import benchmarks.*
import org.openjdk.jmh.annotations.*
import org.organicdesign.fp.collections.RrbTree
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

    var rrbTree = RrbTree.empty<String>()

    @Setup(Level.Trial)
    fun prepare() {
        rrbTree = RrbTree.empty()
        repeat(times = listSize) {
            rrbTree = rrbTree.append("some element")
        }
        listHalfSize = listSize / 2
    }

    @Benchmark
    fun setAtIndex(): RrbTree<String> {
        return rrbTree.replace(listHalfSize, "another element")
    }

    @Benchmark
    fun addAtIndex(): RrbTree<String> {
        return rrbTree.insert(listHalfSize, "some element")
    }

    @Benchmark
    fun removeAtIndex(): RrbTree<String> {
        return rrbTree.without(listHalfSize)
    }
}
