package com.serhiiv.openweather.di

import com.serhiiv.openweather.BaseApplication
import com.serhiiv.openweather.di.feature.FeatureMainModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        FeatureMainModule::class
    ]
)
@PerApplication
interface AppComponent : AndroidInjector<BaseApplication> {

    @Component.Factory
    interface Factory {
        fun create(): AppComponent
    }

    object Initializer {
        fun init(): AppComponent {
            return DaggerAppComponent.factory().create()
        }
    }
}
