package com.serhiiv.openweather.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.piotrek1543.android.boilerplate.cache.model.MainCachedEntity
import com.serhiiv.openweather.data.local.db.constants.MainConstants

@Dao
interface MainDao {
    @Query(MainConstants.QUERY_MAIN)
    suspend fun getAll(): List<MainCachedEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: List<MainCachedEntity>)

    @Query(MainConstants.QUERY_DELETE_ALL)
    suspend fun deleteAll()
}
