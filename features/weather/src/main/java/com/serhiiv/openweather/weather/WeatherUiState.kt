package com.serhiiv.openweather.weather

import com.serhiiv.openweather.core.model.Forecast
import javax.inject.Inject

data class WeatherUiState(
    val cityName: String?,
    val temp: String?,
    val tempMinMax: String?,
    val pressure: String?,
    val humidity: String?,
    val description: String?,
    val iconUrl: String?
) {
    class Mapper @Inject constructor(private val res: WeatherResources) {
        operator fun invoke(forecast: Forecast, units: String): WeatherUiState = with(forecast) {
            val cityName = city?.name ?: ""
            val unitsSymbol = unitsSymbol(units)
            var temp = ""
            var tempMinMax = ""
            var pressure = ""
            var humidity = ""
            var iconUrl = ""
            var description = ""
            items?.firstOrNull()?.also {
                it.main?.also { main ->
                    val (_temp, tempMin, tempMax, _pressure, _humidity) = main
                    temp = _temp?.let { temp -> res.getTemp(temp, unitsSymbol) } ?: ""

                    tempMinMax = when {
                        tempMin != null && tempMax != null -> res.getTempMinMax(
                            tempMin,
                            tempMax,
                            unitsSymbol
                        )
                        else -> ""
                    }
                    pressure = _pressure?.let(res::getPressure) ?: ""
                    humidity = _humidity?.let(res::getHumidity) ?: ""
                }
                it.weather?.also { weather ->
                    description = weather.description ?: ""
                    iconUrl = weather.icon?.let(res::getIconUrl) ?: ""
                }
            }
            WeatherUiState(
                cityName = cityName,
                temp = temp,
                tempMinMax = tempMinMax,
                pressure = pressure,
                humidity = humidity,
                description = description,
                iconUrl = iconUrl
            )
        }

        private fun unitsSymbol(units: String) = when (units) {
            "metric" -> "C"
            "imperial" -> "F"
            else -> ""
        }
    }
}
