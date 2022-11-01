package com.memoir.home.viewmodel


data class WeatherItemViewModel(
    val id: Int,
    val date: String,
    val temp: String,
    val pressure: Int,
    val humidity: Int,
    val weather: String,
    val desc: String
)