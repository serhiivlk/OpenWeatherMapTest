package com.serhiiv.openweather.domain.model

data class ForecastItem(
    val date: Long?,
    val dateText: String?,
    val main: Main?,
    val weather: Weather?
)
