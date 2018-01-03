package benchmarks.pcollections.treePVector

import benchmarks.*
import org.openjdk.jmh.annotations.*
import org.pcollections.TreePVector
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

    var pVector = TreePVector.empty<String>()

    @Setup(Level.Invocation)
    fun prepare() {
        pVector = TreePVector.empty<String>()
    }

    @Benchmark
    fun addFirst(): TreePVector<String> {
        repeat(times = listSize) {
            pVector = pVector.plus(0, "some element")
        }
        return pVector
    }

    @Benchmark
    fun addLast(): TreePVector<String> {
        repeat(times = listSize) {
            pVector = pVector.plus("some element")
        }
        return pVector
    }
}