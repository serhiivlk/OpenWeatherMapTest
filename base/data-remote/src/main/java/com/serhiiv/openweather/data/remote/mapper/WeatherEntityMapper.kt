package com.serhiiv.openweather.data.remote.mapper

import com.serhiiv.openweather.data.model.WeatherEntity
import com.serhiiv.openweather.data.remote.model.WeatherResponse
import javax.inject.Inject

class WeatherEntityMapper @Inject constructor(): EntityMapper<WeatherEntity, WeatherResponse> {
    override fun map(from: WeatherResponse): WeatherEntity = with(from){
        WeatherEntity(
            id = id,
            main = main,
            description = description,
            icon = icon
        )
    }
}