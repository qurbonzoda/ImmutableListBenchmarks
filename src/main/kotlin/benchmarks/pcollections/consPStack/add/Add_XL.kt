package benchmarks.pcollections.consPStack.add

import benchmarks.BENCHMARK_SIZE_XL
import org.openjdk.jmh.annotations.*
import org.pcollections.ConsPStack
import java.util.concurrent.TimeUnit

@Fork(1)
@Warmup(iterations = 5, batchSize = BENCHMARK_SIZE_XL)
@Measurement(iterations = 5, batchSize = BENCHMARK_SIZE_XL)
@BenchmarkMode(Mode.SingleShotTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
open class Add_XL {
    var pStack = ConsPStack.empty<String>()

    @Setup(Level.Iteration)
    fun prepare() {
        pStack = ConsPStack.empty<String>()
    }

    @Benchmark
    fun addFirst(): ConsPStack<String> {
        pStack = pStack.plus("some element")
        return pStack
    }

    @Benchmark
    fun addLast(): ConsPStack<String> {
        pStack = pStack.plus(pStack.size, "some element")
        return pStack
    }
}