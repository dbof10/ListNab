package com.ctech.memoir.core.arch

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class ViewStateStore<T : Any>(
        initialState: T,
        private val scopeProvider: CoroutineScopeProvider
) : CoroutineScope {

    private val liveData by lazy {
        MutableLiveData<T>(initialState)
    }

    private val job = Job()

    override val coroutineContext: CoroutineContext = job + scopeProvider.io

    fun observe(owner: LifecycleOwner, observer: (T) -> Unit) =
            liveData.observe(owner, Observer { observer(it!!) })

    @MainThread
    fun dispatchState(state: T) {
        liveData.value = state
    }

    fun dispatchAction(f: suspend (T) -> Action<T>) {
        launch {
            val action = f(state)
            val newState = action(state)
            if (newState != state) {
                withContext(scopeProvider.main) {
                    dispatchState(newState)
                }
            }
        }
    }

    fun dispatchActions(channel: ReceiveChannel<Action<T>>) {
        launch {
            channel.consumeEach { action ->

                val newState = action(state)
                if (newState != state) {
                    withContext(scopeProvider.main) {
                        dispatchState(newState)
                    }
                }
            }
        }
    }

    val state get() = liveData.value!!

    fun cancel() = job.cancel()
}