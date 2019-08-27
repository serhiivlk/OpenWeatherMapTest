package com.serhiiv.openweather.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.piotrek1543.android.boilerplate.cache.model.WeatherCachedEntity
import com.serhiiv.openweather.data.local.db.constants.ListWeatherRelationConstants
import com.serhiiv.openweather.data.local.model.ListWeatherRelationEntity

@Dao
interface ListWeatherRelationDao {
    @Query("SELECT * FROM weather INNER JOIN list_weather_relation ON weather.id=weatherId WHERE listDt=:dt LIMIT 1")
    suspend fun getWeatherByListDt(dt: Long): WeatherCachedEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: List<ListWeatherRelationEntity>)

    @Query(ListWeatherRelationConstants.QUERY_DELETE_ALL)
    suspend fun deleteAll()
}
