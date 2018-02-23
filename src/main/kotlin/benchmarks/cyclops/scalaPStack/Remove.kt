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
open class Remove {
    @Param(BM_1, BM_3, BM_6, BM_10, BM_15, BM_25, BM_50, BM_100, BM_1000)
    var listSize: Int = 0

    var pStack = ScalaPStack.emptyPStack<String>()

    @Setup(Level.Invocation)
    fun prepare() {
        repeat(times = listSize) {
            pStack = pStack.plus("some element")
        }
    }

    @Benchmark
    fun removeFirst(): ScalaPStack<String> {
        repeat(times = listSize) {
            pStack = pStack.minus(0)
        }
        return pStack
    }

    @Benchmark
    fun removeLast(): ScalaPStack<String> {
        repeat(times = listSize) {
            pStack = pStack.minus(pStack.size - 1)
        }
        return pStack
    }

    @Benchmark
    fun removeFirstRemoveLast(): ScalaPStack<String> {
        repeat(times = listSize shr 1) {
            pStack = pStack.minus(0)
            pStack = pStack.minus(pStack.size - 1)
        }
        return pStack
    }
}
