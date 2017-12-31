package benchmarks.pcollections.consPStack.remove

import benchmarks.BENCHMARK_SIZE_M
import org.openjdk.jmh.annotations.*
import org.pcollections.ConsPStack
import java.util.concurrent.TimeUnit

@Fork(1)
@Warmup(iterations = 5, batchSize = BENCHMARK_SIZE_M)
@Measurement(iterations = 5, batchSize = BENCHMARK_SIZE_M)
@BenchmarkMode(Mode.SingleShotTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
open class Remove_M {
    var pStack = ConsPStack.empty<String>()

    @Setup(Level.Iteration)
    fun prepare() {
        repeat(times = BENCHMARK_SIZE_M) {
            pStack = pStack.plus("some element")
        }
    }

    @Benchmark
    fun removeFirst(): ConsPStack<String> {
        pStack = pStack.minus(0)
        return pStack
    }

    @Benchmark
    fun removeLast(): ConsPStack<String> {
        pStack = pStack.minus(pStack.size - 1)
        return pStack
    }
}
