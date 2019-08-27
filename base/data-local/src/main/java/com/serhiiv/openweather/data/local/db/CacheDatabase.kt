package com.serhiiv.openweather.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.piotrek1543.android.boilerplate.cache.model.MainCachedEntity
import com.piotrek1543.android.boilerplate.cache.model.WeatherCachedEntity
import com.serhiiv.openweather.data.local.db.dao.*
import com.serhiiv.openweather.data.local.model.CityCashedEntity
import com.serhiiv.openweather.data.local.model.ForecastCachedEntity
import com.serhiiv.openweather.data.local.model.ListCachedEntity
import javax.inject.Inject

@Database(
    entities = [
        CityCashedEntity::class,
        ForecastCachedEntity::class,
        ListCachedEntity::class,
        MainCachedEntity::class,
        WeatherCachedEntity::class
//        ListWeatherRelationEntity::class
    ],
    exportSchema = false,
    version = 1
)
abstract class CacheDatabase @Inject constructor() : RoomDatabase() {
    abstract fun forecastDao(): ForecastDao

    abstract fun cityDao(): CityDao

    abstract fun listDao(): ListDao

    abstract fun mainDao(): MainDao

    abstract fun weatherDao(): WeatherDao

//    abstract fun listWeatherRelationDao(): ListWeatherRelationDao
}
