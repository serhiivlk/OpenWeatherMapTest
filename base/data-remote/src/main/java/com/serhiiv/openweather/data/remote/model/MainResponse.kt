package com.serhiiv.openweather.data.remote.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MainResponse(
    @field:Json(name = "temp")
    val temp: Double? = null,
    @field:Json(name = "temp_min")
    val tempMin: Double? = null,
    @field:Json(name = "temp_max")
    val tempMax: Double? = null,
    @field:Json(name = "pressure")
    val pressure: Double? = null,
    @field:Json(name = "sea_level")
    val seaLevel: Double? = null,
    @field:Json(name = "grnd_level")
    val grndLevel: Double? = null,
    @field:Json(name = "humidity")
    val humidity: Int? = null,
    @field:Json(name = "temp_kf")
    val tempKf: Double? = null
)