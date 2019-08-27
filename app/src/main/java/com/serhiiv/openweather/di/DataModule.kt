package com.serhiiv.openweather.di

import androidx.room.Room
import com.serhiiv.openweather.core.android.di.App
import com.serhiiv.openweather.core.storage.PreferenceStore
import com.serhiiv.openweather.data.ForecastRepositoryImpl
import com.serhiiv.openweather.data.SelectableCityRepositoryImpl
import com.serhiiv.openweather.data.datasource.ForecastLocalDataSource
import com.serhiiv.openweather.data.datasource.ForecastRemoteDataSource
import com.serhiiv.openweather.data.datasource.SelectableCityLocalDataSource
import com.serhiiv.openweather.data.local.datasource.ForecastLocalDataSourceImpl
import com.serhiiv.openweather.data.local.datasource.SelectableCityLocalDataSourceImpl
import com.serhiiv.openweather.data.local.db.CacheDatabase
import com.serhiiv.openweather.data.local.db.dao.*
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

    @Module(includes = [LocalModule.DatabaseModule::class])
    interface LocalModule {
        @Binds
        fun bindPreferenceStore(impl: PreferenceStoreImpl): PreferenceStore

        @Binds
        fun bindSelectableCityLocalDataSource(impl: SelectableCityLocalDataSourceImpl): SelectableCityLocalDataSource

        @Binds
        fun bindForecastLocalDataSource(impl: ForecastLocalDataSourceImpl): ForecastLocalDataSource

        @Module
        class DatabaseModule {
            @Provides
            fun provideOpenWeatherMapDatabase(app: App): CacheDatabase {
                return Room.databaseBuilder(
                    app.getApplicationContext(),
                    CacheDatabase::class.java,
                    "app.db"
                )
                    .fallbackToDestructiveMigration()
                    .fallbackToDestructiveMigrationOnDowngrade()
                    .build()
            }

            @Provides
            fun provideForecastDao(database: CacheDatabase): ForecastDao = database.forecastDao()

            @Provides
            fun provideCityDao(database: CacheDatabase): CityDao = database.cityDao()

            @Provides
            fun provideListDou(database: CacheDatabase): ListDao = database.listDao()

            @Provides
            fun provideMainDao(database: CacheDatabase): MainDao = database.mainDao()

            @Provides
            fun provideWeatherDao(database: CacheDatabase): WeatherDao = database.weatherDao()

//            @Provides
//            fun provideListWeatherRelationDao(database: CacheDatabase): ListWeatherRelationDao =
//                database.listWeatherRelationDao()
        }
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
