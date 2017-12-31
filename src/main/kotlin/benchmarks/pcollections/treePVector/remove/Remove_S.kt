package benchmarks.pcollections.treePVector.remove

import benchmarks.BENCHMARK_SIZE_S
import org.openjdk.jmh.annotations.*
import org.pcollections.TreePVector
import java.util.concurrent.TimeUnit

@Fork(1)
@Warmup(iterations = 5, batchSize = BENCHMARK_SIZE_S)
@Measurement(iterations = 5, batchSize = BENCHMARK_SIZE_S)
@BenchmarkMode(Mode.SingleShotTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
open class Remove_S {
    var pVector = TreePVector.empty<String>()

    @Setup(Level.Iteration)
    fun prepare() {
        repeat(times = BENCHMARK_SIZE_S) {
            pVector = pVector.plus("some element")
        }
    }

    @Benchmark
    fun removeFirst(): TreePVector<String> {
        pVector = pVector.minus(0)
        return pVector
    }

    @Benchmark
    fun removeLast(): TreePVector<String> {
        pVector = pVector.minus(pVector.size - 1)
        return pVector
    }
}
