package benchmarks.pcollections.consPStack

import benchmarks.*
import org.openjdk.jmh.annotations.*
import org.openjdk.jmh.infra.Blackhole
import org.pcollections.ConsPStack
import persistentDeque.emptyDeque
import java.util.concurrent.TimeUnit

@OutputTimeUnit(TimeUnit.MICROSECONDS)
@BenchmarkMode(Mode.AverageTime)
@Fork(1)
@Warmup(iterations = 5)
@Measurement(iterations = 5)
@State(Scope.Benchmark)
open class Iterate {
    var pStack = ConsPStack.empty<String>()

    @Param(BENCHMARK_SIZE_XS.toString(),
            BENCHMARK_SIZE_S.toString(),
            BENCHMARK_SIZE_M.toString(),
            BENCHMARK_SIZE_L.toString(),
            BENCHMARK_SIZE_XL.toString())
    var listSize: Int = 0

    @Setup(Level.Trial)
    fun prepare() {
        pStack = ConsPStack.empty<String>()
        repeat(times = listSize) {
            pStack = pStack.plus("some element")
        }
    }

    @Benchmark
    fun firstToLast(bh: Blackhole) {
        val iterator = pStack.listIterator()

        while (iterator.hasNext()) {
            bh.consume(iterator.next())
        }
    }

    @Benchmark
    fun lastToFirst(bh: Blackhole) {
        val iterator = pStack.listIterator(listSize)

        while (iterator.hasPrevious()) {
            bh.consume(iterator.previous())
        }
    }
}
