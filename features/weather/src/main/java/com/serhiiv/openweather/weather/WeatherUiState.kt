package com.serhiiv.openweather.weather

import com.serhiiv.openweather.core.model.Forecast
import com.serhiiv.openweather.core.model.ForecastItem
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

data class WeatherUiState(
    val cityName: String?,
    val temp: String?,
    val tempMinMax: String?,
    val pressure: String?,
    val humidity: String?,
    val description: String?,
    val iconUrl: String?,
    val dayItems: List<WeatherDailyUiState>
) {

    data class WeatherDailyUiState(
        val day: String?,
        val temp: String?,
        val iconUrl: String?
    )

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
            val dayItems = items?.drop(1)?.map { mapDayItem(it, unitsSymbol) }.orEmpty()

            WeatherUiState(
                cityName = cityName,
                temp = temp,
                tempMinMax = tempMinMax,
                pressure = pressure,
                humidity = humidity,
                description = description,
                iconUrl = iconUrl,
                dayItems = dayItems
            )
        }

        private fun mapDayItem(item: ForecastItem, units: String): WeatherDailyUiState =
            with(item) {
                WeatherDailyUiState(
                    day = date?.let { dayFormat.format(Date(it * 1000)) },
                    temp = main?.temp?.let { res.getTemp(it, units) },
                    iconUrl = weather?.icon?.let(res::getIconUrl)
                )
            }

        private fun unitsSymbol(units: String) = when (units) {
            "metric" -> "C"
            "imperial" -> "F"
            else -> ""
        }

        companion object {
            @JvmStatic
            private val dayFormat = SimpleDateFormat("dd/MM hh:mm", Locale.getDefault())
        }
    }
}
