package benchmarks.baseline

import benchmarks.*
import org.openjdk.jmh.annotations.*
import org.openjdk.jmh.infra.Blackhole
import java.util.*
import java.util.concurrent.TimeUnit

// java -jar target/benchmarks.jar persistentDeque.Add persistentDeque.Remove -wi 10 -i 10 -prof gc -rf csv -rff results/stack-based-buffers/$(date +%Y.%m.%d-%H:%M:%S)-persistentDeque-add-remove.csv

@Fork(1)
@Warmup(iterations = 5)
@Measurement(iterations = 5)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
open class Add {
    @Param(BM_1, BM_4, BM_10, BM_15, BM_20, BM_25, BM_35, BM_50, BM_75,
            BM_100, BM_1000, BM_10000, BM_100000, BM_1000000)
    var listSize: Int = 0

    private var list = LinkedList<String>()

    @Setup(Level.Invocation)
    fun prepare() {
        list = LinkedList()
    }

    @Benchmark
    fun addFirst(): LinkedList<String> {
        repeat(times = listSize) {
            list.addFirst("some element")
        }
        return list
    }

    @Benchmark
    fun addLast(): LinkedList<String> {
        repeat(times = listSize) {
            list.addLast("some element")
        }
        return list
    }

    @Benchmark
    fun addFirstAddLast(): LinkedList<String> {
        repeat(times = listSize shr 1) {
            list.addFirst("some element")
            list.addLast("some element")
        }
        return list
    }

    @Benchmark
    fun addLastAndIterate(bh: Blackhole): List<String> {
        repeat(times = listSize) {
            list.addLast("some element")
        }

        for (e in list) {
            bh.consume(e)
        }
        return list
    }
}