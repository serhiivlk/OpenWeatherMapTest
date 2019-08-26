package com.serhiiv.openweather.data.remote.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CityResponse(
    @field:Json(name = "id")
    val id: Long? = null,
    @field:Json(name = "name")
    val name: String? = null,
    @field:Json(name = "coord")
    val coord: CoordResponse? = null,
    @field:Json(name = "country")
    val country: String? = null,
    @field:Json(name = "population")
    val population: Int? = null,
    @field:Json(name = "timezone")
    val timezone: Int? = null,
    @field:Json(name = "sunrise")
    val sunrise: Int? = null,
    @field:Json(name = "sunset")
    val sunset: Int? = null
)
