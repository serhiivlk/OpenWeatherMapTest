package com.serhiiv.openweather.core.model

data class ForecastItem(
    val date: Long?,
    val dateText: String?,
    val main: Main?,
    val weather: Weather?
)
