package benchmarks.pcollections.consPStack

import benchmarks.*
import org.openjdk.jmh.annotations.*
import org.pcollections.ConsPStack
import java.util.concurrent.TimeUnit

@OutputTimeUnit(TimeUnit.MICROSECONDS)
@BenchmarkMode(Mode.AverageTime)
@Fork(1)
@Warmup(iterations = 5)
@Measurement(iterations = 5)
@State(Scope.Thread)
open class MutateByIndex {

    @Param(BENCHMARK_SIZE_XS.toString(),
            BENCHMARK_SIZE_S.toString(),
            BENCHMARK_SIZE_M.toString(),
            BENCHMARK_SIZE_L.toString(),
            BENCHMARK_SIZE_XL.toString())
    var listSize: Int = 0

    var listHalfSize: Int = 0

    var pStack  = ConsPStack.empty<String>()

    @Setup(Level.Trial)
    fun prepare() {
        pStack = ConsPStack.empty()
        repeat(times = listSize) {
            pStack = pStack.plus("some element")
        }
        listHalfSize = listSize / 2
    }

    @Benchmark
    fun setAtIndex(): ConsPStack<String> {
        return pStack.with(listHalfSize, "another element")
    }

    @Benchmark
    fun addAtIndex(): ConsPStack<String> {
        return pStack.plus(listHalfSize, "some element")
    }

    @Benchmark
    fun removeAtIndex(): ConsPStack<String> {
        return pStack.minus(listHalfSize)
    }
}
