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
open class Add {
    @Param(BM_1, BM_3, BM_6, BM_10, BM_15, BM_25, BM_50, BM_100, BM_1000)
    var listSize: Int = 0

    var list = listOf<String>()
    private val someElement = listOf("some element")

    @Setup(Level.Invocation)
    fun prepare() {
        list = emptyList()
    }

    @Benchmark
    fun addFirst(): List<String> {
        repeat(times = listSize) {
            list = someElement + list
        }
        return list
    }

    @Benchmark
    fun addLast(): List<String> {
        repeat(times = listSize) {
            list += someElement
        }
        return list
    }

    @Benchmark
    fun addFirstAddLast(): List<String> {
        repeat(times = listSize shr 1) {
            list = someElement + list
            list += someElement
        }
        return list
    }
}