package com.serhiiv.openweather.domain.repository

import com.serhiiv.openweather.domain.model.Forecast

interface ForecastRepository {
    suspend fun getForecastByCityName(cityName: String): Forecast
}
