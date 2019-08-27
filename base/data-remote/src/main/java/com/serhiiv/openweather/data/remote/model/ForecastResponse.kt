package com.serhiiv.openweather.data.remote.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastResponse(
    @field:Json(name = "cod")
    val cod: String? = null,
    @field:Json(name = "message")
    val message: Double? = null,
    @field:Json(name = "cnt")
    val cnt: Int? = null,
    @field:Json(name = "list")
    val list: List<ForecastListItemResponse?> = emptyList(),
    @field:Json(name = "city")
    val city: CityResponse? = null
)
