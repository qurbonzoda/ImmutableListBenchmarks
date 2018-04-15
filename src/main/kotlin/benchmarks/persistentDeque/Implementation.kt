package benchmarks.persistentDeque

import immutableDeque.ImmutableDeque

const val INITIAL_IMPL = "INITIAL_IMPL"

const val STACK_7_IMPL = "STACK_7_IMPL"
const val STACK_8_IMPL = "STACK_8_IMPL"
const val STACK_9_IMPL = "STACK_9_IMPL"
const val STACK_12_IMPL = "STACK_12_IMPL"
const val STACK_13_IMPL = "STACK_13_IMPL"
const val STACK_19_IMPL = "STACK_19_IMPL"
const val STACK_19B_IMPL = "STACK_19B_IMPL"
const val STACK_25_IMPL = "STACK_25_IMPL"
const val STACK_25O_IMPL = "STACK_25O_IMPL"
const val STACK_31_IMPL = "STACK_31_IMPL"
const val STACK_32_IMPL = "STACK_32_IMPL"
const val STACK_48_IMPL = "STACK_48_IMPL"
const val STACK_61_IMPL = "STACK_61_IMPL"
const val STACK_64_IMPL = "STACK_64_IMPL"

const val ARRAY_7_IMPL = "ARRAY_7_IMPL"
const val ARRAY_7S_IMPL = "ARRAY_7S_IMPL"
const val ARRAY_8_IMPL = "ARRAY_8_IMPL"
const val ARRAY_8S_IMPL = "ARRAY_8S_IMPL"
const val ARRAY_9_IMPL = "ARRAY_9_IMPL"
const val ARRAY_9S_IMPL = "ARRAY_9S_IMPL"
const val ARRAY_12_IMPL = "ARRAY_12_IMPL"
const val ARRAY_12S_IMPL = "ARRAY_12S_IMPL"
const val ARRAY_13_IMPL = "ARRAY_13_IMPL"
const val ARRAY_13S_IMPL = "ARRAY_13S_IMPL"
const val ARRAY_19_IMPL = "ARRAY_19_IMPL"
const val ARRAY_19S_IMPL = "ARRAY_19S_IMPL"
const val ARRAY_19B_IMPL = "ARRAY_19B_IMPL"
const val ARRAY_19SB_IMPL = "ARRAY_19SB_IMPL"
const val ARRAY_25_IMPL = "ARRAY_25_IMPL"
const val ARRAY_25S_IMPL = "ARRAY_25S_IMPL"
const val ARRAY_31_IMPL = "ARRAY_31_IMPL"
const val ARRAY_31S_IMPL = "ARRAY_31S_IMPL"
const val ARRAY_32_IMPL = "ARRAY_32_IMPL"
const val ARRAY_32S_IMPL = "ARRAY_32S_IMPL"
const val ARRAY_48_IMPL = "ARRAY_48_IMPL"
const val ARRAY_48S_IMPL = "ARRAY_48S_IMPL"
const val ARRAY_61_IMPL = "ARRAY_61_IMPL"
const val ARRAY_61S_IMPL = "ARRAY_61S_IMPL"
const val ARRAY_64_IMPL = "ARRAY_64_IMPL"
const val ARRAY_64S_IMPL = "ARRAY_64S_IMPL"

