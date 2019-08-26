package com.serhiiv.openweather.domain.model

data class Main(
    val temp: Double?,
    val tempMin: Double?,
    val tempMax: Double?,
    val pressure: Double?,
    val humidity: Int?
)
