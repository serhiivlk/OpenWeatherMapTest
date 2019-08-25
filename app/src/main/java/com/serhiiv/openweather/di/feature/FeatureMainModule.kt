package com.serhiiv.openweather.di.feature

import com.serhiiv.openweather.di.PerActivity
import com.serhiiv.openweather.test.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FeatureMainModule {
    @PerActivity
    @ContributesAndroidInjector(
        modules = [
            FeatureWeatherModule::class
        ]
    )
    fun contributeMainActivity(): MainActivity
}
