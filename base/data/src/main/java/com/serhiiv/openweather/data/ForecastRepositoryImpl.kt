package com.serhiiv.openweather.data

import com.serhiiv.openweather.core.model.Forecast
import com.serhiiv.openweather.data.datasource.ForecastRemoteDataSource
import com.serhiiv.openweather.data.mapper.ForecastMapper
import com.serhiiv.openweather.domain.repository.ForecastRepository
import javax.inject.Inject

class ForecastRepositoryImpl @Inject constructor(
    private val remoteDataSource: ForecastRemoteDataSource,
    private val forecastMapper: ForecastMapper
) : ForecastRepository {
    override suspend fun getForecastByCityName(cityName: String, units: String): Forecast {
        return remoteDataSource.getForecastByCityName(cityName, units)
            .let(forecastMapper::invoke)
    }
}
