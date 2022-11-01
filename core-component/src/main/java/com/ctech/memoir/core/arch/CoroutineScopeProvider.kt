package com.ctech.memoir.core.arch

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope

interface CoroutineScopeProvider {
    val io: CoroutineDispatcher
    val main: CoroutineDispatcher
    val async: CoroutineScope
}


class AppCoroutineScopeProvider : CoroutineScopeProvider {

    override val io: CoroutineDispatcher
        get() = Dispatchers.IO
    override val main: CoroutineDispatcher
        get() = Dispatchers.Main
    override val async: CoroutineScope
        get() = GlobalScope

}

