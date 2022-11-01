package com.memoir.home.repo

import com.memoir.home.model.WeatherResponse

open class HomeRepository(
    private val apiClient: WeatherApiClient,
    private val memCache: LinkedHashMap<String, WeatherResponse>
) {

    companion object {
        const val APP_ID = "60c6fbeb4b93ac653c492ba806fc346d"
        const val UNIT_METRIC = "metric"
    }

    open suspend fun fetch(
        city: String, limit: Int
    ): WeatherResponse {
        val cached = memCache[city]
        return if (cached == null) {
            val res = apiClient.fetch(city, limit, APP_ID, UNIT_METRIC)
            memCache[city] = res
            res
        } else {
            cached
        }
    }

}