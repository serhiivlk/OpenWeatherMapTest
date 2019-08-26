package com.serhiiv.openweather.di.feature

import com.serhiiv.openweather.di.PerFragment
import com.serhiiv.openweather.settings.SettingsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FeatureSettingsModule {
    @PerFragment
    @ContributesAndroidInjector
    fun contributeSettingsFragment(): SettingsFragment
}
