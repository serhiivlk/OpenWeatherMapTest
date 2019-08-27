package com.serhiiv.openweather.data.mapper

import com.serhiiv.openweather.core.model.Weather
import com.serhiiv.openweather.data.model.WeatherEntity
import javax.inject.Inject

class WeatherMapper @Inject constructor() : DomainMapper<Weather, WeatherEntity> {
    override fun map(from: WeatherEntity): Weather = with(from) {
        Weather(
            id = id,
            main = main,
            description = description,
            icon = icon
        )
    }
}
