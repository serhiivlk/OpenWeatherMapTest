package com.serhiiv.openweather.data.mapper

import com.serhiiv.openweather.data.model.ListEntity
import com.serhiiv.openweather.domain.model.ForecastItem
import javax.inject.Inject

class ListMapper @Inject constructor(
    private val mainMapper: MainMapper,
    private val weatherMapper: WeatherMapper
) : DomainMapper<ForecastItem, ListEntity> {
    override fun map(from: ListEntity): ForecastItem = with(from) {
        ForecastItem(
            date = dt,
            main = main?.let(mainMapper::invoke),
            weather = weather?.let(weatherMapper::invoke)
        )
    }
}