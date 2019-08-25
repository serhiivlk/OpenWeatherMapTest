package com.serhiiv.openweather.di.feature

import com.serhiiv.openweather.di.PerFragment
import com.serhiiv.openweather.weather.WeatherFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FeatureWeatherModule {
    @PerFragment
    @ContributesAndroidInjector
    fun contributeWeatherFragment(): WeatherFragment
}
