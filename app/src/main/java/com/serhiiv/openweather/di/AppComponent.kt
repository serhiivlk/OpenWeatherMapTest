package com.serhiiv.openweather.di

import com.serhiiv.openweather.BaseApplication
import com.serhiiv.openweather.core.android.di.App
import com.serhiiv.openweather.di.feature.FeatureMainModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        MainToolsModule::class,
        DataModule::class,
        DomainModule::class,
        FeatureMainModule::class
    ]
)
@PerApplication
interface AppComponent : AndroidInjector<BaseApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: App): AppComponent
    }

    object Initializer {
        fun init(app: App): AppComponent {
            return DaggerAppComponent.factory().create(app)
        }
    }
}
