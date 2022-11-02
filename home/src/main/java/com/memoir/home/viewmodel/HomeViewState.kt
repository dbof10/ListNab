package com.memoir.home.viewmodel


data class HomeViewState(val content: List<PropertyItemViewModel>? = null,
                         val loading: Boolean = false,
                         val error: Throwable? = null
)