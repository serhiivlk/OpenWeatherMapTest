package com.serhiiv.openweather.data.remote.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherResponse(
    @field:Json(name = "id")
    val id: Long? = null,
    @field:Json(name = "main")
    val main: String? = null,
    @field:Json(name = "description")
    val description: String? = null,
    @field:Json(name = "icon")
    val icon: String? = null
)
