package benchmarks.paguro.rrbTree

import benchmarks.*
import org.openjdk.jmh.annotations.*
import org.organicdesign.fp.collections.RrbTree
import java.util.concurrent.TimeUnit

@Fork(1)
@Warmup(iterations = 5)
@Measurement(iterations = 5)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
open class MutateByIndex {
    @Param(BM_1, BM_4, BM_10, BM_15, BM_20, BM_25, BM_35, BM_50, BM_75,
            BM_100, BM_1000, BM_10000, BM_100000, BM_1000000)
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
