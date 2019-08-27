package com.serhiiv.openweather.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.serhiiv.openweather.data.local.db.constants.ListConstants
import com.serhiiv.openweather.data.local.model.ListCachedEntity

@Dao
interface ListDao {
    @Query(ListConstants.QUERY_LIST)
    suspend fun getAll(): List<ListCachedEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: List<ListCachedEntity>)

    @Query(ListConstants.QUERY_DELETE_ALL)
    suspend fun deleteAll()
}
