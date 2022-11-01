package com.ctech.memoir.di


import com.google.gson.Gson
import com.memoir.home.repo.HomeRepository
import com.memoir.home.repo.WeatherApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {


    @Provides
    @Singleton
    fun provideHomeRepo(api: WeatherApiClient): HomeRepository {
        return HomeRepository(api, LinkedHashMap())
    }


    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()


        val loggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Timber.tag("OkHttp").d(message)
            }

        })

        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        builder.addInterceptor(loggingInterceptor)

        return builder
            .followRedirects(false)
            .build()
    }


    @Singleton
    @Provides
    fun provideRetrofit(
        gson: Gson,
        okHttpClient: OkHttpClient
    ): Retrofit {

        return Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Singleton
    @Provides
    fun provideApiClient(
        retrofit: Retrofit
    ): WeatherApiClient {

        return retrofit.create(WeatherApiClient::class.java)
    }

}