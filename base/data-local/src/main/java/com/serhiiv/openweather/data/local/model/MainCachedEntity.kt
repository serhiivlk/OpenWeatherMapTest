package com.piotrek1543.android.boilerplate.cache.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import com.serhiiv.openweather.data.local.db.constants.MainConstants
import com.serhiiv.openweather.data.local.model.ListCachedEntity
import com.serhiiv.openweather.data.local.model.base.CachedEntity

/**
 * Model used solely for the caching of a [Main]
 */
@Entity(
    tableName = MainConstants.TABLE_NAME,
    foreignKeys = [ForeignKey(
        entity = ListCachedEntity::class,
        parentColumns = ["dt"],
        childColumns = ["dtKey"],
        onDelete = CASCADE
    )]
)
data class MainCachedEntity(
    @PrimaryKey
    var dtKey: Long? = null,
    var temp: Double? = null,
    var tempMin: Double? = null,
    var tempMax: Double? = null,
    var pressure: Double? = null,
    var seaLevel: String? = null,
    var grndLevel: String? = null,
    var humidity: Int? = null,
    var tempKf: String? = null
) : CachedEntity
