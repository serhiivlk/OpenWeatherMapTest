package com.serhiiv.openweather.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.serhiiv.openweather.data.local.db.constants.ListConstants
import com.serhiiv.openweather.data.local.model.base.CachedEntity

@Entity(
    tableName = ListConstants.TABLE_NAME
)
data class ListCachedEntity(
    @PrimaryKey
    var dt: Long? = null,
    var dtTxt: String? = null
) : CachedEntity
