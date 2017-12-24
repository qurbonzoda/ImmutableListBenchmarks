package benchmarks.persistentDeque.amortized

import benchmarks.*
import org.openjdk.jmh.annotations.*
import persistentDeque.emptyDeque
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

    var deque = emptyDeque<String>()
//    var element = ""

    @Setup(Level.Trial)
    fun prepare() {
        deque = emptyDeque()

        val random = Random()
        repeat(times = listSize) {
            deque = deque.addFirst(random.nextInt().toString())
        }
        listHalfSize = listSize / 2
//        element = list[listHalfSize]
    }

    @Benchmark
    fun first(): String? {
        return deque.first
    }

    @Benchmark
    fun last(): String? {
        return deque.last
    }

    @Benchmark
    fun size(): Int {
        return deque.size
    }

    @Benchmark
    fun toList(): List<String> {
        return deque.toList()
    }

//    @Benchmark
//    fun prefix(): List<String> {
//        return list.subList(0, listHalfSize)
//    }
//
//    @Benchmark
//    fun suffix(): List<String> {
//        return list.subList(listHalfSize, listSize)
//    }
//
//    @Benchmark
//    fun subList(): List<String> {
//        val quarter = (listHalfSize shr 1)
//        return list.subList(listHalfSize - quarter, listHalfSize + quarter)
//    }

    @Benchmark
    fun getAtIndex(): String {
        return deque.get(listHalfSize)
    }

//    @Benchmark
//    fun indexOfElement(): Int {
//        return list.indexOf(element)
//    }
//
//    @Benchmark
//    fun containsElement(): Boolean {
//        return list.contains(element)
//    }
//
//    @Benchmark
//    fun concatenate(): List<String> {
//        return list + list
//    }
}
