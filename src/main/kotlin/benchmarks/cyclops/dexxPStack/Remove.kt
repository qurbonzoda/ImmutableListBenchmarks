package benchmarks.cyclops.dexxPStack

import benchmarks.*
import com.aol.cyclops.dexx.collections.DexxPStack
import org.openjdk.jmh.annotations.*
import org.pcollections.PStack
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

    var pStack: PStack<String> = DexxPStack.emptyPStack()

    @Setup(Level.Invocation)
    fun prepare() {
        repeat(times = listSize) {
            pStack = pStack.plus("some element")
        }
    }

    @Benchmark
    fun removeFirst(): PStack<String> {
        repeat(times = listSize) {
            pStack = pStack.minus(0)
        }
        return pStack
    }

    @Benchmark
    fun removeLast(): PStack<String> {
        repeat(times = listSize) {
            pStack = pStack.minus(pStack.size - 1)
        }
        return pStack
    }
}
