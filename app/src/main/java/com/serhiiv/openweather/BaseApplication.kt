package com.serhiiv.openweather

import com.serhiiv.openweather.di.AppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class BaseApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return AppComponent.Initializer.init()
    }
}
