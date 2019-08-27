package com.serhiiv.openweather.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.serhiiv.openweather.data.local.db.constants.ForecastConstants
import com.serhiiv.openweather.data.local.model.base.CachedEntity

@Entity(tableName = ForecastConstants.TABLE_NAME)
data class ForecastCachedEntity(
    @PrimaryKey()
    var cityId: Long = 0,
    var cnt: Int? = null
) : CachedEntity
