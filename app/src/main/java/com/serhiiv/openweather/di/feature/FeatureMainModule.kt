package com.serhiiv.openweather.di.feature

import com.serhiiv.openweather.MainActivity
import com.serhiiv.openweather.di.PerActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FeatureMainModule {
    @PerActivity
    @ContributesAndroidInjector(
        modules = [
            FeatureWeatherModule::class,
            FeatureSettingsModule::class
        ]
    )
    fun contributeMainActivity(): MainActivity
}
