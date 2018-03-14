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
open class AddRemove {
    @Param(BM_1, BM_3, BM_6, BM_10, BM_15, BM_25, BM_50, BM_100, BM_1000)
    var listSize: Int = 0

    var pStack = ClojurePStack.emptyPStack<String>()

    @Setup(Level.Trial)
    fun prepare() {
        pStack = ClojurePStack.emptyPStack()
        repeat(times = listSize) {
            pStack = pStack.plus("some element")
        }
    }

    @Benchmark
    fun addFirstRemoveLast(): ClojurePStack<String> {
        repeat(times = listSize shr 1) {
            pStack = pStack.plus(0, "some element")
            pStack = pStack.minus(pStack.size - 1)
        }
        return pStack
    }

    @Benchmark
    fun addLastRemoveFirst(): ClojurePStack<String> {
        repeat(times = listSize shr 1) {
            pStack = pStack.plus("some element")
            pStack = pStack.minus(0)
        }
        return pStack
    }
}