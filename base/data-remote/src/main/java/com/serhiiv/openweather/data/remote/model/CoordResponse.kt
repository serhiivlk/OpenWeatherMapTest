package com.serhiiv.openweather.data.remote.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CoordResponse(
    @field:Json(name = "lat")
    val lat: Double? = null,
    @field:Json(name = "lon")
    val lon: Double? = null
)
