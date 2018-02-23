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
open class Add {
    @Param(BM_1, BM_3, BM_6, BM_10, BM_15, BM_25, BM_50, BM_100, BM_1000)
    var listSize: Int = 0

    var pStack = ScalaPStack.emptyPStack<String>()

    @Setup(Level.Invocation)
    fun prepare() {
        pStack = ScalaPStack.emptyPStack<String>()
    }

    @Benchmark
    fun addFirst(): ScalaPStack<String> {
        repeat(times = listSize) {
            pStack = pStack.plus(0, "some element")
        }
        return pStack
    }

    @Benchmark
    fun addLast(): ScalaPStack<String> {
        repeat(times = listSize) {
            pStack = pStack.plus("some element")
        }
        return pStack
    }

    @Benchmark
    fun addFirstAddLast(): ScalaPStack<String> {
        repeat(times = listSize shr 1) {
            pStack = pStack.plus(0, "some element")
            pStack = pStack.plus("some element")
        }
        return pStack
    }
}