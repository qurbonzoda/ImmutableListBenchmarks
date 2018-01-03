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
    @Param(BENCHMARK_SIZE_XS.toString(),
            BENCHMARK_SIZE_S.toString(),
            BENCHMARK_SIZE_M.toString(),
            BENCHMARK_SIZE_L.toString(),
            BENCHMARK_SIZE_XL.toString())
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
}
