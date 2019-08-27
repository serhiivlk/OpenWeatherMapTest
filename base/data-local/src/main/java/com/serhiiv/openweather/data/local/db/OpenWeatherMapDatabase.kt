package com.serhiiv.openweather.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.piotrek1543.android.boilerplate.cache.model.MainCachedEntity
import com.piotrek1543.android.boilerplate.cache.model.WeatherCachedEntity
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
    ],
    exportSchema = false,
    version = 2
)
abstract class OpenWeatherMapDatabase @Inject constructor() : RoomDatabase() {

}
