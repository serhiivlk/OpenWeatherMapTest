package com.serhiiv.openweather.data

import com.serhiiv.openweather.core.model.Forecast
import com.serhiiv.openweather.data.datasource.ForecastLocalDataSource
import com.serhiiv.openweather.data.datasource.ForecastRemoteDataSource
import com.serhiiv.openweather.data.mapper.ForecastMapper
import com.serhiiv.openweather.domain.repository.ForecastRepository
import javax.inject.Inject

class ForecastRepositoryImpl @Inject constructor(
    private val remoteDataSource: ForecastRemoteDataSource,
    private val localDataSource: ForecastLocalDataSource,
    private val forecastMapper: ForecastMapper,
    private val networkHandler: NetworkHandler
) : ForecastRepository {
    override suspend fun getForecastByCityName(cityName: String, units: String): Forecast {
        return try {
            remoteDataSource.getForecastByCityName(cityName, units).also {
                localDataSource.storeForecast(it)
            }
        } catch (e: Exception) {
            localDataSource.getForecast()
        }
            .let(forecastMapper::invoke)
    }
}
