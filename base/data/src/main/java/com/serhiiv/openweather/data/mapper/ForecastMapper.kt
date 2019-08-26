package com.serhiiv.openweather.data.mapper

import com.serhiiv.openweather.data.model.ForecastEntity
import com.serhiiv.openweather.domain.model.Forecast
import javax.inject.Inject

class ForecastMapper @Inject constructor(
    private val cityMapper: CityMapper,
    private val listMapper: ListMapper
) : DomainMapper<Forecast, ForecastEntity> {
    override fun map(from: ForecastEntity): Forecast = with(from) {
        Forecast(
            city = city?.let(cityMapper::invoke),
            count = cnt,
            items = list?.let(listMapper::invoke)
        )
    }
}