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
open class Remove {
    @Param(BM_1, BM_4, BM_10, BM_15, BM_20, BM_25, BM_35, BM_50, BM_75,
            BM_100, BM_1000, BM_10000, BM_100000, BM_1000000)
    var listSize: Int = 0

    private var filledRrbTree = RrbTree.empty<String>()

    @Setup(Level.Trial)
    fun prepare() {
        filledRrbTree = RrbTree.empty()
        repeat(times = listSize) {
            filledRrbTree = filledRrbTree.append("some element")
        }
    }

    @Benchmark
    fun removeFirst(): RrbTree<String> {
        var rrbTree = filledRrbTree
        repeat(times = listSize) {
            rrbTree = rrbTree.without(0)
        }
        return rrbTree
    }

    @Benchmark
    fun removeLast(): RrbTree<String> {
        var rrbTree = filledRrbTree
        repeat(times = listSize) {
            rrbTree = rrbTree.without(rrbTree.size - 1)
        }
        return rrbTree
    }

    @Benchmark
    fun removeFirstRemoveLast(): RrbTree<String> {
        var rrbTree = filledRrbTree
        repeat(times = listSize shr 1) {
            rrbTree = rrbTree.without(0)
            rrbTree = rrbTree.without(rrbTree.size - 1)
        }
        return rrbTree
    }
}
