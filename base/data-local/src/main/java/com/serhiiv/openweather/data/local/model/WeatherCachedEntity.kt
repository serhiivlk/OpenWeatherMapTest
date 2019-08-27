package com.piotrek1543.android.boilerplate.cache.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.serhiiv.openweather.data.local.db.constants.WeatherConstants
import com.serhiiv.openweather.data.local.model.ListCachedEntity
import com.serhiiv.openweather.data.local.model.base.CachedEntity


@Entity(
    tableName = WeatherConstants.TABLE_NAME,
    foreignKeys = [ForeignKey(
        entity = ListCachedEntity::class,
        parentColumns = ["dt"],
        childColumns = ["dtKey"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class WeatherCachedEntity(
    @PrimaryKey
    var dtKey: Long? = null,
    var id: Long? = null,
    var main: String? = null,
    var description: String? = null,
    var icon: String? = null
) : CachedEntity
