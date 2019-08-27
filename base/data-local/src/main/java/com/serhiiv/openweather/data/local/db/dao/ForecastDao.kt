package com.serhiiv.openweather.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.serhiiv.openweather.data.local.db.constants.ForecastConstants
import com.serhiiv.openweather.data.local.model.ForecastCachedEntity

@Dao
interface ForecastDao {
    @Query(ForecastConstants.QUERY_FORECAST)
    suspend fun getForecast(): ForecastCachedEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: ForecastCachedEntity)

    @Query(ForecastConstants.QUERY_DELETE_ALL)
    suspend fun deleteAll()
}