val EMPTY_DEQUE = mapOf<String, ImmutableDeque<String>>(
        INITIAL_IMPL to immutableDeque.initial.persistentDeque.emptyDeque(),

        STACK_7_IMPL to immutableDeque.smallDequeOptimization.stackBuffer.bufferSize7.emptyDeque.emptyDeque(),
        STACK_8_IMPL to immutableDeque.smallDequeOptimization.stackBuffer.bufferSize8.emptyDeque.emptyDeque(),
        STACK_9_IMPL to immutableDeque.smallDequeOptimization.stackBuffer.bufferSize9.emptyDeque.emptyDeque(),
        STACK_12_IMPL to immutableDeque.smallDequeOptimization.stackBuffer.bufferSize12.emptyDeque.emptyDeque(),
        STACK_13_IMPL to immutableDeque.smallDequeOptimization.stackBuffer.bufferSize13.emptyDeque.emptyDeque(),
        STACK_19_IMPL to immutableDeque.smallDequeOptimization.stackBuffer.bufferSize19.emptyDeque.emptyDeque(),
        STACK_19B_IMPL to immutableDeque.smallDequeOptimization.stackBuffer.bufferSize19B.emptyDeque.emptyDeque(),
        STACK_25_IMPL to immutableDeque.smallDequeOptimization.stackBuffer.bufferSize25.emptyDeque.emptyDeque(),
        STACK_25O_IMPL to immutableDeque.smallDequeOptimization.stackBuffer.bufferSize25O.emptyDeque.emptyDeque(),
        STACK_31_IMPL to immutableDeque.smallDequeOptimization.stackBuffer.bufferSize31.emptyDeque.emptyDeque(),
        STACK_32_IMPL to immutableDeque.smallDequeOptimization.stackBuffer.bufferSize32.emptyDeque.emptyDeque(),
        STACK_48_IMPL to immutableDeque.smallDequeOptimization.stackBuffer.bufferSize48.emptyDeque.emptyDeque(),
        STACK_61_IMPL to immutableDeque.smallDequeOptimization.stackBuffer.bufferSize61.emptyDeque.emptyDeque(),
        STACK_64_IMPL to immutableDeque.smallDequeOptimization.stackBuffer.bufferSize64.emptyDeque.emptyDeque(),

        ARRAY_7_IMPL to immutableDeque.smallDequeOptimization.arrayBuffer.bufferSize7.emptyDeque.emptyDeque(),
        ARRAY_7S_IMPL to immutableDeque.smallDequeOptimization.arrayBuffer.bufferSize7S.emptyDeque.emptyDeque(),
        ARRAY_8_IMPL to immutableDeque.smallDequeOptimization.arrayBuffer.bufferSize8.emptyDeque.emptyDeque(),
        ARRAY_8S_IMPL to immutableDeque.smallDequeOptimization.arrayBuffer.bufferSize8S.emptyDeque.emptyDeque(),
        ARRAY_9_IMPL to immutableDeque.smallDequeOptimization.arrayBuffer.bufferSize9.emptyDeque.emptyDeque(),
        ARRAY_9S_IMPL to immutableDeque.smallDequeOptimization.arrayBuffer.bufferSize9S.emptyDeque.emptyDeque(),
        ARRAY_12_IMPL to immutableDeque.smallDequeOptimization.arrayBuffer.bufferSize12.emptyDeque.emptyDeque(),
        ARRAY_12S_IMPL to immutableDeque.smallDequeOptimization.arrayBuffer.bufferSize12S.emptyDeque.emptyDeque(),
        ARRAY_13_IMPL to immutableDeque.smallDequeOptimization.arrayBuffer.bufferSize13.emptyDeque.emptyDeque(),
        ARRAY_13S_IMPL to immutableDeque.smallDequeOptimization.arrayBuffer.bufferSize13S.emptyDeque.emptyDeque(),
        ARRAY_19_IMPL to immutableDeque.smallDequeOptimization.arrayBuffer.bufferSize19.emptyDeque.emptyDeque(),
        ARRAY_19S_IMPL to immutableDeque.smallDequeOptimization.arrayBuffer.bufferSize19S.emptyDeque.emptyDeque(),
        ARRAY_19B_IMPL to immutableDeque.smallDequeOptimization.arrayBuffer.bufferSize19B.emptyDeque.emptyDeque(),
        ARRAY_19SB_IMPL to immutableDeque.smallDequeOptimization.arrayBuffer.bufferSize19SB.emptyDeque.emptyDeque(),
        ARRAY_25_IMPL to immutableDeque.smallDequeOptimization.arrayBuffer.bufferSize25.emptyDeque.emptyDeque(),
        ARRAY_25S_IMPL to immutableDeque.smallDequeOptimization.arrayBuffer.bufferSize25S.emptyDeque.emptyDeque(),
        ARRAY_31_IMPL to immutableDeque.smallDequeOptimization.arrayBuffer.bufferSize31.emptyDeque.emptyDeque(),
        ARRAY_31S_IMPL to immutableDeque.smallDequeOptimization.arrayBuffer.bufferSize31S.emptyDeque.emptyDeque(),
        ARRAY_32_IMPL to immutableDeque.smallDequeOptimization.arrayBuffer.bufferSize32.emptyDeque.emptyDeque(),
        ARRAY_32S_IMPL to immutableDeque.smallDequeOptimization.arrayBuffer.bufferSize32S.emptyDeque.emptyDeque(),
        ARRAY_48_IMPL to immutableDeque.smallDequeOptimization.arrayBuffer.bufferSize48.emptyDeque.emptyDeque(),
        ARRAY_48S_IMPL to immutableDeque.smallDequeOptimization.arrayBuffer.bufferSize48S.emptyDeque.emptyDeque(),
        ARRAY_61_IMPL to immutableDeque.smallDequeOptimization.arrayBuffer.bufferSize61.emptyDeque.emptyDeque(),
        ARRAY_61S_IMPL to immutableDeque.smallDequeOptimization.arrayBuffer.bufferSize61S.emptyDeque.emptyDeque(),
        ARRAY_64_IMPL to immutableDeque.smallDequeOptimization.arrayBuffer.bufferSize64.emptyDeque.emptyDeque(),
        ARRAY_64S_IMPL to immutableDeque.smallDequeOptimization.arrayBuffer.bufferSize64S.emptyDeque.emptyDeque())