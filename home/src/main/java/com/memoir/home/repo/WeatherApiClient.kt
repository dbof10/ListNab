package com.memoir.home.repo

import com.memoir.home.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiClient {

    @GET("forecast/daily")
    suspend fun fetch(
        @Query("q") city:String,
        @Query("cnt") limit:Int,
        @Query("appid") appId:String,
        @Query("units") units:String,

        ): WeatherResponse
}