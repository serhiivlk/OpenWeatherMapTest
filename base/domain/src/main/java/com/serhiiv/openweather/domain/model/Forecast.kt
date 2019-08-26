package com.serhiiv.openweather.domain.model

data class Forecast(
    val city: City?,
    val count: Int?,
    val items: List<ForecastItem>?
)
