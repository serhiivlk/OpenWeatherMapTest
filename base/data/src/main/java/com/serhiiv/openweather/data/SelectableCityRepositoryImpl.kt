package com.serhiiv.openweather.data

import com.serhiiv.openweather.core.model.SelectableCity
import com.serhiiv.openweather.data.datasource.SelectableCityLocalDataSource
import com.serhiiv.openweather.data.mapper.SelectableCityMapper
import com.serhiiv.openweather.domain.repository.SelectableCityRepository
import javax.inject.Inject

class SelectableCityRepositoryImpl @Inject constructor(
    private val localDataStore: SelectableCityLocalDataSource,
    private val selectableCityMapper: SelectableCityMapper
) : SelectableCityRepository {
    override suspend fun getAll(): List<SelectableCity> {
        return localDataStore.getAll()
            .let(selectableCityMapper::invoke)
    }

    override suspend fun getSelectedCityName(): String {
        return localDataStore.getSelectedCityName()
    }

    override suspend fun storeCityName(cityName: String) {
        localDataStore.storeCityName(cityName)
    }
}
