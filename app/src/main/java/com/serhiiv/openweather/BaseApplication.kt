package com.serhiiv.openweather

import com.serhiiv.openweather.core.android.di.App
import com.serhiiv.openweather.core.tools.Logger
import com.serhiiv.openweather.di.AppComponent
import com.serhiiv.openweather.navigation.NavigationLifecycleCallback
import com.serhiiv.openweather.test.BuildConfig
import com.serhiiv.openweather.tools.LoggerPrinter
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber
import javax.inject.Inject

class BaseApplication : DaggerApplication(), App {

    @Inject
    lateinit var navigationLifecycleCallback: NavigationLifecycleCallback

    override fun onCreate() {
        super.onCreate()

        initLogger()
        registerNavigationCallback()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return AppComponent.Initializer.init(this)
    }

    private fun initLogger() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Logger.addPrinter(LoggerPrinter.Timber())
        }
    }

    private fun registerNavigationCallback() {
        registerActivityLifecycleCallbacks(navigationLifecycleCallback)
    }
}
