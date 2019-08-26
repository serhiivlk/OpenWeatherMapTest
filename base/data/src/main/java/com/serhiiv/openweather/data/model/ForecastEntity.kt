package com.serhiiv.openweather.data.model

data class ForecastEntity(
    val city: CityEntity? = null,
    val cnt: Int? = null,
    val list: List<ListEntity>? = null
)