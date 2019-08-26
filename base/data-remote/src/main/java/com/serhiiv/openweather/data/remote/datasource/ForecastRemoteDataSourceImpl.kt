package com.serhiiv.openweather.data.remote.datasource

import com.serhiiv.openweather.data.datasource.ForecastRemoteDataSource
import com.serhiiv.openweather.data.model.ForecastEntity
import com.serhiiv.openweather.data.remote.ApiConstants
import com.serhiiv.openweather.data.remote.ForecastService
import com.serhiiv.openweather.data.remote.mapper.ForecastEntityMapper
import javax.inject.Inject

class ForecastRemoteDataSourceImpl @Inject constructor(
    private val service: ForecastService,
    private val forecastEntityMapper: ForecastEntityMapper
) : ForecastRemoteDataSource {
    override suspend fun getForecastByCityName(cityName: String, units: String): ForecastEntity {
        val apiKey = ApiConstants.API_KEY
        return service.getForecastByCityName(cityName, units, apiKey)
            .let(forecastEntityMapper::invoke)
    }
}
