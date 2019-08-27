package com.serhiiv.openweather.di

import com.serhiiv.openweather.core.storage.PreferenceStore
import com.serhiiv.openweather.data.ForecastRepositoryImpl
import com.serhiiv.openweather.data.SelectableCityRepositoryImpl
import com.serhiiv.openweather.data.datasource.ForecastRemoteDataSource
import com.serhiiv.openweather.data.datasource.SelectableCityLocalDataSource
import com.serhiiv.openweather.data.local.datasource.SelectableCityLocalDataSourceImpl
import com.serhiiv.openweather.data.local.storage.PreferenceStoreImpl
import com.serhiiv.openweather.data.remote.ApiClient
import com.serhiiv.openweather.data.remote.ForecastService
import com.serhiiv.openweather.data.remote.datasource.ForecastRemoteDataSourceImpl
import com.serhiiv.openweather.domain.repository.ForecastRepository
import com.serhiiv.openweather.domain.repository.SelectableCityRepository
import com.serhiiv.openweather.test.BuildConfig
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(
    includes = [
        DataModule.RepositoryModule::class,
        DataModule.RemoteModule::class,
        DataModule.LocalModule::class
    ]
)
interface DataModule {
    @Module
    interface RepositoryModule {

        @Binds
        fun bindForecastRepository(impl: ForecastRepositoryImpl): ForecastRepository

        @Binds
        fun bindSelectableCityRepository(impl: SelectableCityRepositoryImpl): SelectableCityRepository
    }

    @Module
    interface LocalModule {
        @Binds
        fun bindPreferenceStore(impl: PreferenceStoreImpl): PreferenceStore

        @Binds
        fun bindSelectableCityLocalDataSource(impl: SelectableCityLocalDataSourceImpl): SelectableCityLocalDataSource
    }

    @Module
    interface RemoteModule {

        @Binds
        fun bindForecastRemoteDataSource(impl: ForecastRemoteDataSourceImpl): ForecastRemoteDataSource

        @Module
        companion object {
            @Provides
            @JvmStatic
            fun provideForecastService(): ForecastService {
                return ApiClient.create(ForecastService::class.java) {
                    loggingEnabled = BuildConfig.DEBUG
                }
            }
        }
    }
}
