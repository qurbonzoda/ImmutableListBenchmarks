package benchmarks.pcollections.treePVector

import benchmarks.*
import org.openjdk.jmh.annotations.*
import org.pcollections.PVector
import org.pcollections.TreePVector
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

    var pVector = TreePVector.empty<String>()
    var element = ""

    @Setup(Level.Trial)
    fun prepare() {
        pVector = TreePVector.empty<String>()

        val random = Random()
        repeat(times = listSize) {
            pVector = pVector.plus(random.nextInt().toString())
        }
        listHalfSize = listSize / 2
        element = pVector[listHalfSize]
    }

    @Benchmark
    fun first(): String? {
        return pVector.first()
    }

    @Benchmark
    fun last(): String? {
        return pVector.last()
    }

    @Benchmark
    fun size(): Int {
        return pVector.size
    }

    @Benchmark
    fun toList(): List<String> {
        return pVector.toList()
    }

    @Benchmark
    fun prefix(): TreePVector<String> {
        return pVector.subList(0, listHalfSize)
    }

    @Benchmark
    fun suffix(): TreePVector<String> {
        return pVector.subList(listHalfSize, listSize)
    }

    @Benchmark
    fun subList(): TreePVector<String> {
        val quarter = (listHalfSize shr 1)
        return pVector.subList(listHalfSize - quarter, listHalfSize + quarter)
    }

    @Benchmark
    fun getAtIndex(): String {
        return pVector.get(listHalfSize)
    }

    @Benchmark
    fun indexOfElement(): Int {
        return pVector.indexOf(element)
    }

    @Benchmark
    fun containsElement(): Boolean {
        return pVector.contains(element)
    }

    @Benchmark
    fun concatenate(): TreePVector<String> {
        return pVector.plusAll(pVector)
    }
}
