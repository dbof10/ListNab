package com.ctech.memoir.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.ctech.memoir.core.arch.AppCoroutineScopeProvider
import com.ctech.memoir.core.arch.CoroutineScopeProvider
import com.ctech.memoir.res.AppResourcesProvider
import com.ctech.memoir.res.ResourcesProvider
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    const val SHAREDPREFS_NAME = "memoir" + "_preferences"

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .create()
    }

    @Provides
    fun provideSharedPrefs(application: Application): SharedPreferences {
        return application.getSharedPreferences(SHAREDPREFS_NAME, Context.MODE_PRIVATE)
    }

    @Provides
    fun provideResProvider(application: Application): ResourcesProvider {
        return AppResourcesProvider(application)
    }


    @Provides
    @Singleton
    fun provideCoroutineScopeProvider(): CoroutineScopeProvider = AppCoroutineScopeProvider()


}