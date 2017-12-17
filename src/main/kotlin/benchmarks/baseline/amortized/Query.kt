package benchmarks.baseline.amortized

import benchmarks.*
import org.openjdk.jmh.annotations.*
import java.util.*
import java.util.concurrent.TimeUnit

@OutputTimeUnit(TimeUnit.MICROSECONDS)
@BenchmarkMode(Mode.AverageTime)
@Fork(1)
@Warmup(iterations = 5)
@Measurement(iterations = 5)
@State(Scope.Benchmark)
open class Query {

    @Param(BENCHMARK_SIZE_XS.toString(),
            BENCHMARK_SIZE_S.toString(),
            BENCHMARK_SIZE_M.toString(),
            BENCHMARK_SIZE_L.toString(),
            BENCHMARK_SIZE_XL.toString())
    var listSize: Int = 0

    var listHalfSize: Int = 0

    var list = LinkedList<String>()
    var element = ""

    @Setup()
    fun prepare() {
        assert(list.isEmpty())

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
