package com.serhiiv.openweather.data.remote.mapper

import com.serhiiv.openweather.data.model.MainEntity
import com.serhiiv.openweather.data.remote.model.MainResponse
import javax.inject.Inject

class MainEntityMapper @Inject constructor(): EntityMapper<MainEntity, MainResponse> {
    override fun map(from: MainResponse): MainEntity = with(from){
        MainEntity(
            temp = temp,
            tempMin = tempMin,
            tempMax = tempMax,
            pressure = pressure,
            humidity = humidity
        )
    }
}
