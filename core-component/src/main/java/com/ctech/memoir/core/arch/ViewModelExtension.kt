package com.ctech.memoir.core.arch

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.channels.ReceiveChannel

fun <T> ViewModel.produceActions(scope: CoroutineScope,f: suspend ProducerScope<Action<T>>.() -> Unit): ReceiveChannel<Action<T>> {
    return scope
            .produceActions(f)
}