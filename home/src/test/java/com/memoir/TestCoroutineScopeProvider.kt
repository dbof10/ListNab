package com.memoir

import com.ctech.memoir.core.arch.CoroutineScopeProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope

class TestCoroutineScopeProvider : CoroutineScopeProvider {

    override val io: CoroutineDispatcher
        get() = TestCoroutineDispatcher()
    override val main: CoroutineDispatcher
        get() = TestCoroutineDispatcher()
    override val async: CoroutineScope
        get() = TestCoroutineScope()

}