package benchmarks.cyclops.clojurePVector

import benchmarks.*
import com.aol.cyclops.clojure.collections.ClojurePVector
import org.openjdk.jmh.annotations.*
import org.openjdk.jmh.infra.Blackhole
import java.util.concurrent.TimeUnit

@OutputTimeUnit(TimeUnit.MICROSECONDS)
@BenchmarkMode(Mode.AverageTime)
@Fork(1)
@Warmup(iterations = 5)
@Measurement(iterations = 5)
@State(Scope.Benchmark)
open class Iterate {

    @Param(BENCHMARK_SIZE_XS.toString(),
            BENCHMARK_SIZE_S.toString(),
            BENCHMARK_SIZE_M.toString(),
            BENCHMARK_SIZE_L.toString(),
            BENCHMARK_SIZE_XL.toString())
    var listSize: Int = 0

    var pVector = ClojurePVector.emptyPVector<String>()

    @Setup(Level.Trial)
    fun prepare() {
        pVector = ClojurePVector.emptyPVector()
        repeat(times = listSize) {
            pVector = pVector.plus("some element")
        }
    }

    @Benchmark
    fun firstToLast(bh: Blackhole) {
        val iterator = pVector.listIterator()

        while (iterator.hasNext()) {
            bh.consume(iterator.next())
        }
    }

    @Benchmark
    fun lastToFirst(bh: Blackhole) {
        val iterator = pVector.listIterator(listSize)

        while (iterator.hasPrevious()) {
            bh.consume(iterator.previous())
        }
    }
}
