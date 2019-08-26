package com.serhiiv.openweather.data.model

data class ListEntity(
    val dt: Long? = null,
    val dtTxt: String? = null,
    val main: MainEntity? = null,
    val weather: WeatherEntity? = null
)
