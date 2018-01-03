package benchmarks.cyclops.clojurePVector

import benchmarks.*
import com.aol.cyclops.clojure.collections.ClojurePVector
import com.aol.cyclops.scala.collections.ScalaPVector
import org.openjdk.jmh.annotations.*
import org.pcollections.PVector
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

    var pVector: PVector<String> = ClojurePVector.emptyPVector()

    @Setup(Level.Invocation)
    fun prepare() {
        repeat(times = listSize) {
            pVector = pVector.plus("some element")
        }
    }

    @Benchmark
    fun removeFirst(): PVector<String> {
        repeat(times = listSize) {
            pVector = pVector.minus(0)
        }
        return pVector
    }

    @Benchmark
    fun removeLast(): PVector<String> {
        repeat(times = listSize) {
            pVector = pVector.minus(pVector.size - 1)
        }
        return pVector
    }
}
