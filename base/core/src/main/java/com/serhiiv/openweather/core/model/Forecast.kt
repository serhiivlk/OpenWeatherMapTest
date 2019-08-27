package com.serhiiv.openweather.core.model

data class Forecast(
    val city: City?,
    val count: Int?,
    val items: List<ForecastItem>?
)
