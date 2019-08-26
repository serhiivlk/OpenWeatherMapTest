package com.serhiiv.openweather.data.remote.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastListItemResponse(
    @Json(name = "dt")
    val dt: Long? = null,
    @Json(name = "main")
    val main: MainResponse? = null,
    @Json(name = "weather")
    val weather: List<WeatherResponse?>? = null,
    @Json(name = "clouds")
    val clouds: CloudsResponse? = null,
    @Json(name = "wind")
    val wind: WindResponse? = null,
    @Json(name = "sys")
    val sys: SysResponse? = null,
    @Json(name = "dt_txt")
    val dtTxt: String? = null,
    @Json(name = "rain")
    val rain: RainResponse? = null
)