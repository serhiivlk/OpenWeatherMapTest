package com.serhiiv.openweather.data.local.mapper

import com.piotrek1543.android.boilerplate.cache.model.WeatherCachedEntity
import com.serhiiv.openweather.data.model.WeatherEntity
import javax.inject.Inject

open class WeatherEntityMapper @Inject constructor() :
    CachedEntityMapper<WeatherCachedEntity, WeatherEntity> {
    override fun mapToCashed(entity: WeatherEntity): WeatherCachedEntity = with(entity) {
        WeatherCachedEntity(
            id = id,
            main = main,
            description = description,
            icon = icon
        )
    }

    override fun mapFromCashed(cached: WeatherCachedEntity): WeatherEntity = with(cached) {
        WeatherEntity(
            id = id,
            main = main,
            description = description,
            icon = icon
        )
    }
}
