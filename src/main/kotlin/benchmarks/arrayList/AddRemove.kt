package benchmarks.arrayList

import benchmarks.*
import org.openjdk.jmh.annotations.*
import java.util.concurrent.TimeUnit

@Fork(1)
@Warmup(iterations = 5)
@Measurement(iterations = 5)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
open class AddRemove {
    @Param(BM_1, BM_4, BM_10, BM_15, BM_20, BM_25, BM_35, BM_50, BM_75, BM_100, BM_1000)
    var listSize: Int = 0

    var list = listOf<String>()
    private val someElement = listOf("some element")

    @Setup(Level.Trial)
    fun prepare() {
        list = List(listSize, { "some element" })
    }

    @Benchmark
    fun addLastRemoveFirst(): List<String> {
        repeat(times = listSize shr 1) {
            list += someElement
            list.drop(1)
        }
        return list
    }
}