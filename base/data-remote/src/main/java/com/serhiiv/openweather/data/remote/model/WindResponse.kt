package com.serhiiv.openweather.data.remote.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WindResponse(
    @field:Json(name = "speed")
    val speed: Double? = null,
    @field:Json(name = "deg")
    val deg: Double? = null
)
