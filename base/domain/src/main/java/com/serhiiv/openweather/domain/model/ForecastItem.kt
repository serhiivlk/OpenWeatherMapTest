package com.serhiiv.openweather.domain.model

data class ForecastItem(
    val date: Long?,
    val main: Main?,
    val weather: Weather?
)
