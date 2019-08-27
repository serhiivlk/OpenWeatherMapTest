package com.serhiiv.openweather.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.serhiiv.openweather.data.local.db.constants.CityConstants
import com.serhiiv.openweather.data.local.model.base.CachedEntity

@Entity(tableName = CityConstants.TABLE_NAME)
data class CityCashedEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long?,
    var name: String? = null,
    var country: String? = null
) : CachedEntity
