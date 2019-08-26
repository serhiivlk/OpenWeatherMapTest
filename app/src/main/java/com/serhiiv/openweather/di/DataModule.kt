package com.serhiiv.openweather.di

import com.serhiiv.openweather.data.ForecastRepositoryImpl
import com.serhiiv.openweather.data.datasource.ForecastRemoteDataSource
import com.serhiiv.openweather.data.remote.ApiServiceFactory
import com.serhiiv.openweather.data.remote.ForecastService
import com.serhiiv.openweather.data.remote.datasource.ForecastRemoteDataSourceImpl
import com.serhiiv.openweather.domain.repository.ForecastRepository
import com.serhiiv.openweather.test.BuildConfig
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [DataModule.RemoteModule::class])
interface DataModule {
    @Binds
    fun bindForecastRepository(impl: ForecastRepositoryImpl): ForecastRepository

    @Module
    interface RemoteModule {

        @Binds
        fun bindForecastRemoteDataSource(impl: ForecastRemoteDataSourceImpl): ForecastRemoteDataSource

        @Module
        companion object {
            @Provides
            @JvmStatic
            fun provideForecastService(): ForecastService {
                return ApiServiceFactory.create(ForecastService::class.java) {
                    loggingEnabled = BuildConfig.DEBUG
                }
            }
        }
    }
}
