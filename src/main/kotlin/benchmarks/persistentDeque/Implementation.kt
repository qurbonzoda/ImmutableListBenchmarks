package benchmarks.persistentDeque

import immutableDeque.ImmutableDeque


const val CHILD_COUNT_2 = "2"
const val CHILD_COUNT_3 = "3"
const val CHILD_COUNT_4 = "4"
const val CHILD_COUNT_8 = "8"
const val CHILD_COUNT_16 = "16"
const val CHILD_COUNT_32 = "32"
const val CHILD_COUNT_64 = "64"

const val BUFFER_SIZE_8 = "8"
const val BUFFER_SIZE_16 = "16"
const val BUFFER_SIZE_32 = "32"
const val BUFFER_SIZE_64 = "64"
const val BUFFER_SIZE_128 = "128"
const val BUFFER_SIZE_256 = "256"
const val BUFFER_SIZE_512 = "512"
const val BUFFER_SIZE_1024 = "1024"

fun <T> emptyDeque(childCount: Int, bufferSize: Int): ImmutableDeque<T> {
    return when(Pair(childCount, bufferSize)) {
        Pair(2, 8)      -> immutableDeque.childCount2.bufferSize8.emptyDeque.emptyDeque()
        Pair(2, 16)     -> immutableDeque.childCount2.bufferSize16.emptyDeque.emptyDeque()
        Pair(2, 32)     -> immutableDeque.childCount2.bufferSize32.emptyDeque.emptyDeque()
        Pair(2, 64)     -> immutableDeque.childCount2.bufferSize64.emptyDeque.emptyDeque()
        Pair(2, 128)    -> immutableDeque.childCount2.bufferSize128.emptyDeque.emptyDeque()
        Pair(2, 256)    -> immutableDeque.childCount2.bufferSize256.emptyDeque.emptyDeque()
        Pair(2, 512)    -> immutableDeque.childCount2.bufferSize512.emptyDeque.emptyDeque()
        Pair(2, 1024)   -> immutableDeque.childCount2.bufferSize1024.emptyDeque.emptyDeque()

        Pair(3, 16)     -> immutableDeque.childCount3.bufferSize16.emptyDeque.emptyDeque()
        Pair(3, 32)     -> immutableDeque.childCount3.bufferSize32.emptyDeque.emptyDeque()
        Pair(3, 64)     -> immutableDeque.childCount3.bufferSize64.emptyDeque.emptyDeque()
        Pair(3, 128)    -> immutableDeque.childCount3.bufferSize128.emptyDeque.emptyDeque()
        Pair(3, 256)    -> immutableDeque.childCount3.bufferSize256.emptyDeque.emptyDeque()
        Pair(3, 512)    -> immutableDeque.childCount3.bufferSize512.emptyDeque.emptyDeque()
        Pair(3, 1024)   -> immutableDeque.childCount3.bufferSize1024.emptyDeque.emptyDeque()

        Pair(4, 16)     -> immutableDeque.childCount4.bufferSize16.emptyDeque.emptyDeque()
        Pair(4, 32)     -> immutableDeque.childCount4.bufferSize32.emptyDeque.emptyDeque()
        Pair(4, 64)     -> immutableDeque.childCount4.bufferSize64.emptyDeque.emptyDeque()
        Pair(4, 128)    -> immutableDeque.childCount4.bufferSize128.emptyDeque.emptyDeque()
        Pair(4, 256)    -> immutableDeque.childCount4.bufferSize256.emptyDeque.emptyDeque()
        Pair(4, 512)    -> immutableDeque.childCount4.bufferSize512.emptyDeque.emptyDeque()
        Pair(4, 1024)   -> immutableDeque.childCount4.bufferSize1024.emptyDeque.emptyDeque()

        Pair(8, 32)     -> immutableDeque.childCount8.bufferSize32.emptyDeque.emptyDeque()
        Pair(8, 64)     -> immutableDeque.childCount8.bufferSize64.emptyDeque.emptyDeque()
        Pair(8, 128)    -> immutableDeque.childCount8.bufferSize128.emptyDeque.emptyDeque()
        Pair(8, 256)    -> immutableDeque.childCount8.bufferSize256.emptyDeque.emptyDeque()
        Pair(8, 512)    -> immutableDeque.childCount8.bufferSize512.emptyDeque.emptyDeque()
        Pair(8, 1024)   -> immutableDeque.childCount8.bufferSize1024.emptyDeque.emptyDeque()

        Pair(16, 64)    -> immutableDeque.childCount16.bufferSize64.emptyDeque.emptyDeque()
        Pair(16, 128)   -> immutableDeque.childCount16.bufferSize128.emptyDeque.emptyDeque()
        Pair(16, 256)   -> immutableDeque.childCount16.bufferSize256.emptyDeque.emptyDeque()
        Pair(16, 512)   -> immutableDeque.childCount16.bufferSize512.emptyDeque.emptyDeque()
        Pair(16, 1024)  -> immutableDeque.childCount16.bufferSize1024.emptyDeque.emptyDeque()

        Pair(32, 128)   -> immutableDeque.childCount32.bufferSize128.emptyDeque.emptyDeque()
        Pair(32, 256)   -> immutableDeque.childCount32.bufferSize256.emptyDeque.emptyDeque()
        Pair(32, 512)   -> immutableDeque.childCount32.bufferSize512.emptyDeque.emptyDeque()
        Pair(32, 1024)  -> immutableDeque.childCount32.bufferSize1024.emptyDeque.emptyDeque()

        Pair(64, 256)   -> immutableDeque.childCount64.bufferSize256.emptyDeque.emptyDeque()
        Pair(64, 512)   -> immutableDeque.childCount64.bufferSize512.emptyDeque.emptyDeque()
        Pair(64, 1024)  -> immutableDeque.childCount64.bufferSize1024.emptyDeque.emptyDeque()
        else            -> throw IllegalArgumentException()
    }
}