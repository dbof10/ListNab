package com.memoir.home.viewmodel


data class HomeViewState(val content: List<WeatherItemViewModel>? = null,
                         val loading: Boolean = false,
                         val error: Throwable? = null
)