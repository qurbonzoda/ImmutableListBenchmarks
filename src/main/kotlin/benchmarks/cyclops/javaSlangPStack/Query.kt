package benchmarks.cyclops.javaSlangPStack

import benchmarks.*
import com.aol.cyclops.javaslang.collections.JavaSlangPStack
import org.openjdk.jmh.annotations.*
import org.pcollections.PStack
import java.util.Random
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

    var pStack: PStack<String> = JavaSlangPStack.emptyPStack()
    var element = ""

    @Setup(Level.Trial)
    fun prepare() {
        pStack = JavaSlangPStack.emptyPStack()

        val random = Random()
        repeat(times = listSize) {
            pStack = pStack.plus(random.nextInt().toString())
        }
        listHalfSize = listSize / 2
        element = pStack[listHalfSize]
    }

    @Benchmark
    fun first(): String? {
        return pStack.first()
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
    fun prefix(): PStack<String> {
        return pStack.subList(0, listHalfSize)
    }

    @Benchmark
    fun suffix(): PStack<String> {
        return pStack.subList(listHalfSize, listSize)
    }

    @Benchmark
    fun subList(): PStack<String> {
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
    fun concatenate(): PStack<String> {
        return pStack.plusAll(pStack)
    }
}
