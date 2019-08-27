package com.serhiiv.openweather.domain.repository

import com.serhiiv.openweather.core.model.SelectableCity

interface SelectableCityRepository {
    suspend fun getAll(): List<SelectableCity>
}
