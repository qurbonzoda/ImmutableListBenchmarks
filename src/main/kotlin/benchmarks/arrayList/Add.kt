package benchmarks.arrayList

import benchmarks.*
import org.openjdk.jmh.annotations.*
import org.openjdk.jmh.infra.Blackhole
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

    private val emptyList = emptyList<String>()

    @Benchmark
    fun addFirst(): List<String> {
        var list = emptyList
        repeat(times = listSize) {
            list = listOf("some element") + list
        }
        return list
    }

    @Benchmark
    fun addLast(): List<String> {
        var list = emptyList
        repeat(times = listSize) {
            list += listOf("some element")
        }
        return list
    }

    @Benchmark
    fun addFirstAddLast(): List<String> {
        var list = emptyList
        repeat(times = listSize shr 1) {
            list = listOf("some element") + list
            list += listOf("some element")
        }
        return list
    }

    @Benchmark
    fun addLastAndIterate(bh: Blackhole): List<String> {
        var list = emptyList
        repeat(times = listSize) {
            list += listOf("some element")
        }

        for (e in list) {
            bh.consume(e)
        }
        return list
    }
}