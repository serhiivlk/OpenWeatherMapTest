package com.serhiiv.openweather.data.remote.mapper

import com.serhiiv.openweather.data.model.ForecastEntity
import com.serhiiv.openweather.data.remote.model.ForecastResponse
import javax.inject.Inject

class ForecastEntityMapper @Inject constructor(
    private val cityEntityMapper: CityEntityMapper,
    private val listEntityMapper: ListEntityMapper
) : EntityMapper<ForecastEntity, ForecastResponse> {
    override fun map(from: ForecastResponse): ForecastEntity = with(from) {
        ForecastEntity(
            city = city?.let(cityEntityMapper::invoke),
            cnt = cnt,
            list = list?.filterNotNull()?.let(listEntityMapper::invoke)
        )
    }
}
