package com.serhiiv.openweather.domain.repository

import com.serhiiv.openweather.core.model.Forecast

interface ForecastRepository {
    suspend fun getForecastByCityName(cityName: String, units: String): Forecast
}
