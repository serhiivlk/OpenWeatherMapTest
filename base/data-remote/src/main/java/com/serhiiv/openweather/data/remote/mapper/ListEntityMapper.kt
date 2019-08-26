package com.serhiiv.openweather.data.remote.mapper

import com.serhiiv.openweather.data.model.ListEntity
import com.serhiiv.openweather.data.remote.model.ForecastListItemResponse
import javax.inject.Inject

class ListEntityMapper @Inject constructor(
    private val mainEntityMapper: MainEntityMapper,
    private val weatherEntityMapper: WeatherEntityMapper
) : EntityMapper<ListEntity, ForecastListItemResponse> {
    override fun map(from: ForecastListItemResponse): ListEntity = with(from) {
        ListEntity(
            dt = dt,
            dtTxt = dtTxt,
            main = main?.let(mainEntityMapper::invoke),
            weather = weather?.firstOrNull()?.let(weatherEntityMapper::invoke)
        )
    }
}
