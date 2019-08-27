package com.serhiiv.openweather.data.local.mapper

import com.serhiiv.openweather.data.local.model.CityCashedEntity
import com.serhiiv.openweather.data.model.CityEntity
import javax.inject.Inject

open class CityEntityMapper @Inject constructor() :
    CachedEntityMapper<CityCashedEntity, CityEntity> {

    override fun mapToCashed(entity: CityEntity): CityCashedEntity = with(entity) {
        return CityCashedEntity(
            id = id,
            name = name,
            country = country
        )
    }

    override fun mapFromCashed(cached: CityCashedEntity): CityEntity = with(cached) {
        return CityEntity(
            id = id,
            name = name,
            country = country
        )
    }
}
