package com.serhiiv.openweather.data.datasource

import com.serhiiv.openweather.data.model.SelectableCityEntity

interface SelectableCityLocalDataSource {
    suspend fun getAll(): List<SelectableCityEntity>
}