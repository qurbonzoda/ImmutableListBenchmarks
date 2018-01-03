package benchmarks.cyclops.clojurePVector

import benchmarks.*
import com.aol.cyclops.clojure.collections.ClojurePVector
import org.openjdk.jmh.annotations.*
import org.pcollections.PVector
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

    var pVector: PVector<String> = ClojurePVector.emptyPVector()

    @Setup(Level.Invocation)
    fun prepare() {
        pVector = ClojurePVector.emptyPVector()
    }

    @Benchmark
    fun addFirst(): PVector<String> {
        repeat(times = listSize) {
            pVector = pVector.plus(0, "some element")
        }
        return pVector
    }

    @Benchmark
    fun addLast(): PVector<String> {
        repeat(times = listSize) {
            pVector = pVector.plus("some element")
        }
        return pVector
    }
}