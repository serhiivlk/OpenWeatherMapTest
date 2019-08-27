package com.serhiiv.openweather.data.local.mapper

import com.serhiiv.openweather.data.local.model.SelectableCityJsonEntity
import com.serhiiv.openweather.data.model.SelectableCityEntity
import javax.inject.Inject

class SelectableCityEntityMapper @Inject constructor() :
    CachedEntityMapper<SelectableCityJsonEntity, SelectableCityEntity> {
    override fun mapToCashed(entity: SelectableCityEntity): SelectableCityJsonEntity =
        with(entity) {
            SelectableCityJsonEntity(
                id = id,
                name = name,
                country = country
            )
        }

    override fun mapFromCashed(cached: SelectableCityJsonEntity): SelectableCityEntity =
        with(cached) {
            SelectableCityEntity(
                id = id,
                name = name,
                country = country
            )
        }
}
