package com.serhiiv.openweather.data.local.model

import com.serhiiv.openweather.data.local.model.base.CachedEntity
import com.squareup.moshi.Json

data class SelectableCityJsonEntity(
    @field:Json(name = "id")
    val id: Long,
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "country")
    val country: String
) : CachedEntity
