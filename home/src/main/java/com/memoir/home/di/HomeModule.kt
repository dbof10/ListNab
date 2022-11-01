package com.memoir.home.di

import com.memoir.home.ui.WeatherAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object HomeModule {


    @Provides
    fun provideHomeAdapter(): WeatherAdapter {
        return WeatherAdapter(WeatherAdapter.DIFFER)
    }


}