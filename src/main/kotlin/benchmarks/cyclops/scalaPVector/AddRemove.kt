package benchmarks.cyclops.scalaPVector

import benchmarks.*
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
open class AddRemove {
    @Param(BM_1, BM_4, BM_10, BM_15, BM_20, BM_25, BM_35, BM_50, BM_75,
            BM_100, BM_1000, BM_10000, BM_100000, BM_1000000)
    var listSize: Int = 0

    var pVector = ScalaPVector.emptyPVector<String>()

    @Setup(Level.Trial)
    fun prepare() {
        pVector = ScalaPVector.emptyPVector()
        repeat(times = listSize) {
            pVector = pVector.plus("some element")
        }
    }

    @Benchmark
    fun addLastRemoveFirst(): PVector<String> {
        repeat(times = listSize shr 1) {
            pVector = pVector.plus("some element")
            pVector = pVector.minus(0)
        }
        return pVector
    }
}