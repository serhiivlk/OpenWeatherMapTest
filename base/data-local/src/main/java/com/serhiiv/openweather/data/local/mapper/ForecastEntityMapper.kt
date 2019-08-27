package com.serhiiv.openweather.data.local.mapper

import com.serhiiv.openweather.data.local.model.ForecastCachedEntity
import com.serhiiv.openweather.data.model.ForecastEntity
import javax.inject.Inject

open class ForecastEntityMapper @Inject constructor() :
    CachedEntityMapper<ForecastCachedEntity, ForecastEntity> {

    override fun mapToCashed(entity: ForecastEntity): ForecastCachedEntity = with(entity) {
        ForecastCachedEntity(
            cityId = entity.city?.id ?: 0,
            cnt = cnt
        )
    }

    override fun mapFromCashed(cached: ForecastCachedEntity): ForecastEntity = with(cached) {
        ForecastEntity(
            cnt = cnt
        )
    }
}
