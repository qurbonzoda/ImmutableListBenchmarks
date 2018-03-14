package benchmarks.paguro.rrbTree

import benchmarks.*
import org.openjdk.jmh.annotations.*
import org.openjdk.jmh.infra.Blackhole
import org.organicdesign.fp.collections.RrbTree
import java.util.concurrent.TimeUnit


@Fork(1)
@Warmup(iterations = 5)
@Measurement(iterations = 5)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
open class Add {
    @Param(BM_1, BM_3, BM_6, BM_10, BM_15, BM_25, BM_50,
            BM_100, BM_1000, BM_10000, BM_100000, BM_1000000)
    var listSize: Int = 0

    var rrbTree = RrbTree.empty<String>()

    @Setup(Level.Invocation)
    fun prepare() {
        rrbTree = RrbTree.empty<String>()
    }

    @Benchmark
    fun addFirst(): RrbTree<String> {
        repeat(times = listSize) {
            rrbTree = rrbTree.insert(0, "some element")
        }
        return rrbTree
    }

    @Benchmark
    fun addLast(): RrbTree<String> {
        repeat(times = listSize) {
            rrbTree = rrbTree.append("some element")
        }
        return rrbTree
    }

    @Benchmark
    fun addFirstAddLast(): RrbTree<String> {
        repeat(times = listSize shr 1) {
            rrbTree = rrbTree.insert(0, "some element")
            rrbTree = rrbTree.append("some element")
        }
        return rrbTree
    }

    @Benchmark
    fun addLastAndIterate(bh: Blackhole): RrbTree<String> {
        repeat(times = listSize) {
            rrbTree = rrbTree.append("some element")
        }
        val iterator = rrbTree.listIterator()

        while (iterator.hasNext()) {
            bh.consume(iterator.next())
        }
        return rrbTree
    }
}