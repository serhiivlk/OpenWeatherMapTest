package com.serhiiv.openweather.data.mapper

import com.serhiiv.openweather.core.model.Main
import com.serhiiv.openweather.data.model.MainEntity
import javax.inject.Inject

class MainMapper @Inject constructor() : DomainMapper<Main, MainEntity>{
    override fun map(from: MainEntity): Main = with(from){
        Main(
            temp = temp,
            tempMin = tempMin,
            tempMax = tempMax,
            pressure = pressure,
            humidity = humidity
        )
    }
}
