package com.serhiiv.openweather.di

import com.serhiiv.openweather.NetworkHandlerImpl
import com.serhiiv.openweather.core.android.navigation.Navigator
import com.serhiiv.openweather.data.NetworkHandler
import com.serhiiv.openweather.navigation.NavigatorImpl
import dagger.Binds
import dagger.Module

@Module
interface MainToolsModule {
    @Binds
    fun bindNetworkHandler(impl: NetworkHandlerImpl): NetworkHandler

    @Binds
    @PerApplication
    fun bindNavigator(impl: NavigatorImpl): Navigator
}
