package com.serhiiv.openweather.data.datasource

import com.serhiiv.openweather.data.model.ForecastEntity

interface ForecastLocalDataSource {
    suspend fun clearForecast()
    suspend fun storeForecast(forecast: ForecastEntity)
    suspend fun getForecast(): ForecastEntity
}
