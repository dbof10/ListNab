package com.memoir.home.viewmodel

data class PropertyItemViewModel(
    val id: String, val isFavorite: Boolean?,
    val location: String, val price: String,
    val imageUrls: List<String>,
    val title: String
)