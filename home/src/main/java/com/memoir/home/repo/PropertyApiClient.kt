package com.memoir.home.repo

import com.memoir.home.model.GetPropertiesResponse
import retrofit2.http.GET

interface PropertyApiClient {

    @GET("properties")
    suspend fun fetch(): GetPropertiesResponse
}