package com.serhiiv.openweather.core.android.navigation

import androidx.navigation.NavController

interface Navigator {
    fun bind(navController: NavController)
    fun unbind()

    fun actionSettingsFromWeather()

    fun actionChooseCityFromWeather()

    fun actionWeatherFromChooseCity()
}
