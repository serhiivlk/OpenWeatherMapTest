package com.serhiiv.openweather.weather

import com.serhiiv.openweather.core.android.di.App
import javax.inject.Inject

class WeatherResources @Inject constructor(app: App) {
    private val res = app.getApplicationContext().resources

    fun getTemp(temp: Double, unitsSymbol: String): String =
        res.getString(R.string.weather_temp_formatted, temp, unitsSymbol)

    fun getTempMinMax(tempMin: Double, tempMax: Double, unitsSymbol: String): String =
        res.getString(R.string.weather_temp_min_max_formatted, tempMin, tempMax, unitsSymbol)

    fun getPressure(pressure: Double): String =
        res.getString(R.string.weather_pressure_formatted, pressure)

    fun getHumidity(humidity: Int): String =
        res.getString(R.string.weather_humidity_formatted, humidity)

    fun getIconUrl(icon: String): String = res.getString(R.string.weather_icon_url_formatted, icon)
}
