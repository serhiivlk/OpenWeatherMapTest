package com.serhiiv.openweather.navigation

import androidx.navigation.NavController
import com.serhiiv.openweather.core.android.navigation.Navigator
import com.serhiiv.openweather.test.R
import javax.inject.Inject

class NavigatorImpl @Inject constructor() : Navigator {
    private var navController: NavController? = null

    override fun bind(navController: NavController) {
        this.navController = navController
    }

    override fun unbind() {
        navController = null
    }

    override fun actionSettingsFromWeather() {
        navController?.navigate(R.id.action_weatherFragment_to_settingsFragment)
    }

    override fun actionChooseCityFromWeather() {
        navController?.navigate(R.id.action_weatherFragment_to_chooseCityFragment)
    }

    override fun actionWeatherFromChooseCity() {
        navController?.run {
            if (currentDestination?.id == R.id.chooseCityFragment) navigateUp()
        }
    }
}
