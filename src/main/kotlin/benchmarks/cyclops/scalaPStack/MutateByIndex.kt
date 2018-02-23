package benchmarks.cyclops.scalaPStack

import benchmarks.*
import com.aol.cyclops.scala.collections.ScalaPStack
import org.openjdk.jmh.annotations.*
import java.util.concurrent.TimeUnit

@Fork(1)
@Warmup(iterations = 5)
@Measurement(iterations = 5)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
open class MutateByIndex {
    @Param(BM_1, BM_3, BM_6, BM_10, BM_15, BM_25, BM_50, BM_100, BM_1000)
    var listSize: Int = 0

    var listHalfSize: Int = 0

    var pStack = ScalaPStack.emptyPStack<String>()

    @Setup(Level.Trial)
    fun prepare() {
        pStack = ScalaPStack.emptyPStack()
        repeat(times = listSize) {
            pStack = pStack.plus("some element")
        }
        listHalfSize = listSize / 2
    }

    @Benchmark
    fun setAtIndex(): ScalaPStack<String> {
        return pStack.with(listHalfSize, "another element")
    }

    @Benchmark
    fun addAtIndex(): ScalaPStack<String> {
        return pStack.plus(listHalfSize, "some element")
    }

    @Benchmark
    fun removeAtIndex(): ScalaPStack<String> {
        return pStack.minus(listHalfSize)
    }
}
