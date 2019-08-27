package com.serhiiv.openweather.data.mapper

import com.serhiiv.openweather.core.model.SelectableCity
import com.serhiiv.openweather.data.model.SelectableCityEntity
import javax.inject.Inject

class SelectableCityMapper @Inject constructor() :
    DomainMapper<SelectableCity, SelectableCityEntity> {
    override fun map(from: SelectableCityEntity): SelectableCity = with(from) {
        SelectableCity(
            id = id,
            name = name,
            country = country
        )
    }
}
