package com.memoir.home.model

class WeatherResponse(val list: List<Forecast>)

data class Forecast(
    val dt: Long, val pressure: Int, val humidity: Int, val weather: List<Weather>,
    val temp: Temperature
)

data class Weather(val main: String, val description: String)

data class Temperature(val min: Float, val max: Float)