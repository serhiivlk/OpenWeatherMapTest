package com.serhiiv.openweather.data.local.mapper

import com.piotrek1543.android.boilerplate.cache.model.MainCachedEntity
import com.serhiiv.openweather.data.model.MainEntity
import javax.inject.Inject

open class MainEntityMapper @Inject constructor() :
    CachedEntityMapper<MainCachedEntity, MainEntity> {

    override fun mapToCashed(entity: MainEntity): MainCachedEntity = with(entity) {
        MainCachedEntity(
            temp = temp,
            tempMin = tempMin,
            tempMax = tempMax,
            pressure = pressure,
            humidity = humidity
        )
    }

    override fun mapFromCashed(cached: MainCachedEntity): MainEntity = with(cached) {
        MainEntity(
            temp = temp,
            tempMin = tempMin,
            tempMax = tempMax,
            pressure = pressure,
            humidity = humidity
        )
    }
}
