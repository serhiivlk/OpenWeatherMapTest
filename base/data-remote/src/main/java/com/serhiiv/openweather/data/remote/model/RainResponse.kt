package com.serhiiv.openweather.data.remote.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RainResponse(
    @field:Json(name = "3h")
    val h: Double? = null
)
