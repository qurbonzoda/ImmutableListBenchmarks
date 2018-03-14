package benchmarks.cyclops.javaSlangPStack

import benchmarks.*
import com.aol.cyclops.javaslang.collections.JavaSlangPStack
import org.openjdk.jmh.annotations.*
import org.pcollections.PStack
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

    var pStack: PStack<String> = JavaSlangPStack.emptyPStack()

    @Setup(Level.Trial)
    fun prepare() {
        pStack = JavaSlangPStack.emptyPStack()
        repeat(times = listSize) {
            pStack = pStack.plus("some element")
        }
    }

    @Benchmark
    fun addFirstRemoveLast(): PStack<String> {
        repeat(times = listSize shr 1) {
            pStack = pStack.plus(0, "some element")
            pStack = pStack.minus(pStack.size - 1)
        }
        return pStack
    }

    @Benchmark
    fun addLastRemoveFirst(): PStack<String> {
        repeat(times = listSize shr 1) {
            pStack = pStack.plus("some element")
            pStack = pStack.minus(0)
        }
        return pStack
    }
}