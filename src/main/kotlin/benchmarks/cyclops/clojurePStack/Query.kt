package benchmarks.cyclops.clojurePStack

import benchmarks.*
import com.aol.cyclops.clojure.collections.ClojurePStack
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
    @Param(BM_1, BM_3, BM_6, BM_10, BM_15, BM_25, BM_50, BM_100, BM_1000)
    var listSize: Int = 0

    var listHalfSize: Int = 0

    var pStack = ClojurePStack.emptyPStack<String>()
    var element = ""

    @Setup(Level.Trial)
    fun prepare() {
        pStack = ClojurePStack.emptyPStack()

        val random = Random()
        repeat(times = listSize) {
            pStack = pStack.plus(random.nextInt().toString())
        }
        listHalfSize = listSize / 2
        element = pStack[listHalfSize]
    }

    @Benchmark
    fun first(): String? {
        return pStack.head()
    }

    @Benchmark
    fun last(): String? {
        return pStack.last()
    }

    @Benchmark
    fun size(): Int {
        return pStack.size
    }

    @Benchmark
    fun toList(): List<String> {
        return pStack.toList()
    }

    @Benchmark
    fun prefix(): ClojurePStack<String> {
        return pStack.subList(0, listHalfSize)
    }

    @Benchmark
    fun suffix(): ClojurePStack<String> {
        return pStack.subList(listHalfSize, listSize)
    }

    @Benchmark
    fun subList(): ClojurePStack<String> {
        val quarter = (listHalfSize shr 1)
        return pStack.subList(listHalfSize - quarter, listHalfSize + quarter)
    }

    @Benchmark
    fun getAtIndex(): String {
        return pStack.get(listHalfSize)
    }

    @Benchmark
    fun indexOfElement(): Int {
        return pStack.indexOf(element)
    }

    @Benchmark
    fun containsElement(): Boolean {
        return pStack.contains(element)
    }

    @Benchmark
    fun concatenate(): ClojurePStack<String> {
        return pStack.plusAll(pStack)
    }
}
