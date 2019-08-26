package com.serhiiv.openweather.data.remote.mapper

import com.serhiiv.openweather.data.model.CityEntity
import com.serhiiv.openweather.data.remote.model.CityResponse
import javax.inject.Inject

class CityEntityMapper @Inject constructor() : EntityMapper<CityEntity, CityResponse> {
    override fun map(from: CityResponse): CityEntity = with(from) {
        CityEntity(
            id = id,
            name = name,
            country = country
        )
    }
}
