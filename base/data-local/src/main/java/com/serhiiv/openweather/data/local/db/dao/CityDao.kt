package com.serhiiv.openweather.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.serhiiv.openweather.data.local.db.constants.CityConstants
import com.serhiiv.openweather.data.local.model.CityCashedEntity

@Dao
interface CityDao {
    @Query(CityConstants.QUERY_CITY)
    suspend fun getCity(): CityCashedEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: CityCashedEntity)

    @Query(CityConstants.QUERY_DELETE_ALL)
    suspend fun deleteAll()
}