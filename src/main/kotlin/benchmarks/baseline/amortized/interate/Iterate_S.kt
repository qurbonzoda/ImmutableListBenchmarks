package benchmarks.baseline.amortized.interate

import benchmarks.BENCHMARK_SIZE_S
import org.openjdk.jmh.annotations.*
import org.openjdk.jmh.infra.Blackhole
import java.util.*
import java.util.concurrent.TimeUnit

@OutputTimeUnit(TimeUnit.MICROSECONDS)
@BenchmarkMode(Mode.AverageTime)
@Fork(1)
@Warmup(iterations = 5)
@Measurement(iterations = 5)
@State(Scope.Benchmark)
open class Iterate_S {
    var list = LinkedList<String>()

    @Setup()
    fun prepare() {
        repeat(times = BENCHMARK_SIZE_S) {
            list.addFirst("some element")
        }
    }

    @Benchmark
    fun firstToLast(bh: Blackhole) {
        val iterator = list.listIterator()

        while (iterator.hasNext()) {
            bh.consume(iterator.next())
        }
    }

    @Benchmark
    fun lastToFirst(bh: Blackhole) {
        val iterator = list.descendingIterator()

        while (iterator.hasNext()) {
            bh.consume(iterator.next())
        }
    }
}
