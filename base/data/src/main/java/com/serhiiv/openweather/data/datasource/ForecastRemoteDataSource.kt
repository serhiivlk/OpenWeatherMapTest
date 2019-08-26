package com.serhiiv.openweather.data.datasource

import com.serhiiv.openweather.data.model.ForecastEntity

interface ForecastRemoteDataSource {
    suspend fun getForecastByCityName(cityName: String, units: String): ForecastEntity
}
