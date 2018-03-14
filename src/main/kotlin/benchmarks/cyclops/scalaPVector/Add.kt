package benchmarks.cyclops.scalaPVector

import benchmarks.*
import com.aol.cyclops.scala.collections.ScalaPVector
import org.openjdk.jmh.annotations.*
import java.util.concurrent.TimeUnit

@Fork(1)
@Warmup(iterations = 5)
@Measurement(iterations = 5)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
open class Add {
    @Param(BM_1, BM_3, BM_6, BM_10, BM_15, BM_25, BM_50,
            BM_100, BM_1000, BM_10000, BM_100000, BM_1000000)
    var listSize: Int = 0

    var pVector = ScalaPVector.emptyPVector<String>()

    @Setup(Level.Invocation)
    fun prepare() {
        pVector = ScalaPVector.emptyPVector()
    }

    @Benchmark
    fun addFirst(): ScalaPVector<String> {
        repeat(times = listSize) {
            pVector = pVector.plus(0, "some element")
        }
        return pVector
    }

    @Benchmark
    fun addLast(): ScalaPVector<String> {
        repeat(times = listSize) {
            pVector = pVector.plus("some element")
        }
        return pVector
    }

    @Benchmark
    fun addFirstAddLast(): ScalaPVector<String> {
        repeat(times = listSize shr 1) {
            pVector = pVector.plus(0, "some element")
            pVector = pVector.plus("some element")
        }
        return pVector
    }
}