package com.memoir.home.di

import android.content.Context
import androidx.room.Room
import com.memoir.home.repo.HomeRepository
import com.memoir.home.repo.PropertyApiClient
import com.memoir.home.repo.PropertyDatabase
import com.memoir.home.ui.PropertiesAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
object HomeModule {

    @Provides
    @ActivityRetainedScoped
    fun provideHomeRepo(api: PropertyApiClient, db: PropertyDatabase): HomeRepository {
        return HomeRepository(api, db.propertiesDao())
    }

    @Provides
    fun providePropertiesAdapter(): PropertiesAdapter {
        return PropertiesAdapter(PropertiesAdapter.DIFFER)
    }


    @Provides
    fun providePropertyDatabase(@ApplicationContext context: Context): PropertyDatabase {
        val db = Room.databaseBuilder(
            context,
            PropertyDatabase::class.java, "property"
        ).build()
        return db
    }

}