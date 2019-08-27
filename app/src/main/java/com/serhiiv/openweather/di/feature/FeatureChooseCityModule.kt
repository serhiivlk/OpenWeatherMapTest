package com.serhiiv.openweather.di.feature

import com.serhiiv.openweather.choosecity.ChooseCityFragment
import com.serhiiv.openweather.di.PerFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FeatureChooseCityModule {
    @PerFragment
    @ContributesAndroidInjector
    fun contributeChooseCityFragment(): ChooseCityFragment
}
