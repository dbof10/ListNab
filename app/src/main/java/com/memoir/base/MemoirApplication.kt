package com.memoir.base

import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class MemoirApplication : MultiDexApplication() {

//    override val coroutineContext: CoroutineContext
//        get() = Dispatchers.IO


    override fun onCreate() {
        super.onCreate()


//        launch {
//            if (BuildConfig.DEBUG) {
//                Timber.plant(Timber.DebugTree())
//            }
//
//        }

    }

}