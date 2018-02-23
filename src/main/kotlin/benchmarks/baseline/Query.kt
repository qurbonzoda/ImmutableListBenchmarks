package benchmarks.baseline

import benchmarks.*
import org.openjdk.jmh.annotations.*
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

    var list = LinkedList<String>()
    var element = ""

    @Setup(Level.Trial)
    fun prepare() {
        list.clear()

        val random = Random()
        repeat(times = listSize) {
            list.addFirst(random.nextInt().toString())
        }
        listHalfSize = listSize / 2
        element = list[listHalfSize]
    }

    @Benchmark
    fun first(): String {
        return list.first
    }

    @Benchmark
    fun last(): String {
        return list.last
    }

    @Benchmark
    fun size(): Int {
        return list.size
    }

    @Benchmark
    fun toList(): List<String> {
        return list.toList()
    }

    @Benchmark
    fun prefix(): List<String> {
        return list.subList(0, listHalfSize)
    }

    @Benchmark
    fun suffix(): List<String> {
        return list.subList(listHalfSize, listSize)
    }

    @Benchmark
    fun subList(): List<String> {
        val quarter = (listHalfSize shr 1)
        return list.subList(listHalfSize - quarter, listHalfSize + quarter)
    }

    @Benchmark
    fun getAtIndex(): String {
        return list.get(listHalfSize)
    }

    @Benchmark
    fun indexOfElement(): Int {
        return list.indexOf(element)
    }

    @Benchmark
    fun containsElement(): Boolean {
        return list.contains(element)
    }

    @Benchmark
    fun concatenate(): List<String> {
        return list + list
    }
}
