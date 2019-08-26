package com.serhiiv.openweather.di

import com.serhiiv.openweather.NetworkHandlerImpl
import com.serhiiv.openweather.data.NetworkHandler
import dagger.Binds
import dagger.Module

@Module
interface MainToolsModule {
    @Binds
    fun bindNetworkHandler(impl: NetworkHandlerImpl): NetworkHandler
}
