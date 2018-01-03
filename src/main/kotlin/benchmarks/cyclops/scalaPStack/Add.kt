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
    @Param(BENCHMARK_SIZE_XS.toString(),
            BENCHMARK_SIZE_S.toString(),
            BENCHMARK_SIZE_M.toString(),
            BENCHMARK_SIZE_L.toString(),
            BENCHMARK_SIZE_XL.toString())
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
}