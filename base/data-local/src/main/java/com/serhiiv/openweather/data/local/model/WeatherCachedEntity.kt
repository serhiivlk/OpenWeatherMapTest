package com.piotrek1543.android.boilerplate.cache.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.serhiiv.openweather.data.local.db.constants.WeatherConstants
import com.serhiiv.openweather.data.local.model.base.CachedEntity


/**
 * Model used solely for the caching of a [Weather]
 */
@Entity(
    tableName = WeatherConstants.TABLE_NAME,
    foreignKeys = [ForeignKey(
        entity = MainCachedEntity::class,
        parentColumns = ["dtKey"],
        childColumns = ["dtKey"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class WeatherCachedEntity(
    @PrimaryKey
    var id: Long? = null,
    var dtKey: Long? = null,
    var main: String? = null,
    var description: String? = null,
    var icon: String? = null
) : CachedEntity
