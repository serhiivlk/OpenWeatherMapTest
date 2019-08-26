package com.serhiiv.openweather.data.mapper

import com.serhiiv.openweather.data.model.CityEntity
import com.serhiiv.openweather.domain.model.City
import javax.inject.Inject

class CityMapper @Inject constructor() : DomainMapper<City, CityEntity> {
    override fun map(from: CityEntity): City = with(from) {
        return City(
            id = id,
            name = name,
            country = country
        )
    }
}