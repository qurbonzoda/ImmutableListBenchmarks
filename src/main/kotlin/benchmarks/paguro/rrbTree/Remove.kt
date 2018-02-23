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
    @Param(BM_1, BM_3, BM_6, BM_10, BM_15, BM_25, BM_50,
            BM_100, BM_1000, BM_10000, BM_100000, BM_1000000, BM_10000000)
    var listSize: Int = 0

    var rrbTree = RrbTree.empty<String>()

    @Setup(Level.Invocation)
    fun prepare() {
        repeat(times = listSize) {
            rrbTree = rrbTree.append("some element")
        }
    }

    @Benchmark
    fun removeFirst(): RrbTree<String> {
        repeat(times = listSize) {
            rrbTree = rrbTree.without(0)
        }
        return rrbTree
    }

    @Benchmark
    fun removeLast(): RrbTree<String> {
        repeat(times = listSize) {
            rrbTree = rrbTree.without(rrbTree.size - 1)
        }
        return rrbTree
    }

    @Benchmark
    fun removeFirstRemoveLast(): RrbTree<String> {
        repeat(times = listSize shr 1) {
            rrbTree = rrbTree.without(0)
            rrbTree = rrbTree.without(rrbTree.size - 1)
        }
        return rrbTree
    }
}
