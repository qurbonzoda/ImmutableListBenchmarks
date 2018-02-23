package benchmarks.paguro.rrbTree

import benchmarks.*
import org.openjdk.jmh.annotations.*
import org.organicdesign.fp.collections.RrbTree
import java.util.*
import java.util.concurrent.TimeUnit

@Fork(1)
@Warmup(iterations = 5)
@Measurement(iterations = 5)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
open class Query {
    @Param(BM_1, BM_3, BM_6, BM_10, BM_15, BM_25, BM_50,
            BM_100, BM_1000, BM_10000, BM_100000, BM_1000000, BM_10000000)
    var listSize: Int = 0

    var listHalfSize: Int = 0

    var rrbTree = RrbTree.empty<String>()
    var element = ""

    @Setup(Level.Trial)
    fun prepare() {
        rrbTree = RrbTree.empty<String>()

        val random = Random()
        repeat(times = listSize) {
            rrbTree = rrbTree.append(random.nextInt().toString())
        }
        listHalfSize = listSize / 2
        element = rrbTree[listHalfSize]
    }

    @Benchmark
    fun first(): String? {
        return rrbTree.first()
    }

    @Benchmark
    fun last(): String? {
        return rrbTree.last()
    }

    @Benchmark
    fun size(): Int {
        return rrbTree.size
    }

    @Benchmark
    fun toList(): List<String> {
        return rrbTree.toList()
    }

    @Benchmark
    fun prefix(): RrbTree<String> {
        return rrbTree.split(listHalfSize)._1()
    }

    @Benchmark
    fun suffix(): RrbTree<String> {
        return rrbTree.split(listSize)._2()
    }

    @Benchmark
    fun subList(): RrbTree<String> {
        val quarter = (listHalfSize shr 1)
        val suffix = rrbTree.split(listHalfSize - quarter)._2()
        return suffix.split(listHalfSize)._1()
    }

    @Benchmark
    fun getAtIndex(): String {
        return rrbTree.get(listHalfSize)
    }

    @Benchmark
    fun indexOfElement(): Int {
        return rrbTree.indexOf(element)
    }

    @Benchmark
    fun containsElement(): Boolean {
        return rrbTree.contains(element)
    }

    @Benchmark
    fun concatenate(): RrbTree<String> {
        return rrbTree.join(rrbTree)
    }
}
