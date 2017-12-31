package benchmarks.pcollections.consPStack

import benchmarks.*
import org.openjdk.jmh.annotations.*
import org.pcollections.ConsPStack
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

    var pStack = ConsPStack.empty<String>()

    @Setup(Level.Invocation)
    fun prepare() {
        pStack = ConsPStack.empty<String>()
    }

    @Benchmark
    fun addFirst(): ConsPStack<String> {
        repeat(times = listSize) {
            pStack = pStack.plus("some element")
        }
        return pStack
    }

    @Benchmark
    fun addLast(): ConsPStack<String> {
        repeat(times = listSize) {
            pStack = pStack.plus(pStack.size, "some element")
        }
        return pStack
    }
}