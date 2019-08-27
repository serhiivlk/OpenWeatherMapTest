package com.serhiiv.openweather.data.local.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.serhiiv.openweather.data.local.db.constants.CityConstants
import com.serhiiv.openweather.data.local.model.base.CachedEntity

@Entity(
    tableName = CityConstants.TABLE_NAME,
    foreignKeys = [ForeignKey(
        entity = ForecastCachedEntity::class,
        parentColumns = ["cityId"],
        childColumns = ["id"],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE
    )]
)
data class CityCashedEntity(
    @PrimaryKey
    var id: Long?,
    var name: String? = null,
    var country: String? = null
) : CachedEntity
