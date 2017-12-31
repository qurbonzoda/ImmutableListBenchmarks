package benchmarks.pcollections.treePVector.add

import benchmarks.BENCHMARK_SIZE_XL
import org.openjdk.jmh.annotations.*
import org.pcollections.ConsPStack
import org.pcollections.TreePVector
import java.util.concurrent.TimeUnit

@Fork(1)
@Warmup(iterations = 5, batchSize = BENCHMARK_SIZE_XL)
@Measurement(iterations = 5, batchSize = BENCHMARK_SIZE_XL)
@BenchmarkMode(Mode.SingleShotTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
open class Add_XL {
    var pVector = TreePVector.empty<String>()

    @Setup(Level.Iteration)
    fun prepare() {
        pVector = TreePVector.empty<String>()
    }

    @Benchmark
    fun addFirst(): TreePVector<String> {
        pVector = pVector.plus("some element")
        return pVector
    }

    @Benchmark
    fun addLast(): TreePVector<String> {
        pVector = pVector.plus(pVector.size, "some element")
        return pVector
    }
}