package com.serhiiv.openweather.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.piotrek1543.android.boilerplate.cache.model.WeatherCachedEntity
import com.serhiiv.openweather.data.local.db.constants.WeatherConstants

@Dao
interface WeatherDao {
    @Query(WeatherConstants.QUERY_WEATHER)
    suspend fun getAll(): List<WeatherCachedEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: List<WeatherCachedEntity>)

    @Query(WeatherConstants.QUERY_DELETE_ALL)
    suspend fun deleteAll()
}
