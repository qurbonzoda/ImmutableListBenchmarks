package benchmarks.cyclops.clojurePStack

import benchmarks.*
import com.aol.cyclops.clojure.collections.ClojurePStack
import org.openjdk.jmh.annotations.*
import java.util.concurrent.TimeUnit

@Fork(1)
@Warmup(iterations = 5)
@Measurement(iterations = 5)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
open class Remove {
    @Param(BENCHMARK_SIZE_XS.toString(),
            BENCHMARK_SIZE_S.toString(),
            BENCHMARK_SIZE_M.toString(),
            BENCHMARK_SIZE_L.toString(),
            BENCHMARK_SIZE_XL.toString())
    var listSize: Int = 0

    var pStack = ClojurePStack.emptyPStack<String>()

    @Setup(Level.Invocation)
    fun prepare() {
        repeat(times = listSize) {
            pStack = pStack.plus("some element")
        }
    }

    @Benchmark
    fun removeFirst(): ClojurePStack<String> {
        repeat(times = listSize) {
            pStack = pStack.minus(0)
        }
        return pStack
    }

    @Benchmark
    fun removeLast(): ClojurePStack<String> {
        repeat(times = listSize) {
            pStack = pStack.minus(pStack.size - 1)
        }
        return pStack
    }
}
