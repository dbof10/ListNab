package com.ctech.memoir.core.arch

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce

class Action<T>(private val f: T.() -> T) {
    operator fun invoke(t: T) = t.f()
}

@ExperimentalCoroutinesApi
fun <T> CoroutineScope.produceActions(f: suspend ProducerScope<Action<T>>.() -> Unit): ReceiveChannel<Action<T>> {
    return produce(block = f)
}

@ExperimentalCoroutinesApi
suspend fun <T> ProducerScope<Action<T>>.emit(f: T.() -> T) = send(Action(f))