package com.serhiiv.openweather

import com.serhiiv.openweather.core.tools.Logger
import com.serhiiv.openweather.di.AppComponent
import com.serhiiv.openweather.test.BuildConfig
import com.serhiiv.openweather.tools.LoggerPrinter
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber

class BaseApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()

        initLogger()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return AppComponent.Initializer.init()
    }

    private fun initLogger() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Logger.addPrinter(LoggerPrinter.Timber())
        }
    }
}
